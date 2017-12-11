package com.mortgage.exception;

import java.util.List;

/**
 * This custom validation will handle the field validation errors.
 * 
 * @author Arvind
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public ValidationException(String errorMessage) {

		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public ValidationException(List<String> errorMessageList) {

		this.errorMessageList = errorMessageList;
	}

	/**
	 * @return the errorMessageList
	 */
	public List<String> getErrorMessageList() {
		return errorMessageList;
	}

	/**
	 * @param errorMessageList
	 *            the errorMessageList to set
	 */
	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	public ValidationException() {
		super();
	}

	private String errorMessage;

	private List<String> errorMessageList;

	public String getErrorMessage() {

		return errorMessage;
	}

}
