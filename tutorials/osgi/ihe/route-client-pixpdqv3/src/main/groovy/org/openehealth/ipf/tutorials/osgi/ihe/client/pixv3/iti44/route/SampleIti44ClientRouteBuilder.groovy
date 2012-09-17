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
package org.openehealth.ipf.tutorials.osgi.ihe.client.pixv3.iti44.route

import org.apache.camel.spring.SpringRouteBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Boris Stanojevic
 */
public class SampleIti44ClientRouteBuilder extends SpringRouteBuilder {

    private Logger LOG = LoggerFactory.getLogger(SampleIti44ClientRouteBuilder.class)

    void configure() {

        LOG.info("Configuring the ITI-44 Client")

        from('file:workspace/pixv3/in')
            .to('jms:queue:delivery-pixv3-file')

        from('jms:queue:delivery-pixv3-file')
            .to('xds-iti44://0.0.0.0:8183/cxf/xds-iti44-service1?secure=true')
            .process {
                LOG.info("ITI-44 RESPONSE: ${it.in.body}")
            }
            .to('file:workspace/pixv3/out')
        }
}
