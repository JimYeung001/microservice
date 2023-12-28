package com.eazy.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazy.core.constants.ApplicationConstants;
import com.eazy.core.dto.LoansDto;
import com.eazy.core.entities.accounts.Accounts;
import com.eazy.core.entities.accounts.Customer;
import com.eazy.core.entities.loans.Loans;
import com.eazy.core.exception.CustomerAlreadyExistsException;
import com.eazy.core.exception.ResourceNotFoundException;
import com.eazy.core.mapper.LoansMapper;
import com.eazy.core.repositories.AccountsRepository;
import com.eazy.core.repositories.CustomerRepository;
import com.eazy.core.repositories.LoansRepository;
import com.eazy.loans.service.ILoansService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoansRepository loansRepository;

	@Override
	public void createLoan(LoansDto loansDto) {
		Loans loan = LoansMapper.mapToLoans(loansDto, new Loans());
		Optional<Loans> optLoan = loansRepository.findByMobileNumber(loan.getMobileNumber());
		if (optLoan.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobile number " + optLoan.get().getMobileNumber());
		}
		loansRepository.save(loan);
	}

	private Accounts createNewAccounts(Customer savedCustomer) {
		Accounts newAccounts = new Accounts();
		newAccounts.setCustomerId(savedCustomer.getCustomerId());
		long accNumber = 10000000000L + new Random().nextInt(900000000);
		newAccounts.setAccountNumber(accNumber);
		newAccounts.setAccountType(ApplicationConstants.SAVINGS);
		newAccounts.setBranchAddress(ApplicationConstants.ADDRESS);
		return newAccounts;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		Loans loan = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));

		LoansDto loansDto = LoansMapper.mapToLoansDto(loan, new LoansDto());
		return loansDto;
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		boolean isUpdated = false;
		if (null != loansDto) {
			Loans loan = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Loan", "mobile Number", loansDto.getMobileNumber()));
			LoansMapper.mapToLoans(loansDto, loan);
			loansRepository.save(loan);
			isUpdated = true;
		}

		return isUpdated;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loan = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile Number", mobileNumber));
		loansRepository.deleteById(loan.getLoanId());
		return true;
	}

}
