package com.project.urlshortener.exception;

import org.springframework.http.HttpStatus;

/**
 * @author mayurpatil
 */

public class InvalidUrlException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatusCode;
	private int errorCode;
	private String errorMessage;

	public InvalidUrlException(final String message) {
		super(message);
		this.errorMessage = message;
	}

	public InvalidUrlException(final String message, final Throwable cause) {
		super(message, cause);
		this.errorMessage = message;
	}

	public InvalidUrlException(int errorCode, HttpStatus httpStatusCode, final String message, final Throwable cause) {
		super(message, cause);
		this.setHttpStatusCode(httpStatusCode);
		this.setErrorCode(errorCode);
		this.errorMessage = message;
	}

	public InvalidUrlException(int errorCode, HttpStatus httpStatusCode, final String message) {
		super(message);
		this.setHttpStatusCode(httpStatusCode);
		this.setErrorCode(errorCode);
		this.errorMessage = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(HttpStatus httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		if (httpStatusCode != null)
			return errorMessage + " (Status Code: " + httpStatusCode.toString() + "; Error Code: " + getErrorCode()
					+ ")";
		return errorMessage;
	}

}
