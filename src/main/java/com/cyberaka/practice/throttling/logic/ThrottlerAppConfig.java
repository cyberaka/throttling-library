package com.cyberaka.practice.throttling.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * We need to register our throttler interceptor through this class.
 */
@Configuration
public class ThrottlerAppConfig implements WebMvcConfigurer {

    @Autowired
    ThrottlingInterceptor throttlingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(throttlingInterceptor);
    }
}
