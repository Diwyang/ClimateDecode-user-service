package com.climate.decode.userservice.dto;

import com.climate.decode.userservice.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAddress {
	
	@Enumerated(EnumType.STRING)
	@Schema(required = true)
	private AddressType	addressType;
	
	@Schema(required = true)
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	@Schema(required = true)
	private String country;
	
	private String pincode;

}
