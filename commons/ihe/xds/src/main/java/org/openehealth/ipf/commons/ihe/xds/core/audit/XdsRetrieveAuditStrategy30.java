/*
 * Copyright 2012 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.audit;

import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetRequestType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetRequestType.DocumentRequest;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.ebxml30.RetrieveDocumentSetResponseType.DocumentResponse;
import org.openhealthtools.ihe.atna.auditor.codes.rfc3881.RFC3881EventCodes.RFC3881EventOutcomeCodes;

import java.util.Map;

/**
 * Basis for Strategy pattern implementation for ATNA Auditing
 * in ebXML 3.0-based retrieval-related XDS transactions.
 *
 * @author Dmytro Rud
 */
abstract public class XdsRetrieveAuditStrategy30 extends XdsAuditStrategy<XdsRetrieveAuditDataset> {

    public XdsRetrieveAuditStrategy30(boolean serverSide) {
        super(serverSide);
    }


    @Override
    public XdsRetrieveAuditDataset enrichAuditDatasetFromRequest(XdsRetrieveAuditDataset auditDataset, Object pojo, Map<String, Object> parameters) {
        RetrieveDocumentSetRequestType request = (RetrieveDocumentSetRequestType) pojo;
        if (request.getDocumentRequest() != null) {
            for (DocumentRequest document : request.getDocumentRequest()) {
                auditDataset.registerRequestedDocument(
                        document.getDocumentUniqueId(),
                        document.getRepositoryUniqueId(),
                        document.getHomeCommunityId(),
                        null,
                        null);
            }
        }
        return auditDataset;
    }


    @Override
    public boolean enrichAuditDatasetFromResponse(XdsRetrieveAuditDataset auditDataset, Object pojo) {
        RetrieveDocumentSetResponseType response = (RetrieveDocumentSetResponseType) pojo;
        if (response.getDocumentResponse() != null) {
            for (DocumentResponse documentResponse : response.getDocumentResponse()) {
                auditDataset.registerDeliveredDocument(
                        documentResponse.getDocumentUniqueId(),
                        documentResponse.getRepositoryUniqueId(),
                        documentResponse.getHomeCommunityId());
            }
        }
        return true;
    }


    @Override
    public XdsRetrieveAuditDataset createAuditDataset() {
        return new XdsRetrieveAuditDataset(isServerSide());
    }


    @Override
    public RFC3881EventOutcomeCodes getEventOutcomeCode(Object pojo) {
        // This method is not necessary for retrieve transactions, because the
        // logic is very easy: all successfully retrieved files will be audited
        // with status SUCCESS, and all failed ones -- with status FAILURE.
        return null;
    }
}
