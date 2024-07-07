package com.climate.decode.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.climate.decode.userservice.entity.User;
import com.climate.decode.userservice.entity.UserAddress;
import com.climate.decode.userservice.entity.UserMobile;
import com.climate.decode.userservice.repository.UserDetailsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDetailsRepository repo;
	
	@Override
	public User createUserDetails(User user) {
		log.info("UserService >> createUserDetails" );
		// Ensure the Person entity is associated with its Addresses and Mobiles
        for (UserAddress address : user.getUserAddress()) {
            address.setUser(user);
        }
        for (UserMobile mobile : user.getUserMobile()) {
            mobile.setUser(user);
        }
        
        user.getRole().setUser(user);
		return repo.save(user);
	}

	@Override
	public List<User> getUserDetails() {
		log.info("UserService >> getUserDetails" );
		return repo.findAll();
	}

	@Override
	public User getUserDetailsById(Long userId) {
		log.info("UserService >> getUserDetailsById" );
		Optional<User> user = repo.findById(userId);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public String deleteUserDetails(Long userId) {
		log.info("UserService >> deleteUserDetails" );
		repo.deleteById(userId);
		return "Deleted SuccessFully";
	}

	@Override
	public User updateUserDetails(Long userId, User user) {
		log.info("UserService >> updateUserDetails" );
		Optional<User> user1 = repo.findById(userId);
		 return user1.isPresent() ? repo.save(user) : null;
	}

	
}
