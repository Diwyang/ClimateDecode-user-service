package com.climate.decode.userservice.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.climate.decode.userservice.dto.UserRequest;
import com.climate.decode.userservice.dto.UserResponse;
import com.climate.decode.userservice.response.ApiResponse;

@RequestMapping("/users")
public interface UserDetailsApi {

	@PostMapping("")
	public ApiResponse<UserResponse> createUserDetails(@RequestBody UserRequest UserDetailsDto);

	@GetMapping("")
	public ApiResponse<List<UserResponse>> getUserDetails();

	@GetMapping("/{userId}")
	public ApiResponse<UserResponse> getUserDetailsById(@PathVariable(value = "userId") Integer userId);

	@DeleteMapping("/{userId}")
	public ApiResponse<String> deleteUserDetails(@PathVariable(value = "userId") Integer userId);

	@PatchMapping("/{userId}")
	public ApiResponse<UserResponse> updateUserDetails(@PathVariable(value = "userId") Integer userId,
			@RequestBody UserRequest UserDetailsDto);
}
