package com.eazy.accounts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazy.accounts.dto.CustomerDto;
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
		// TODO Auto-generated method stub

	}

}
