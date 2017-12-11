package com.mortgage.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mortgage.domain.MortgageCheck;
import com.mortgage.domain.MortgageCheckResponse;
import com.mortgage.domain.MortgageRate;
import com.mortgage.util.MortgagePersistance;

/**
 * This is the class provide the implementaion of service interface.Mainly it
 * deals with GET and POST response to the controller layer. And also executes
 * the business logic.
 * 
 * @author Arvind
 */

@Service
public class MortgageServiceImpl implements MortgageService {

	private final static Logger LOGGER = Logger.getLogger(MortgageServiceImpl.class);

	/**
	 * This method will return the list of current interest rates.
	 * 
	 * @return List
	 */
	@Override
	public List<MortgageRate> getMortgageList() {
		return MortgagePersistance.mortgageDetails;
	}

	/**
	 * This method will populate MortgageCheckResponse object and send it back
	 * to controller.
	 * 
	 * @param mortgageCheck
	 * @return MortgageCheckResponse
	 */
	@Override
	public MortgageCheckResponse getMortgageCheckResponse(MortgageCheck mortgageCheck) {

		MortgageCheckResponse mortgageCheckResponse = null;

		try {
			/**
			 * Get the valid interest based upon the maturity period
			 */
			float interestRate = getInterestRate(mortgageCheck);
			/**
			 * Calculate mortage: Assuming the formula : loanValue * interest *
			 * maturity period * 12
			 */
			double totalMortgage = mortgageCheck.getLoanValue() * interestRate * mortgageCheck.getMaturityPeriod() * 12;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Total value of the mortage :" + totalMortgage);
			}
			mortgageCheckResponse = new MortgageCheckResponse();

			/** Check if Mortgage is greater than 4 times income */
			boolean isMortgageMoreThanIncome = mortgageMoreThanIncome(mortgageCheck, totalMortgage);
			if (!isMortgageMoreThanIncome) {

				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Mortgage :" + totalMortgage + " is greater than Income Amount Income :"
							+ mortgageCheck.getIncome());
				}

				mortgageCheckResponse.setFeasible(false);
				mortgageCheckResponse.setMonthlyMortgage(getMonthlyMortgage(mortgageCheck));
				return mortgageCheckResponse;
			}

			/** Check if Mortgage is greater than home Amount */
			boolean isMortgageMoreHomeAmount = mortgageMoreHomeAmount(mortgageCheck, totalMortgage);
			if (!isMortgageMoreHomeAmount) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Mortgage :" + totalMortgage + " greater than Home Amount :"
							+ mortgageCheck.getHomeValue());
				}
				mortgageCheckResponse.setFeasible(false);
				mortgageCheckResponse.setMonthlyMortgage(getMonthlyMortgage(mortgageCheck));
				return mortgageCheckResponse;
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Mortgage :" + totalMortgage + " less than Home Amount :" + mortgageCheck.getHomeValue()
						+ " and Income :" + mortgageCheck.getIncome());
			}

			mortgageCheckResponse.setFeasible(true);
			mortgageCheckResponse.setMonthlyMortgage(getMonthlyMortgage(mortgageCheck));

		} catch (Exception e) {
			LOGGER.error("Error occured during getMortgageCheckResponse" + e.getMessage());
		}
		return mortgageCheckResponse;
	}

	/**
	 * This method will check if the mortage amount is greater than 4 time
	 * Income amount.
	 * 
	 * @param mortgageCheck
	 * @param totalMortgage
	 * @return
	 */
	private boolean mortgageMoreThanIncome(MortgageCheck mortgageCheck, double totalMortgage) {
		if (totalMortgage < 4 * mortgageCheck.getIncome()) {
			return true;
		}
		return false;
	}

	/**
	 * This method will check if the mortgage amount is greater than home
	 * amount.
	 * 
	 * @param mortgageCheck
	 * @param totalMortgage
	 * @return
	 */
	private boolean mortgageMoreHomeAmount(MortgageCheck mortgageCheck, double totalMortgage) {
		if (totalMortgage < mortgageCheck.getHomeValue()) {
			return true;
		}
		return false;
	}

	/**
	 * This method will determine the value interest rate based upon the
	 * maturity period set in the db.
	 * 
	 * @param mortgageCheck
	 * @return
	 */
	private float getInterestRate(MortgageCheck mortgageCheck) {
		float interestRate = 0;
		/** Get the maturity period */
		for (MortgageRate mortgageRate : MortgagePersistance.mortgageDetails) {
			if (mortgageRate.getMaturityPeriod() == mortgageCheck.getMaturityPeriod()) {
				interestRate = mortgageRate.getInterestRate();
			}
		}
		return interestRate;
	}

	/**
	 * This method will calculate the monthly cost based upon the interest
	 * rate,maturity and loan value.
	 * 
	 * @param mortgageCheck
	 * @return
	 */
	private double getMonthlyMortgage(MortgageCheck mortgageCheck) {
		return (getInterestRate(mortgageCheck) / 12) * (mortgageCheck.getMaturityPeriod() * 12)
				* mortgageCheck.getLoanValue();
	}
}
