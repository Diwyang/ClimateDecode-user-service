package com.climate.decode.userservice.service;

import java.util.List;

import com.climate.decode.userservice.entity.User;

public interface UserService {

	public User createUserDetails(User user);

	public List<User> getUserDetails();

	public User getUserDetailsById(Long userId);

	public String deleteUserDetails(Long userId);

	public User updateUserDetails(Long userId, User user);

}
