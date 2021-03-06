/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.xua;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.openehealth.ipf.commons.ihe.ws.cxf.audit.AbstractAuditInterceptor;
import org.openehealth.ipf.commons.ihe.ws.cxf.audit.WsAuditDataset;
import org.openehealth.ipf.commons.ihe.ws.cxf.audit.XuaProcessor;
import org.openhealthtools.ihe.atna.auditor.models.rfc3881.CodedValueType;
import org.opensaml.core.config.ConfigurationService;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistry;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.xml.SAMLConstants;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeStatement;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;

/**
 * @author Dmytro Rud
 */
@Slf4j
public class BasicXuaProcessor implements XuaProcessor {

    /**
     * If a SAML assertion is stored under this key in the Web Service context,
     * IPF will use it instead of parsing the WS-Security header by itself.
     * If there are no Web Service context element under this key, or if this element
     * does not contain a SAML assertion, IPF will parse the WS-Security header
     * and store the assertion extracted from there (if any) under this key.
     */
    public static final String XUA_SAML_ASSERTION = AbstractAuditInterceptor.class.getName() + ".XUA_SAML_ASSERTION";

    /**
     * XML Namespace URI of WS-Security Extensions 1.1.
     */
    public static final String WSSE_NS_URI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

    public static final String PURPOSE_OF_USE_URI = "urn:oasis:names:tc:xspa:1.0:subject:purposeofuse";

    private static final UnmarshallerFactory SAML_UNMARSHALLER_FACTORY;

    static {
        try {
            InitializationService.initialize();
            XMLObjectProviderRegistry registry = ConfigurationService.get(XMLObjectProviderRegistry.class);
            SAML_UNMARSHALLER_FACTORY = registry.getUnmarshallerFactory();
        } catch (InitializationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts ITI-40 XUA user name from the SAML2 assertion contained
     * in the given CXF message, and stores it in the ATNA audit dataset.
     *
     * @param message
     *      source CXF message.
     * @param headerDirection
     *      direction of the header containing the SAML2 assertion.
     * @param auditDataset
     *      target ATNA audit dataset.
     */
    public void extractXuaUserNameFromSaml2Assertion(
            SoapMessage message,
            Header.Direction headerDirection,
            WsAuditDataset auditDataset)
    {
        Assertion assertion = null;

        // check whether someone has already parsed the SAML2 assertion
        Object o = message.getContextualProperty(XUA_SAML_ASSERTION);
        if (o instanceof Assertion) {
            assertion = (Assertion) o;
        }

        // extract SAML assertion the from WS-Security SOAP header
        if (assertion == null) {
            Header header = message.getHeader(new QName(WSSE_NS_URI, "Security"));
            if (! ((header != null) &&
                    headerDirection.equals(header.getDirection()) &&
                    (header.getObject() instanceof Element)))
            {
                return;
            }

            Element headerElem = (Element) header.getObject();
            NodeList nodeList = headerElem.getElementsByTagNameNS(SAMLConstants.SAML20_NS, "Assertion");
            Element assertionElem = (Element) nodeList.item(0);
            if (assertionElem == null) {
                return;
            }

            Unmarshaller unmarshaller = SAML_UNMARSHALLER_FACTORY.getUnmarshaller(assertionElem);
            try {
                assertion = (Assertion) unmarshaller.unmarshall(assertionElem);
            } catch (UnmarshallingException e) {
                log.warn("Cannot extract SAML assertion from the WS-Security SOAP header", e);
                return;
            }

            message.getExchange().put(XUA_SAML_ASSERTION, assertion);
        }

        // set ATNA XUA userName element
        String userName = ((assertion.getSubject() != null) && (assertion.getSubject().getNameID() != null))
                ? assertion.getSubject().getNameID().getValue() : null;

        String issuer = (assertion.getIssuer() != null)
                ? assertion.getIssuer().getValue() : null;

        if (StringUtils.isNotEmpty(issuer) && StringUtils.isNotEmpty(userName)) {
            auditDataset.setUserName(assertion.getSubject().getNameID().getSPProvidedID() + '<' + userName + '@' + issuer + '>');
        }

        // collect purposes of use
        for (AttributeStatement statement : assertion.getAttributeStatements()) {
            for (Attribute attribute : statement.getAttributes()) {
                if (PURPOSE_OF_USE_URI.equals(attribute.getName())) {
                    for (XMLObject value : attribute.getAttributeValues()) {
                        NodeList purposeElemList = value.getDOM().getElementsByTagNameNS("urn:hl7-org:v3", "PurposeOfUse");
                        for (int i = 0; i < purposeElemList.getLength(); ++i) {
                            Element purposeElem = (Element) purposeElemList.item(i);
                            CodedValueType cvt = new CodedValueType();
                            cvt.setCode(purposeElem.getAttribute("code"));
                            cvt.setCodeSystemName(purposeElem.getAttribute("codeSystem"));
                            cvt.setOriginalText(purposeElem.getAttribute("displayName"));
                            auditDataset.getPurposesOfUse().add(cvt);
                        }
                    }
                }
            }
        }
    }

}
