package com.climate.decode.userservice.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {

	private Long userId;
	
	private String name;
	
	private String email;
	
	private String status;
	
	private ZonedDateTime createdDateTime;

	private ZonedDateTime updatedDateTime;
	
    private UserRole role;
	
    private List<UserAddress> addresses;
	
    private List<UserMobile> mobiles;
	
}
