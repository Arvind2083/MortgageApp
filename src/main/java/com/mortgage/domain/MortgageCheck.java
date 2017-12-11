/**
 * 
 */
package com.mortgage.domain;

import org.hibernate.validator.constraints.Range;
import com.mortgage.validator.MaturityPeriodValidator;

/**
 * @author Arvind
 *
 */
public class MortgageCheck {

	@Range(min = 0, max = (long) Double.MAX_VALUE, message = "Please select Income in positive number")
	private double income;
	
	@MaturityPeriodValidator(message="The Maturity Period is incorrect.Please choose Maturity period from 10, 15 or 20 years")
	private int maturityPeriod;
	
	@Range(min = 0,max = (long) Double.MAX_VALUE, message = "Please select Income in positive number")
	private double loanValue;
	
	@Range(min = 0,max = (long) Double.MAX_VALUE, message = "Please select Income in positive number")
	private double homeValue;
	/**
	 * @return the income
	 */
	public double getIncome() {
		return income;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(double income) {
		this.income = income;
	}
	/**
	 * @return the maturityPeriod
	 */
	public int getMaturityPeriod() {
		return maturityPeriod;
	}
	/**
	 * @param maturityPeriod the maturityPeriod to set
	 */
	public void setMaturityPeriod(int maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}
	/**
	 * @return the loanValue
	 */
	public double getLoanValue() {
		return loanValue;
	}
	/**
	 * @param loanValue the loanValue to set
	 */
	public void setLoanValue(double loanValue) {
		this.loanValue = loanValue;
	}
	/**
	 * @return the homeValue
	 */
	public double getHomeValue() {
		return homeValue;
	}
	/**
	 * @param homeValue the homeValue to set
	 */
	public void setHomeValue(double homeValue) {
		this.homeValue = homeValue;
	}
	
	
	
}
