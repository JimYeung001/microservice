package com.eazy.accounts.service;

import com.eazy.core.dto.CustomerDetailsDto;

public interface ICustomerService {

	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
