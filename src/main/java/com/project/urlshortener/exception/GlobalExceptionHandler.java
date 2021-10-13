package com.project.urlshortener.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.urlshortener.common.RestApiResponse;

/**
 * @author mayurpatil
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidUrlException.class)
	protected ResponseEntity<Object> handleTestDataStorageException(InvalidUrlException e) {
		RestApiResponse apiError = new RestApiResponse(e.getErrorCode(), e.getHttpStatusCode(), e.getErrorMessage());
		return buildResponseEntity(apiError);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String errorMessage = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.findFirst()
				.orElse(ex.getMessage());
		RestApiResponse apiError = new RestApiResponse(400, HttpStatus.BAD_REQUEST, errorMessage);
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(RestApiResponse error) {
		return new ResponseEntity<>(error, error.getStatus());
	}
}