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
import com.eazy.core.exception.LoanAlreadyExistsException;
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
			throw new LoanAlreadyExistsException(
					"Loan already registered with given mobile number " + optLoan.get().getMobileNumber());
		}
		loansRepository.save(createNewLoans(loan.getMobileNumber()));
	}

	private Loans createNewLoans(String mobileNumber) {
		Loans loan = new Loans();
		long accNumber = 100000000000L + new Random().nextInt(900000000);
		loan.setLoanNumber(Long.toString(accNumber));
		loan.setMobileNumber(mobileNumber);
		loan.setLoanType(ApplicationConstants.HOME_LOAN);
		loan.setAmountPaid(0);
		loan.setOutstandingAmount(ApplicationConstants.NEW_LOAN_LIMIT);
		loan.setTotalLoan(ApplicationConstants.NEW_LOAN_LIMIT);
		return loan;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		Loans loan = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));

		return LoansMapper.mapToLoansDto(loan, new LoansDto());
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		boolean isUpdated = false;
		if (null != loansDto) {
			Loans loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Loan", "loan Number", loansDto.getLoanNumber()));
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
