/**
 * 
 */
package com.climate.decode.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climate.decode.userservice.entity.User;

/**
 * @author diwya
 *
 */
public interface UserDetailsRepository extends JpaRepository<User, Long>  {

	Optional<User> getByUserId(Integer eventId);
	
}
