/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.hpd.controls.pagination;

import lombok.extern.slf4j.Slf4j;
import org.openehealth.ipf.commons.ihe.hpd.HpdUtils;
import org.openehealth.ipf.commons.ihe.hpd.controls.ControlUtils;
import org.openehealth.ipf.commons.ihe.hpd.controls.handlers.ProducerHandler;
import org.openehealth.ipf.commons.ihe.hpd.controls.handlers.ProducerHandlerBase;
import org.openehealth.ipf.commons.ihe.hpd.stub.dsmlv2.*;
import org.springframework.beans.BeanUtils;

import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;
import jakarta.xml.bind.JAXBElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Client-side handler of pagination controls.
 *
 * @author Dmytro Rud
 * @since 4.3
 */
@Slf4j
public class ProducerPaginationHandler extends ProducerHandlerBase<BatchRequest, BatchResponse> {

    private final int defaultPageSize;

    public ProducerPaginationHandler(ProducerHandler<BatchRequest, BatchResponse> wrappedHandler, int defaultPageSize) {
        super(wrappedHandler);
        this.defaultPageSize = defaultPageSize;
        if (defaultPageSize < 1) {
            throw new IllegalArgumentException("Default page size must be a positive number");
        }
    }

    @Override
    public BatchResponse handle(Object client, BatchRequest batchRequest0) throws Exception {
        // to leave the original request untouched, make a copy of it and work with the copy
        BatchRequest batchRequest = new BatchRequest();
        BeanUtils.copyProperties(batchRequest0, batchRequest, "batchRequests");
        batchRequest.getBatchRequests().addAll(batchRequest0.getBatchRequests());

        Map<String, SearchRequest> requestMap = new HashMap<>();
        Map<String, JAXBElement<?>> responseMap = new HashMap<>();
        Map<String, PagedResultsResponseControl> paginations = new HashMap<>();

        log.debug("Start handling batch request with ID {}", batchRequest.getRequestID());

        for (DsmlMessage request : batchRequest.getBatchRequests()) {
            String requestId = request.getRequestID();
            if (request instanceof SearchRequest searchRequest) {
                requestMap.put(requestId, searchRequest);
                PagedResultsResponseControl pagination = ControlUtils.extractControl(request, PagedResultsResponseControl.OID);
                if (pagination != null) {
                    paginations.put(requestId, pagination);
                }
            }
        }
        log.debug("Expect pagination for requests with IDs {}", paginations.keySet());

        while (true) {
            BatchResponse batchResponse = getWrappedHandler().handle(client, batchRequest);
            Set<String> expectedPaginationResponses = new HashSet<>(paginations.keySet());

            for (JAXBElement<?> jaxbElement : batchResponse.getBatchResponses()) {
                Object value = jaxbElement.getValue();
                String requestId = HpdUtils.extractResponseRequestId(value);
                expectedPaginationResponses.remove(requestId);

                if (value instanceof SearchResponse searchResponse) {
                    PagedResultsResponseControl pagination = ControlUtils.extractControl(searchResponse, PagedResultsResponseControl.OID);

                    Integer resultCode = ((searchResponse.getSearchResultDone() != null) && (searchResponse.getSearchResultDone().getResultCode() != null))
                            ? searchResponse.getSearchResultDone().getResultCode().getCode()
                            : null;

                    if ((resultCode == null) || (resultCode != 0)) {
                        if (paginations.containsKey(requestId)) {
                            log.debug("Got result code {} in response with ID {} --> break pagination", resultCode, requestId);
                            paginations.remove(requestId);
                        } else {
                            log.debug("Got result code {} in response with ID {} --> there was no pagination, do nothing", resultCode, requestId);
                        }
                    } else if (paginations.containsKey(requestId)) {
                        if (pagination == null) {
                            log.debug("Expected pagination control in response with ID {}, got none --> break pagination", requestId);
                            paginations.remove(requestId);
                        } else if (pagination.getCookie() == null) {
                            log.debug("Got pagination control for response with ID {} without cookie --> pagination finished", requestId);
                            paginations.remove(requestId);
                        } else {
                            log.debug("Got pagination control for response with ID {} with cookie --> request next page", requestId);
                            PagedResultsResponseControl responseControl = paginations.get(requestId);
                            PagedResultsControl requestControl = new PagedResultsControl(responseControl.getResultSize(), pagination.getCookie(), true);
                            paginations.put(requestId, ControlUtils.convert(requestControl));
                        }
                    } else if (pagination != null) {
                        if (pagination.getCookie() == null) {
                            log.debug("Expected no pagination control in response with ID {}, got one without cookie --> do nothing", requestId);
                        } else {
                            log.debug("Expected no pagination control in response with ID {}, got one with cookie --> request next page with default page size", requestId);
                            PagedResultsControl requestControl = new PagedResultsControl(defaultPageSize, pagination.getCookie(), true);
                            paginations.put(requestId, ControlUtils.convert(requestControl));
                        }
                    } else {
                        log.debug("Expected no pagination control in response with ID {}, got none --> do nothing", requestId);
                    }

                    if (responseMap.containsKey(requestId)) {
                        SearchResponse oldSearchResponse = (SearchResponse) responseMap.get(requestId).getValue();
                        searchResponse.getSearchResultEntry().addAll(0, oldSearchResponse.getSearchResultEntry());
                    }

                } else if (paginations.containsKey(requestId)) {
                    log.debug("Got {} instead of SearchResponse for request with ID {} --> pagination not applicable", value.getClass().getSimpleName(), requestId);
                    paginations.remove(requestId);
                } else {
                    log.debug("Got {} for request with ID {} --> do nothing", value.getClass().getSimpleName(), requestId);
                }

                responseMap.put(requestId, jaxbElement);
            }

            if (!expectedPaginationResponses.isEmpty()) {
                log.debug("Did not get responses for requests {} --> exclude them from pagination", expectedPaginationResponses);
                expectedPaginationResponses.forEach(paginations::remove);
            }

            if (paginations.isEmpty()) {
                log.debug("Finished handling batch request with ID {}", batchRequest.getRequestID());
                batchResponse.getBatchResponses().clear();
                batchResponse.getBatchResponses().addAll(responseMap.values());
                return batchResponse;
            } else {
                log.debug("Create pagination request for request IDs {}", paginations.keySet());
                batchRequest.getBatchRequests().clear();
                for (Map.Entry<String, PagedResultsResponseControl> entry : paginations.entrySet()) {
                    SearchRequest searchRequest = requestMap.get(entry.getKey());
                    ControlUtils.setControl(searchRequest, entry.getValue());
                    batchRequest.getBatchRequests().add(searchRequest);
                }
            }
        }
    }

}
