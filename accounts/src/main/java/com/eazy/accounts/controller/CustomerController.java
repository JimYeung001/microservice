package com.eazy.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazy.accounts.service.ICustomerService;
import com.eazy.core.dto.CustomerDetailsDto;
import com.eazy.core.dto.ErrorResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@RestController
@Tag(name = "REST API for Customer Resource", description = "REST API for Customer details resource UPDATE, CREATE, DELETE, and FETCH")
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class CustomerController {
	
	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService iCustomerService;

	@Operation(summary = "Fetch Customer Details", description = "REST API to fetch existing Customer details in Eazy")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP status internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
			@RequestHeader("eazy-correlation-id") String correlationId,
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		logger.debug("eazy-correlation-id found: {}", correlationId);
		CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
		return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
	}

}
