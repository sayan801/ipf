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
package org.openehealth.ipf.commons.ihe.hl7v2.definitions;

import java.util.Collections;
import java.util.Map;

import ca.uhn.hl7v2.Version;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import org.openehealth.ipf.modules.hl7.parser.CustomModelClassFactory;

/**
 * Utilities to work with custom HAPI class definitions.
 *
 * @author Dmytro Rud
 */
public final class CustomModelClassUtils {

    private static final String CUSTOM_EVENT_MAP_DIRECTORY = "org/openehealth/ipf/commons/ihe/hl7v2/";

    private CustomModelClassUtils() {
        throw new IllegalStateException("Utility class, cannot instantiate");
    }

    /**
     * Creates a model class factory for the given transaction and HL7 version.
     */
    public static CustomModelClassFactory createFactory(String transaction, String version) {
        String packageName = String.format("%s.%s.%s",
                CustomModelClassUtils.class.getPackage().getName(),
                transaction,
                Version.versionOf(version).getPackageVersion());
        Map<String, String[]> map = Collections.singletonMap(version, new String[]{packageName});
        CustomModelClassFactory cmcf = new CustomModelClassFactory(map);
        cmcf.setEventMapDirectory(CUSTOM_EVENT_MAP_DIRECTORY);
        return cmcf;
    }

    /**
     * Creates a parser for the given transaction and HL7 version.
     *
     * @deprecated the correct parser should be obtained from the {@link ca.uhn.hl7v2.HapiContext}
     * that has been configured with the correct {@link ca.uhn.hl7v2.parser.ModelClassFactory}.
     */
    public static Parser createParser(String transaction, String version) {
        return new PipeParser(createFactory(transaction, version));
    }

}
