/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds;

import org.openehealth.ipf.commons.ihe.xds.cxf.audit.ItiAuditStrategy;

/**
 * Provides access to an audit strategy for classes that support ATNA auditing. 
 * @author Jens Riemschneider
 */
public interface Auditable {
    /**
     * Creates a transaction-specific audit strategy.
     * @return a newly created audit strategy instance
     */
    ItiAuditStrategy createAuditStrategy(boolean allowIncompleteAudit);
}