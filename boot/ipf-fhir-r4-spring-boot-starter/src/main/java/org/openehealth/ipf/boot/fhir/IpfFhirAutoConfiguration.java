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

package org.openehealth.ipf.boot.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.narrative2.NullNarrativeGenerator;
import ca.uhn.fhir.rest.server.*;
import jakarta.servlet.Filter;
import org.hl7.fhir.instance.model.api.IBaseConformance;
import org.openehealth.ipf.boot.atna.IpfAtnaAutoConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.IpfFhirServlet;
import org.openehealth.ipf.commons.ihe.fhir.SpringCachePagingProvider;
import org.openehealth.ipf.commons.ihe.fhir.support.DefaultNamingSystemServiceImpl;
import org.openehealth.ipf.commons.ihe.fhir.support.NamingSystemService;
import org.openehealth.ipf.commons.ihe.fhir.support.NullsafeServerCapabilityStatementProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@Configuration
@AutoConfigureAfter(IpfAtnaAutoConfiguration.class)
@EnableConfigurationProperties(IpfFhirConfigurationProperties.class)
public class IpfFhirAutoConfiguration {

    private final IpfFhirConfigurationProperties config;

    public IpfFhirAutoConfiguration(IpfFhirConfigurationProperties config) {
        this.config = config;
    }

    @Bean
    public FhirContext fhirContext(FhirContextCustomizer fhirContextCustomizer) {
        var fhirContext = new FhirContext(config.getFhirVersion());
        fhirContextCustomizer.customizeFhirContext(fhirContext);
        return fhirContext;
    }

    @Bean
    @ConditionalOnMissingBean(FhirContextCustomizer.class)
    public FhirContextCustomizer fhirContextCustomizer() {
        return FhirContextCustomizer.NOOP;
    }

    @Bean
    @ConditionalOnMissingBean(NamingSystemService.class)
    public NamingSystemService namingSystemService(FhirContext fhirContext) throws IOException {
        var namingSystemService = new DefaultNamingSystemServiceImpl(fhirContext);
        for (var resource : config.getNamingSystems()) {
            try (var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
                namingSystemService.addNamingSystemsFromXml(reader);
            }
        }
        return namingSystemService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "fhirServletRegistration")
    @ConditionalOnWebApplication
    public ServletRegistrationBean<IpfFhirServlet> fhirServletRegistration(IpfFhirServlet camelFhirServlet) {
        var urlMapping = config.getFhirMapping();
        var registration = new ServletRegistrationBean<>(camelFhirServlet, urlMapping);
        var servletProperties = config.getServlet();
        registration.setLoadOnStartup(servletProperties.getLoadOnStartup());
        registration.setName(servletProperties.getName());
        for (var entry : servletProperties.getInit().entrySet()) {
            registration.addInitParameter(entry.getKey(), entry.getValue());
        }
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(IServerConformanceProvider.class)
    public IServerConformanceProvider<IBaseConformance> serverConformanceProvider(RestfulServer restfulServer) {
        return new NullsafeServerCapabilityStatementProvider(restfulServer);
    }

    @Bean
    @ConditionalOnMissingBean(IServerAddressStrategy.class)
    public IServerAddressStrategy serverAddressStrategy() {
        return ApacheProxyAddressStrategy.forHttp();
    }

    @Bean
    @ConditionalOnMissingBean(INarrativeGenerator.class)
    public INarrativeGenerator narrativeGenerator() {
        return new NullNarrativeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(IPagingProvider.class)
    @ConditionalOnProperty(value = "ipf.fhir.caching", havingValue = "true")
    public IPagingProvider pagingProvider(CacheManager cacheManager, FhirContext fhirContext) {
        var servletProperties = config.getServlet();
        var pagingProvider = new SpringCachePagingProvider(cacheManager, fhirContext);
        pagingProvider.setDefaultPageSize(servletProperties.getDefaultPageSize());
        pagingProvider.setMaximumPageSize(servletProperties.getMaxPageSize());
        pagingProvider.setDistributed(servletProperties.isDistributedPagingProvider());
        return pagingProvider;
    }

    @Bean
    @ConditionalOnMissingBean(IpfFhirServlet.class)
    @ConditionalOnWebApplication
    public IpfFhirServlet fhirServlet(
            FhirContext fhirContext,
            ObjectProvider<IPagingProvider> pagingProvider,
            IServerAddressStrategy serverAddressStrategy,
            INarrativeGenerator narrativeGenerator) {
        var fhirServlet = new IpfBootFhirServlet(fhirContext, config.isCaching(), pagingProvider);
        var servletProperties = config.getServlet();
        fhirServlet.setLogging(servletProperties.isLogging());
        fhirServlet.setPrettyPrint(servletProperties.isPrettyPrint());
        fhirServlet.setResponseHighlighting(servletProperties.isResponseHighlighting());
        fhirServlet.setStrictErrorHandler(servletProperties.isStrict());
        fhirServlet.setServerAddressStrategy(serverAddressStrategy);
        fhirServlet.setPagingProviderSize(servletProperties.getPagingRequests());
        fhirServlet.setMaximumPageSize(servletProperties.getMaxPageSize());
        fhirServlet.setDefaultPageSize(servletProperties.getDefaultPageSize());
        if (narrativeGenerator != null) {
            fhirServlet.setNarrativeGenerator(narrativeGenerator);
        }
        // Register server interceptor for ITI-119 if on classpath
        try {
            var clazz = Class.forName("org.openehealth.ipf.commons.ihe.fhir.iti119.MatchGradeEnumInterceptor");
            fhirServlet.registerInterceptor(clazz.getConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            // ok
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException(e);
        }
        return fhirServlet;
    }

    @Bean
    @ConditionalOnMissingBean(name = "corsFilterRegistration")
    @ConditionalOnWebApplication
    public FilterRegistrationBean<Filter> corsFilterRegistration() {
        var frb = new FilterRegistrationBean<>();
        frb.addUrlPatterns(config.getFhirMapping());
        frb.setFilter(new CorsFilter(request -> config.getCors()));
        return frb;
    }

}
