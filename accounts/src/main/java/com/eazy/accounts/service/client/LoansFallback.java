package com.eazy.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazy.core.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
		return null;
	}

}
