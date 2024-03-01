package com.climate.decode.userservice.entity;

import java.time.ZonedDateTime;
import java.util.Set;

import com.climate.decode.userservice.dto.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id", updatable = false, nullable = false)
	private String userId;
	
	@Column(name = "name", updatable = true, nullable = false)
	private String name;
	
	@Column(name = "email", updatable = true, nullable = false)
	private String email;
	
	@Column(name = "status", updatable = true, nullable = false)
	private String status;
	
	@Column(name = "created_date", updatable = false, nullable = false)
	private ZonedDateTime createdDateTime;
	
	@Column(name = "updated_date", updatable = false, nullable = false)
	private ZonedDateTime updatedDateTime;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private Set<UserRole> role;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private Set<UserMobile> userMobile;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private Set<UserAddress> userAddress;
	
}
