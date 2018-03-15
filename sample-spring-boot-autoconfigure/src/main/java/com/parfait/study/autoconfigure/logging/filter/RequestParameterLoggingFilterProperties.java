package com.parfait.study.autoconfigure.logging.filter;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.mvc.request-parameter-logging-filter")
public class RequestParameterLoggingFilterProperties {
    boolean enabled;
    Level level;
}

