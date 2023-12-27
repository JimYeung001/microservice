package com.eazy.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazy.accounts.constants.AccountsConstants;
import com.eazy.accounts.dto.AccountsDto;
import com.eazy.accounts.dto.CustomerDto;
import com.eazy.accounts.entity.Accounts;
import com.eazy.accounts.entity.Customer;
import com.eazy.accounts.exception.CustomerAlreadyExistsException;
import com.eazy.accounts.exception.ResourceNotFoundException;
import com.eazy.accounts.mapper.AccountsMapper;
import com.eazy.accounts.mapper.CustomerMapper;
import com.eazy.accounts.repository.AccountsRepository;
import com.eazy.accounts.repository.CustomerRepository;
import com.eazy.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (optCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobile number " + customer.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		accountsRepository.save(createNewAccounts(savedCustomer));

	}

	private Accounts createNewAccounts(Customer savedCustomer) {
		Accounts newAccounts = new Accounts();
		newAccounts.setCustomerId(savedCustomer.getCustomerId());
		long accNumber = 10000000000L + new Random().nextInt(900000000);
		newAccounts.setAccountNumber(accNumber);
		newAccounts.setAccountType(AccountsConstants.SAVINGS);
		newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccounts;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Accounts", "CustomerID", customer.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account, new AccountsDto());
		customerDto.setAccounts(accountsDto);
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccounts();
		if (null != accountsDto) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "Account Number",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId).orElseThrow(
					() -> new ResourceNotFoundException("Customer", "Customer number", customerId.toString()));

			CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}

		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
