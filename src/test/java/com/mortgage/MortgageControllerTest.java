/**
 * 
 */
package com.mortgage;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mortgage.controller.MortgageController;
import com.mortgage.domain.MortgageCheck;
import com.mortgage.domain.MortgageCheckResponse;
import com.mortgage.domain.MortgageRate;
import com.mortgage.response.Response;
import com.mortgage.services.MortgageService;

/**
 * @author Arvind
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = MortgageController.class, secure = false)
public class MortgageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MortgageService mortgageService;
	
	@MockBean 
	private Response response;

	/**
	 * Test method for {@link com.mortgage.controller.MortgageController#getMortgageList()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetMortgageList() throws Exception {
		
		List<MortgageRate> mortgageDetailsMock = new ArrayList<>();
		mortgageDetailsMock.add(new MortgageRate(10, 11.25f,1512913194729L));
		mortgageDetailsMock.add(new MortgageRate(15, 10.25f,1512913183441L));
		mortgageDetailsMock.add(new MortgageRate(20, 9.25f,1512913220058L));
		
		String expectedmortgageDetailsJson = "[\r\n" + 
				"    {\r\n" + 
				"        \"maturityPeriod\": 10,\r\n" + 
				"        \"interestRate\": 11.25,\r\n" + 
				"        \"lastUpdate\": 1512913194729\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"maturityPeriod\": 15,\r\n" + 
				"        \"interestRate\": 10.25,\r\n" + 
				"        \"lastUpdate\": 1512913183441\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"maturityPeriod\": 20,\r\n" + 
				"        \"interestRate\": 9.25,\r\n" + 
				"        \"lastUpdate\": 1512913220058\r\n" + 
				"    }\r\n" + 
				"]";
		
		Mockito.when(mortgageService.getMortgageList()).thenReturn(mortgageDetailsMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/interest-rate").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			JSONAssert.assertEquals(expectedmortgageDetailsJson, result.getResponse()
					.getContentAsString(), false);

		
	}

	/**
	 * Test method for {@link com.mortgage.controller.MortgageController#checkMortgageValidity(com.mortgage.domain.MortgageCheck, org.springframework.validation.BindingResult)}.
	 * @throws Exception 
	 */
	@Test
	public void testCheckMortgageValidity() throws Exception {
		
		String mockJsonRequest = "{\r\n" + 
				"\"income\": \"1000\", \r\n" + 
				"\"maturityPeriod\": \"20\",\r\n" + 
				"\"loanValue\":\"2\",\r\n" + 
				"\"homeValue\":\"30000\"\r\n" + 
				"}";
		
		String expectedString = "{\r\n" + 
				"    \"feasible\": false,\r\n" + 
				"    \"monthlyMortgage\": 370\r\n" + 
				"}";
		
		MortgageCheck mortgageCheck = new MortgageCheck();
		mortgageCheck.setHomeValue(30000);
		mortgageCheck.setIncome(1000);
		mortgageCheck.setLoanValue(2);
		mortgageCheck.setMaturityPeriod(20);
		
		MortgageCheckResponse mortgageCheckResponse = new MortgageCheckResponse(); 
		mortgageCheckResponse.setFeasible(false);
		mortgageCheckResponse.setMonthlyMortgage(370);
		
		Mockito.when(mortgageService.getMortgageCheckResponse(Mockito.any(MortgageCheck.class))).thenReturn(mortgageCheckResponse);
		
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mortgage-check").accept(MediaType.APPLICATION_JSON)
        		.param("income", "1000")
        		.param("maturityPeriod", "20")
        		.param("loanValue", "2")
        		.param("homeValue", "30000");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	    JSONAssert.assertEquals(expectedString, result.getResponse()
					.getContentAsString(), false);
		
	    
	}

}
