package com.climate.decode.userservice.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {
	@Bean
	@Primary
	public Mapper getDozerMapper() {
		List<String> mappingFiles = new ArrayList<String>();
		mappingFiles.add("dozerJdk8Converters.xml");
		mappingFiles.add("dozer/dozer_mapping.xml");
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.setMappingFiles(mappingFiles);
		return dozerBeanMapper;
	}
}
