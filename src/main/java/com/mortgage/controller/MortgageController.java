package com.mortgage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.domain.MortgageCheck;
import com.mortgage.domain.MortgageCheckResponse;
import com.mortgage.domain.MortgageRate;
import com.mortgage.exception.ValidationException;
import com.mortgage.response.Response;
import com.mortgage.services.MortgageService;
import com.mortgage.validator.ErrorResponse;

/**
 * @author Arvind
 *
 */
@RestController
public class MortgageController {

	private final static Logger LOGGER = Logger.getLogger(MortgageController.class);

	/**
	 * This is DI injection for the service layer.
	 */
	@Autowired
	private MortgageService mortgageService;

	/**
	 * This injection will carry the Post response.
	 */
	@Autowired
	private Response apiResponse;

	/**
	 * This method will return list of Potato bags. By default this method will
	 * return only 3 bag details.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/interest-rate")
	public @ResponseBody List<MortgageRate> getMortgageList() {
		/** Call to service layer to return the potato Bags */
		return mortgageService.getMortgageList();
	}

	/**
	 * This method will persist a new bag.
	 * 
	 * @throws ValidationException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/mortgage-check")
	public ResponseEntity<MortgageCheckResponse> checkMortgageValidity(@Valid MortgageCheck mortgageCheck,
			BindingResult bindingResult) throws ValidationException {

		List<String> errorMessages = new ArrayList<>();
		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach(error -> {
				errorMessages.add(error.getDefaultMessage());
			});
			throw new ValidationException(errorMessages.toString());
		}

		/** Call to service layer to validate the Mortgage */
		MortgageCheckResponse mortgageCheckResponse = mortgageService.getMortgageCheckResponse(mortgageCheck);
		return new ResponseEntity<MortgageCheckResponse>(mortgageCheckResponse, HttpStatus.ACCEPTED);
	}

	/**
	 * This is the expection handler for all the Field level validation
	 * for the post request
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.PRECONDITION_FAILED);

	}

}
