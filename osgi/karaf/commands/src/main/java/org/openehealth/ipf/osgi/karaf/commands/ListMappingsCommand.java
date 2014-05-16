/*
 * Copyright 2013 the original author or authors.
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
package org.openehealth.ipf.osgi.karaf.commands;

import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.openehealth.ipf.commons.map.MappingService;
import org.openehealth.ipf.osgi.karaf.util.BlueprintUtils;

import java.util.Iterator;

/**
 * @author Boris Stanojevic
 */
@Command(scope = "ipf", name = "listmappings", description="Lists all Mappings available on IPF Mapping Service")
public class ListMappingsCommand extends OsgiCommandSupport {


    @Override
    protected Object doExecute() throws Exception {
        MappingService mappingService = BlueprintUtils.getOsgiService(getBundleContext(), MappingService.class, 2000);

        if (mappingService != null && mappingService.mappingKeys() != null){
            System.out.println("\nAvailable Mappings(" + mappingService.mappingKeys().size() + ")");
            System.out.println("======================== ");
            for (Iterator it = mappingService.mappingKeys().iterator(); it.hasNext(); ){
                Object mappingKey = it.next();
                System.out.println("\n\tMappingKey: " + mappingKey);
                for (Iterator innerIt = mappingService.keys(mappingKey).iterator(); innerIt.hasNext(); ){
                    Object key = innerIt.next();
                    Object value = mappingService.get(mappingKey, key);
                    System.out.println("\t\t" + key + " -> " + value);
                }
            }
        }
        return null;
    }

}
