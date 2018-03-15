package com.parfait.study.autoconfigure;

import com.parfait.study.autoconfigure.logging.filter.RequestParameterLoggingFilter;
import com.parfait.study.autoconfigure.logging.filter.RequestParameterLoggingFilterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(RequestParameterLoggingFilterProperties.class)
public class SampleAutoConfiguration {

    @Autowired
    private RequestParameterLoggingFilterProperties requestParameterLoggingFilterProperties;

    @Bean
    @ConditionalOnProperty(name = "spring.mvc.request-parameter-logging-filter.enabled", havingValue = "true")
    public Filter customUriLoggingFilter() {
        return new RequestParameterLoggingFilter(requestParameterLoggingFilterProperties.getLevel());
    }
}
