package com.climate.decode.userservice.response;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@ToString
@Builder
@AllArgsConstructor
@JsonPropertyOrder({ "data", "errors", "status_code", "status_message", "timestamp" })
public class ApiResponse<T> {

	@JsonProperty("data")
	private T body;

	@JsonProperty("errors")
	private T error;

	@JsonProperty("status_code")
	@NonNull
	private ResponseStatus status;

	@JsonProperty("status_message")
	private String statusMessage;

	@JsonProperty("timestamp")
	@NonNull
	@JsonSerialize(using = ZonedDateTimeDeserializer.class)
	private final ZonedDateTime timestamp = ZonedDateTime.now();

	public static <T> ApiResponse<T> ofSuccess(T body) {
		return ApiResponse.<T>builder().body(body).status(ResponseStatus.SUCCESS).statusMessage("Success").build();
	}

	public static <T> ApiResponse<T> ofSuccess(T body, String message) {
		return ApiResponse.<T>builder().body(body).status(ResponseStatus.SUCCESS).statusMessage(message).build();
	}

	public static <T> ApiResponse<T> ofError(T error) {
		return ApiResponse.<T>builder().error(error).status(ResponseStatus.CLIENT_INVALID_PARAMETERS)
				.statusMessage("Error").build();
	}

	public static ApiResponse<?> ofEmpty() {
		return ApiResponse.builder().body(null).status(ResponseStatus.SUCCESS).statusMessage("Success").build();
	}

	@Builder
	@Getter
	@Setter
	public static class FieldError {
		private String field;
		private String message;
	}

	@Builder
	@Getter
	@Setter
	public static class ParamError {
		private String param;
		private String message;
	}
}
