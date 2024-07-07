package com.climate.decode.userservice.facade;

import java.util.List;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.response.ApiResponse;

public interface UserFacade {

	public ApiResponse<UserResponse> createUserDetails(UserRequest UserDetailsDto);

	public ApiResponse<List<UserResponse>> getUserDetails();

	public ApiResponse<UserResponse> getUserDetailsById(Long userId);

	public ApiResponse<String> deleteUserDetails(Long userId);

	public ApiResponse<UserResponse> updateUserDetails(Long userId, UserRequest UserDetailsDto);

}
