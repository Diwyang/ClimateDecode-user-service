package com.climate.decode.userservice.converter;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final GenericMapper mapper;

    @Override
    public User toEntity(UserRequest dto) {
        return mapper.convert(dto, User.class, user ->{
        	user.setName(dto.getName());
        	user.setEmail(dto.getEmail());
        	user.setUserId(dto.getUserId());
        	user.setCreatedDateTime(dto.getCreatedDateTime());
        	user.setUpdatedDateTime(dto.getUpdatedDateTime());
        	return user;
        }
        		);
    }
    
    @Override
    public UserResponse toDto(User entity) {
        return mapper.convert(entity, UserResponse.class, userRes -> {
        	userRes.setName(entity.getName());
        	userRes.setEmail(entity.getEmail());
        	userRes.setUserId(entity.getUserId());
        	userRes.setCreatedDateTime(entity.getCreatedDateTime());
        	userRes.setUpdatedDateTime(entity.getUpdatedDateTime());
        	return userRes;
        });
    }

    @Override
    public User updateEntity(User entity, UserRequest dto) {
    	return entity;
    }

    @Override
    public User patchEntity(User entity, UserRequest dto) {
        return null;
    }
    
    @Override
    public String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }
}
