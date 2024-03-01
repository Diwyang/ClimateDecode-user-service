/**
 * 
 */
package com.climate.decode.userservice.exception;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.climate.decode.userservice.response.ApiResponse;
import com.climate.decode.userservice.response.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final MessageSource messageSource;
	private static final String INVALID_PARAM_ERR_MSG = "Missing or invalid parameters.";
	private static final String GENERIC_ERR_MSG = "We had a problem processing your request, please try again later.";
	private static final String ERR_MSG = "Error Occurred";
	private static final String EXCEPTION_CLASS = "Exception Class";

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		logException(ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ApiResponse.ofError(ex.getMessage()));

	}

	@ExceptionHandler(BusinessLogicException.class)
	public ResponseEntity<?> businessLogicException(BusinessLogicException ex, WebRequest request) {
		logException(ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body(ApiResponse.ofError(ex.getMessage()));

	}

	@ExceptionHandler(KeycloakException.class)
	public ResponseEntity<?> walletException(KeycloakException ex, WebRequest request) {
		logException(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.ofError(ex.getMessage()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
		logException(ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.ofError(ex.getMessage()));
	}

	@ExceptionHandler(ClientErrorException.class)
	public ResponseEntity<?> clientErrorException(ClientErrorException ex, WebRequest request) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(ApiResponse.ofError(ex.getMessage()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse onConstraintViolationException(ConstraintViolationException exception, WebRequest request)
			throws IOException {
		logException(exception);
		return processConstraintViolationErrors(exception, request);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse onHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request)
			throws IOException {
		logException(exception);
		return ApiResponse
				.builder().status(ResponseStatus.CLIENT_ERROR).error(exception.getMessage()).statusMessage(messageSource
						.getMessage("validation.missing.parameters", null, INVALID_PARAM_ERR_MSG, request.getLocale()))
				.build();
	}

	@ExceptionHandler(TypeMismatchException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse onTypeMismatchException(TypeMismatchException exception, WebRequest request) throws IOException {
		logException(exception);
		List<ApiResponse.ParamError> errorList = new ArrayList<>();
		String paramName;
		if (exception instanceof MethodArgumentTypeMismatchException) {
			paramName = ((MethodArgumentTypeMismatchException) exception).getName();
		} else {
			paramName = exception.getPropertyName();
		}
		String type = Objects.requireNonNull(exception.getRequiredType()).getSimpleName();
		String errorMessage = "Must be an " + type.toLowerCase() + ".";
		ApiResponse.ParamError paramError = ApiResponse.ParamError.builder().param(paramName).message(errorMessage)
				.build();
		errorList.add(paramError);
		ApiResponse errorRes = ApiResponse
				.builder().status(ResponseStatus.CLIENT_ERROR).statusMessage(messageSource
						.getMessage("validation.missing.parameters", null, INVALID_PARAM_ERR_MSG, request.getLocale()))
				.build();
		errorRes.setError(errorList);
		return errorRes;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ApiResponse onMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request)
			throws IOException {
		logException(exception);
		BindingResult result = exception.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors, request);
	}

	private ApiResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors,
			WebRequest request) {
		ApiResponse errorRes = ApiResponse
				.builder().status(ResponseStatus.CLIENT_ERROR).statusMessage(messageSource
						.getMessage("validation.missing.parameters", null, INVALID_PARAM_ERR_MSG, request.getLocale()))
				.build();
		List<ApiResponse.FieldError> errorList = new ArrayList<>();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			ApiResponse.FieldError paramError = ApiResponse.FieldError.builder().field(fieldError.getField())
					.message(fieldError.getDefaultMessage()).build();
			errorList.add(paramError);
		}
		errorRes.setError(errorList);
		return errorRes;
	}

	private ApiResponse processConstraintViolationErrors(ConstraintViolationException exception, WebRequest request) {
		logException(exception);
		ApiResponse errorRes = ApiResponse
				.builder().status(ResponseStatus.CLIENT_ERROR).statusMessage(messageSource
						.getMessage("validation.missing.parameters", null, INVALID_PARAM_ERR_MSG, request.getLocale()))
				.build();
		List<ApiResponse.ParamError> errorList = new ArrayList<>();
		for (final ConstraintViolation<?> violation : exception.getConstraintViolations()) {
			String paramName = violation.getPropertyPath().toString().split("\\.")[1];
			String errorMessage = violation.getMessage();
			ApiResponse.ParamError paramError = ApiResponse.ParamError.builder().param(paramName).message(errorMessage)
					.build();
			errorList.add(paramError);
		}
		errorRes.setError(errorList);
		return errorRes;
	}

	@ExceptionHandler({ Exception.class })
	@org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResponse handleAll(Exception exception, WebRequest request) {
		logException(exception);
		return ApiResponse.ofError(
				messageSource.getMessage("validation.missing.parameters", null, GENERIC_ERR_MSG, request.getLocale()));
	}

	private void logException(Exception ex) {
		ex.printStackTrace();
		log.error("{}={}", EXCEPTION_CLASS, ex.getClass().getName());
		log.error("{}={}", ERR_MSG, ex.getMessage());
	}

}