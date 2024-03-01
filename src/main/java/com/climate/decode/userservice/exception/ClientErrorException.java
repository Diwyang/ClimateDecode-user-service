package com.climate.decode.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ClientErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClientErrorException(String message) {
		super(message);
	}
}
