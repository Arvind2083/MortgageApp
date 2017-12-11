package com.mortgage.domain;

/**
 * This is the class will return the Get List response.
 * 
 * @author Arvind
 *
 */
public class MortgageRate {
	
	private int maturityPeriod;
	private float interestRate;
	private long lastUpdate;
	
	
	public MortgageRate(int maturityPeriod, float interestRate, long lastUpdate) {
		this.maturityPeriod = maturityPeriod;
		this.interestRate = interestRate;
		this.lastUpdate = lastUpdate;
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
	 * @return the interestRate
	 */
	public float getInterestRate() {
		return interestRate;
	}


	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}


	/**
	 * @return the lastUpdate
	 */
	public long getLastUpdate() {
		return lastUpdate;
	}


	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	

}
