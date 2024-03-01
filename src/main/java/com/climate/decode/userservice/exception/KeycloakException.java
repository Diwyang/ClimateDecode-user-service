package com.climate.decode.userservice.exception;

import org.springframework.http.HttpStatus;

public class KeycloakException extends RuntimeException{
    String message;
    String details;
    HttpStatus httpStatus;

    public KeycloakException(String message, String details, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.details = details;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDetails() {
        return details;
    }
}
