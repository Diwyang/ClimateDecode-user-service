package com.climate.decode.userservice.facade;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.entity.User;
import com.climate.decode.userservice.exception.ResourceNotFoundException;
import com.climate.decode.userservice.response.ApiResponse;
import com.climate.decode.userservice.service.DozerConverterService;
import com.climate.decode.userservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final DozerConverterService dozerConverterService;
    
    private final UserService userService;

	
	@Override
	public ApiResponse<UserResponse> createUserDetails(UserRequest userDetailsDto) {
		log.info("UserFacade  createUserDetails");
		User usrReq = dozerConverterService.convert(userDetailsDto, User.class);
		usrReq.setStatus("active");
		usrReq.setCreatedDateTime(ZonedDateTime.now());
		usrReq.setUpdatedDateTime(ZonedDateTime.now());
		User user = userService.createUserDetails(usrReq);
		return ApiResponse.ofSuccess(dozerConverterService.convert(user, UserResponse.class));
	}

	@Override
	public ApiResponse<List<UserResponse>> getUserDetails() {
		log.info("UserFacade  getUserDetails");
		List<User> usrList = userService.getUserDetails();
		List<UserResponse> resList = usrList.stream()
                .map(user -> dozerConverterService.convert(user, UserResponse.class))
                .collect(Collectors.toList());
		return ApiResponse.ofSuccess(resList);
	}

	@Override
	public ApiResponse<UserResponse> getUserDetailsById(Long userId) {
		log.info("UserFacade  getUserDetailsById");
		User user = userService.getUserDetailsById(userId);
		if ( null == user) {
			throw new ResourceNotFoundException("UserId {" + userId + "} Does not exist.");
		}
		return ApiResponse.ofSuccess(dozerConverterService.convert(user, UserResponse.class));
	}

	@Override
	public ApiResponse<String> deleteUserDetails(Long userId) {
		log.info("UserFacade  deleteUserDetails");
		return ApiResponse.ofSuccess(userService.deleteUserDetails(userId));
	}

	@Override
	public ApiResponse<UserResponse> updateUserDetails(Long userId, UserRequest userDetailsDto) {
		log.info("UserFacade  updateUserDetails");
		User user = dozerConverterService.convert(userDetailsDto, User.class);
		user.getRole().setId(user.getUserId());
		user = userService.updateUserDetails(userId, user);
		if ( null == user) {
			throw new ResourceNotFoundException("UserId }" + userId + "Does not exist.");
		}
		return ApiResponse.ofSuccess(dozerConverterService.convert(user, UserResponse.class));
	}

	
}
