package com.eazy.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazy.core.dto.LoansDto;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

	@GetMapping(value = "/api/fetch", consumes = "application/json")
	public ResponseEntity<LoansDto> fetchLoan(@RequestHeader("eazy-correlation-id") String correlationId,
			@RequestParam String mobileNumber);
}
