/**
 * 
 */
package com.mortgage.validator;

/**
 * This is the domain for handling the exception 
 * response.
 * 
 * @author Arvind
 *
 */
public class ErrorResponse {

	private int errorCode;
	private String message;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
