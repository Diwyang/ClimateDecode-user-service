package com.climate.decode.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessLogicException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessLogicException(String message) {
		super(message);

	}
}
