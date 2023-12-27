package com.eazy.accounts.service;

import com.eazy.core.dto.CustomerDto;

public interface IAccountsService {
	/**
	 * 
	 * @param customerDto
	 */
	public void createAccount(CustomerDto customerDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public CustomerDto fetchAccount(String mobileNumber);
	
	
	/**
	 * 
	 * @param customerDto
	 * @return
	 */
	public boolean updateAccount(CustomerDto customerDto);
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public boolean deleteAccount(String mobileNumber);

}
