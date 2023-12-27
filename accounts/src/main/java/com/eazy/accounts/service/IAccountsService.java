package com.eazy.accounts.service;

import com.eazy.accounts.dto.CustomerDto;

public interface IAccountsService {
	/**
	 * 
	 * @param customerDto
	 */
	public void createAccount(CustomerDto customerDto);

}
