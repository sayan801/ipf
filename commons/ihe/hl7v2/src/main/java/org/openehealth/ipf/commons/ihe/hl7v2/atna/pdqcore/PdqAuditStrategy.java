/*
 * Copyright 2015 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.hl7v2.atna.pdqcore;

import ca.uhn.hl7v2.model.Message;
import org.openehealth.ipf.commons.ihe.core.atna.AuditStrategySupport;
import org.openehealth.ipf.commons.ihe.hl7v2.atna.QueryAuditDataset;

import java.util.Map;

/**
 * Generic audit strategy for ITI-21 and ITI-22 (PDQ).
 * @author Dmytro Rud
 */
public abstract class PdqAuditStrategy extends AuditStrategySupport<QueryAuditDataset> {
    
    
    public PdqAuditStrategy(boolean serverSide) {
        super(serverSide);
    }

    
    @Override
    public QueryAuditDataset enrichAuditDatasetFromRequest(QueryAuditDataset auditDataset, Object msg, Map<String, Object> parameters) {
        PdqAuditStrategyUtils.enrichAuditDatasetFromRequest(auditDataset, (Message)msg, parameters);
        return auditDataset;
    }
    
    @Override
    public boolean enrichAuditDatasetFromResponse(QueryAuditDataset auditDataset, Object msg) {
        PdqAuditStrategyUtils.enrichAuditDatasetFromResponse(auditDataset, (Message)msg);
        return true;
    }

    @Override
    public QueryAuditDataset createAuditDataset() {
        return new QueryAuditDataset(isServerSide());
    }
    
}
