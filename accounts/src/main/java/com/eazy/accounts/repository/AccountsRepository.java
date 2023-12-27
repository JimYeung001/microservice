package com.eazy.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.eazy.accounts.entity.Accounts;

import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

	public Optional<Accounts> findByCustomerId(Long customerId);

	@Transactional
	@Modifying
	public void deleteByCustomerId(Long customerId);

}
