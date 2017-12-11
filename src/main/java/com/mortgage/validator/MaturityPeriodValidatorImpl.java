/**
 * 
 */
package com.mortgage.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.mortgage.domain.MortgageRate;
import com.mortgage.util.MortgagePersistance;
/**
 * @author Arvind
 *
 */
public class MaturityPeriodValidatorImpl implements ConstraintValidator<MaturityPeriodValidator, Integer> {
	
	final static public String message = "The Maturity Period is incorrect.Please choose Maturity period from 10, 15 or 20 years";
	private MaturityPeriodValidator annotation;
	 
    @Override
    public void initialize(MaturityPeriodValidator annotation)
    {
        this.annotation = annotation;
    }
 
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context)
    {
    		for(MortgageRate mortgageRate:MortgagePersistance.mortgageDetails){
    			if(mortgageRate.getMaturityPeriod()== value){
    				return true;			
    			}
    		}
    		return false;
    }
}
