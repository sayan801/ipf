/*
 * Copyright 2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.openehealth.ipf.commons.ihe.fhir.pcc44;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.TokenParam;
import lombok.Builder;
import lombok.ToString;
import org.hl7.fhir.r4.model.MedicationRequest;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;

/**
 * @author Christian Ohr
 * @since 3.6
 */
@ToString
public class MedicationRequestSearchParameters extends Pcc44CommonSearchParameters<MedicationRequest> {

    @Builder
    MedicationRequestSearchParameters(ReferenceParam patientReference,
                                             TokenParam _id,
                                             SortSpec sortSpec,
                                             Set<Include> includeSpec,
                                             Set<Include> revIncludeSpec,
                                             FhirContext fhirContext) {
        super(patientReference, _id, sortSpec, includeSpec, revIncludeSpec, fhirContext);
    }

    @Override
    protected Optional<Comparator<MedicationRequest>> comparatorFor(String paramName) {
        switch (paramName) {
            case MedicationRequest.SP_AUTHOREDON: return Optional.of(nullsLast(comparing(MedicationRequest::getAuthoredOn)));
        }
        return Optional.empty();
    }
}
