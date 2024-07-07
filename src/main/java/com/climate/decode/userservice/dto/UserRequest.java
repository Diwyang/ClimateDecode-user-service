package com.climate.decode.userservice.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {

	private Long userId;
	
	@Schema(required = true)
	private String name;
	
	@Schema(required = true)
	private String email;
	
	@Schema(required = true)
	private String status;
	
	private ZonedDateTime createdDateTime;
	
	private ZonedDateTime updatedDateTime;
	
	@Schema(required = true)
    private UserRole role;
	
	@Schema(required = true)
    private List<@Valid UserAddress> addresses;
	
	@Schema(required = true)
    private List<@Valid UserMobile> mobiles;
	
}
