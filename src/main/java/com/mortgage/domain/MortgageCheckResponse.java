package com.mortgage.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This is the response embedded for the POST request.
 * @author Arvind
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MortgageCheckResponse {
	
	/**
	 * This attribute will check the eligibility of the mortgage.
	 */
	private boolean feasible;
	
	/**
	 * This attribute contains the monthly mortgage.
	 */
	private double monthlyMortgage;
	
	/**
	 * @return the feasible
	 */
	public boolean isFeasible() {
		return feasible;
	}
	/**
	 * @param feasible the feasible to set
	 */
	public void setFeasible(boolean mortgagePossible) {
		this.feasible = mortgagePossible;
	}
	/**
	 * @return the monthlyMortgage
	 */
	public double getMonthlyMortgage() {
		return monthlyMortgage;
	}
	/**
	 * @param monthlyMortgage the monthlyMortgage to set
	 */
	public void setMonthlyMortgage(double monthlyMortgage) {
		this.monthlyMortgage = monthlyMortgage;
	}
	
	

}
