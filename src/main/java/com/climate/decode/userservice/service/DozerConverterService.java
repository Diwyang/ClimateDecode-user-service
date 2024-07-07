package com.climate.decode.userservice.service;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DozerConverterService {

    private final DozerBeanMapper dozerBeanMapper;

    public <T, S> T convert(S source, Class<T> targetClass) {
//      log.info("Source  "+source);
//      log.info("TargetClass   "+targetClass);
    	return dozerBeanMapper.map(source, targetClass);
    }
}
