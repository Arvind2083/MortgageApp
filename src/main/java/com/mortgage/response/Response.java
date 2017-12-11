/**
 * 
 */
package com.mortgage.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mortgage.domain.MortgageCheckResponse;

/**
 * This is the response class for the POST response.
 * It will have a message in case of any validation failure.
 * 
 * @author Arvind
 *
 */
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	/**
	 * This list will contains all the validation failures
	 * for the POST request. 
	 */
	private  List<String> message;
	
	/**
	 * This Object will have the response for the POST request. 
	 */
	private MortgageCheckResponse mortgageCheckResponse;
	
	
	/**
	 * @return the message
	 */
	public List<String> getMessage() {
		return message;
	}

	/**
	 * @return the mortgageCheckResponse
	 */
	public MortgageCheckResponse getMortgageCheckResponse() {
		return mortgageCheckResponse;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(List<String> message) {
		this.message = message;
	}

	/**
	 * @param mortgageCheckResponse the mortgageCheckResponse to set
	 */
	public void setMortgageCheckResponse(MortgageCheckResponse mortgageCheckResponse) {
		this.mortgageCheckResponse = mortgageCheckResponse;
	}

}
