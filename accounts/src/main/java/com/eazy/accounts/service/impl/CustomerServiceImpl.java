package com.eazy.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eazy.accounts.service.ICustomerService;
import com.eazy.accounts.service.client.CardsFeignClient;
import com.eazy.accounts.service.client.LoansFeignClient;
import com.eazy.core.dto.AccountsDto;
import com.eazy.core.dto.CardsDto;
import com.eazy.core.dto.CustomerDetailsDto;
import com.eazy.core.dto.LoansDto;
import com.eazy.core.entities.accounts.Accounts;
import com.eazy.core.entities.accounts.Customer;
import com.eazy.core.exception.ResourceNotFoundException;
import com.eazy.core.mapper.AccountsMapper;
import com.eazy.core.mapper.CustomerMapper;
import com.eazy.core.repositories.AccountsRepository;
import com.eazy.core.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "CustomerID", customer.getCustomerId().toString()));

		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,
				new CustomerDetailsDto());
		AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account, new AccountsDto());
		customerDetailsDto.setAccounts(accountsDto);

		ResponseEntity<CardsDto> fetchCard = cardsFeignClient.fetchCard(correlationId,mobileNumber);
		ResponseEntity<LoansDto> fetchLoan = loansFeignClient.fetchLoan(correlationId,mobileNumber);
		customerDetailsDto.setCards(fetchCard.getBody());
		customerDetailsDto.setLoans(fetchLoan.getBody());

		return customerDetailsDto;
	}

}
