/**
 * 
 */
package com.mortgage.util;

import java.util.ArrayList;
import java.util.List;

import com.mortgage.domain.MortgageRate;


/**
 * @author Arvind
 *
 */
public class MortgagePersistance {
	
	/**This static variable list acts as persistence variable in the absence of db */
	public static List<MortgageRate> mortgageDetails = new ArrayList<MortgageRate>();

	/**These are pre-exists bagTypes in the applications*/
	static {
		mortgageDetails.add(new MortgageRate(10, 11.25f,1512913194729L));
		mortgageDetails.add(new MortgageRate(15, 10.25f,1512913183441L));
		mortgageDetails.add(new MortgageRate(20, 9.25f,1512913220058L));
	}
}
