package com.eazy.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazy.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
