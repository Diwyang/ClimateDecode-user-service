package com.climate.decode.userservice.facade;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;

public interface UserFacade {

	public UserResponse createUser(UserRequest userRequest);
	
	public UserResponse ListUsers();
	
	public UserResponse getUserById(Integer userId);
	
	public String deleteUserById(Integer userId);
	
	public UserResponse updateUserById(Integer userId);
	
}
