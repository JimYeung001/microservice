package com.eazy.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazy.core.entities.loans.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
	
	public Optional<Loans> findByMobileNumber(String mobileNumber);
	
	public Optional<Loans> findByLoanNumber(String loanNumber);

}
