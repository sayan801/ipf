/*
 * Copyright 2020 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.transform.requests.query;

import lombok.Getter;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.EbXMLAdhocQueryRequest;
import org.openehealth.ipf.commons.ihe.xds.core.requests.query.FindMedicationTreatmentPlansQuery;

/**
 * Transforms between {@link FindMedicationTreatmentPlansQuery} and {@link EbXMLAdhocQueryRequest}.
 * @author Quentin Ligier
 * @since 3.7
 */
public class FindMedicationTreatmentPlansQueryTransformer extends PharmacyStableDocumentsQueryTransformer<FindMedicationTreatmentPlansQuery> {

    @Getter
    private static final FindMedicationTreatmentPlansQueryTransformer instance = new FindMedicationTreatmentPlansQueryTransformer();

    private FindMedicationTreatmentPlansQueryTransformer() {
    }
}
