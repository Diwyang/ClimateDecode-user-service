package com.climate.decode.userservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.climate.decode.userservice.api.UserDetailsApi;
import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.facade.UserFacade;
import com.climate.decode.userservice.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserDetailsController implements UserDetailsApi {

	private final UserFacade userFacade;
	
	@Override
	public ApiResponse<UserResponse> createUserDetails(UserRequest UserDetailsDto) {
		log.info("UserDetailsController createUserDetails ");
		return userFacade.createUserDetails(UserDetailsDto);
	}

	@Override
	public ApiResponse<List<UserResponse>> getUserDetails() {
		log.info("UserDetailsController getUserDetails ");
		return userFacade.getUserDetails();
	}

	@Override
	public ApiResponse<UserResponse> getUserDetailsById(Long userId) {
		log.info("UserDetailsController getUserDetailsById ");
		return userFacade.getUserDetailsById(userId);
	}

	@Override
	public ApiResponse<String> deleteUserDetails(Long userId) {
		log.info("UserDetailsController deleteUserDetails ");
		return userFacade.deleteUserDetails(userId);
	}

	@Override
	public ApiResponse<UserResponse> updateUserDetails(Long userId, UserRequest UserDetailsDto) {
		log.info("UserDetailsController updateUserDetails ");
		return userFacade.updateUserDetails(userId, UserDetailsDto);
	}

}
