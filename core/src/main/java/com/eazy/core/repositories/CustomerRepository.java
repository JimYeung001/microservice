package com.eazy.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazy.core.entities.accounts.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Optional<Customer> findByMobileNumber(String mobileNumber);

}
