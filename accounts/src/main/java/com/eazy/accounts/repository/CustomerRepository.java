package com.eazy.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazy.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Optional<Customer> findByMobileNumber(String mobileNumber);

}
