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
package org.openehealth.ipf.commons.ihe.xds.core.requests.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.xml.bind.annotation.*;

import java.io.Serial;

/**
 * Represents a stored query for GetSubmissionSets.
 * @author Jens Riemschneider
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSubmissionSetsQuery", propOrder = {"metadataLevel"})
@XmlRootElement(name = "getSubmissionSetsQuery")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class GetSubmissionSetsQuery extends GetByUuidQuery {
    @Serial
    private static final long serialVersionUID = 2089514690641582428L;

    @Getter @Setter private Integer metadataLevel;

    /**
     * Constructs the query.
     */
    public GetSubmissionSetsQuery() {
        super(QueryType.GET_SUBMISSION_SETS);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
