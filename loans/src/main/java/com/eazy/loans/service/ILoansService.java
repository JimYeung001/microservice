package com.eazy.loans.service;

import com.eazy.core.dto.LoansDto;

public interface ILoansService {
	/**
	 * 
	 * @param customerDto
	 */
	public void createLoan(LoansDto loansDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public LoansDto fetchLoan(String mobileNumber);
	
	
	/**
	 * 
	 * @param customerDto
	 * @return
	 */
	public boolean updateLoan(LoansDto loansDto);
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public boolean deleteLoan(String mobileNumber);

}
