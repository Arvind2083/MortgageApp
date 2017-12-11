package com.mortgage.services;

import java.util.List;

import com.mortgage.domain.MortgageCheck;
import com.mortgage.domain.MortgageCheckResponse;
import com.mortgage.domain.MortgageRate;

/**
 * This is the interface for the service layer having 
 * all the logic.This interface will help the controller 
 * to display the GET and POST details to apis/
 * 
 * @author Arvind
 */
public interface MortgageService {

	/**
	 * This method will return predefined interest rates 
	 * to the Get request in the controller
	 */
	List<MortgageRate> getMortgageList();

	/**
	 * This method will return the populated mortgage check response
	 * to the Post method of controller.
	 * 
	 * @param mortgageCheck
	 * @return
	 */
	MortgageCheckResponse getMortgageCheckResponse(MortgageCheck mortgageCheck);

}
