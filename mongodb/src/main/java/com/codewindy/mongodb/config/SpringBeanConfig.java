package com.codewindy.mongodb.config;

import com.codewindy.mongodb.utils.SpringBeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
    @Bean
    public SpringBeanUtils springBeanUtil() {
        return new SpringBeanUtils();
    }
}
