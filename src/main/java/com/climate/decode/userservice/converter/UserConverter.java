package com.climate.decode.userservice.converter;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.entity.User;

public interface UserConverter extends Converter<User, UserRequest, UserResponse> {
	
	String[] getNullPropertyNames(Object source);
}
