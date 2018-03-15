package com.parfait.study.autoconfigure.logging.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.event.Level.*;

@Slf4j
public class RequestParameterLoggingFilter implements Filter {
    private Level level;

    public RequestParameterLoggingFilter(Level level) {
        this.level = level;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String level = filterConfig.getInitParameter("level");
        if (level != null) {
            this.level = Level.valueOf(level);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getParameterMap()
                            .entrySet()
                            .stream()
                            .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                            .flatMap(Stream::of)
                            .collect(Collectors.joining("&"));
        log(uri);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void log(String uri) {
        if (level == ERROR) {
            log.error("uri : {}", uri);
        } else if (level == WARN) {
            log.warn("uri : {}", uri);
        } else if (level == INFO) {
            log.info("uri : {}", uri);
        } else if (level == DEBUG) {
            log.debug("uri : {}", uri);
        } else if (level == TRACE) {
            log.trace("uri : {}", uri);
        } else {
            throw new IllegalStateException("level : " + level);
        }
    }
}

