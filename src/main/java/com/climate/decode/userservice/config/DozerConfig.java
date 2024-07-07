package com.climate.decode.userservice.config;

import java.util.Collections;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        // This will automatically load the "dozer-config.xml" from the classpath
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("dozer-mapping.xml"));
        return mapper;
    }
}

