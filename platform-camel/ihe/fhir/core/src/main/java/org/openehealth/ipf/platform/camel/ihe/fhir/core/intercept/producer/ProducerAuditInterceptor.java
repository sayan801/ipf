/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.fhir.core.intercept.producer;

import org.apache.camel.Exchange;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.codes.EventOutcomeIndicator;
import org.openehealth.ipf.commons.ihe.core.atna.AuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.atna.interceptor.AuditInterceptor;
import org.openehealth.ipf.platform.camel.ihe.core.InterceptorSupport;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.FhirComponent;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.FhirEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;
import static org.openehealth.ipf.platform.camel.core.util.Exchanges.resultMessage;


/**
 * Consumer-side FHIR ATNA auditing Camel interceptor.
 *
 * @author Christian Ohr
 * @since 3.1
 */
public class ProducerAuditInterceptor<AuditDatasetType extends FhirAuditDataset>
        extends InterceptorSupport<FhirEndpoint<AuditDatasetType, FhirComponent<AuditDatasetType>>>
        implements AuditInterceptor<AuditDatasetType, FhirEndpoint<AuditDatasetType, FhirComponent<AuditDatasetType>>> {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerAuditInterceptor.class);

    private final AuditContext auditContext;

    public ProducerAuditInterceptor(AuditContext auditContext) {
        this.auditContext = requireNonNull(auditContext);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        var msg = exchange.getIn().getBody(IBaseResource.class);

        var auditDataset = createAndEnrichAuditDatasetFromRequest(getAuditStrategy(), exchange, msg);
        determineParticipantsAddresses(exchange, auditDataset);

        var failed = false;
        try {
            getWrappedProcessor().process(exchange);
            var result = resultMessage(exchange).getBody(IBaseResource.class);
            failed = !enrichAuditDatasetFromResponse(getAuditStrategy(), auditDataset, result);
        } catch (Exception e) {
            // FHIR exception or unexpected exception
            failed = true;
            throw e;
        } finally {
            if (auditDataset != null) {
                auditDataset.setEventOutcomeIndicator(failed ?
                        EventOutcomeIndicator.MajorFailure :
                        EventOutcomeIndicator.Success);
                getAuditStrategy().doAudit(auditContext, auditDataset);
            }
        }
    }

    @Override
    public void determineParticipantsAddresses(Exchange exchange, AuditDatasetType auditDataset) {
    }

    @Override
    public AuditStrategy<AuditDatasetType> getAuditStrategy() {
        return getEndpoint().getClientAuditStrategy();
    }

    /**
     * Creates a new audit dataset and enriches it with data from the request
     * message.  All exception are ignored.
     *
     * @return newly created audit dataset or <code>null</code> when creation failed.
     */
    private AuditDatasetType createAndEnrichAuditDatasetFromRequest(AuditStrategy<AuditDatasetType> strategy, Exchange exchange, IBaseResource msg) {
        try {
            var auditDataset = strategy.createAuditDataset();
            auditDataset.setSourceUserId("unknown");
            auditDataset.setDestinationUserId(exchange.getProperty("CamelToEndpoint").toString());

            // TODO set client-side headers
            return strategy.enrichAuditDatasetFromRequest(auditDataset, msg, exchange.getIn().getHeaders());
        } catch (Exception e) {
            LOG.error("Exception when enriching audit dataset from request", e);
            return null;
        }
    }


    /**
     * Enriches the given audit dataset with data from the response message.
     * All exception are ignored.
     */
    private boolean enrichAuditDatasetFromResponse(AuditStrategy<AuditDatasetType> strategy, AuditDatasetType auditDataset, IBaseResource response) {
        return strategy.enrichAuditDatasetFromResponse(auditDataset, response, auditContext);
    }


}
