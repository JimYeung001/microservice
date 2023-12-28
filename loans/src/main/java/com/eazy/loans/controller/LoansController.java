package com.eazy.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazy.core.constants.ApplicationConstants;
import com.eazy.core.dto.ErrorResponseDto;
import com.eazy.core.dto.LoansDto;
import com.eazy.core.dto.ResponseDto;
import com.eazy.loans.service.ILoansService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(name = "CRUD REST API for Loans Resource", description = "CRUD REST API for loans resource UPDATE, CREATE, DELETE, and FETCH")
@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class LoansController {

	@Autowired
	private ILoansService iLoansService;

	@Operation(summary = "Create Loan", description = "REST API to create Loans in Eazy")
	@ApiResponse(responseCode = "201", description = "HTTP status CREATED")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoans(@Valid @RequestBody LoansDto loansDto) {
		iLoansService.createLoan(loansDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(ApplicationConstants.STATUS_201, ApplicationConstants.MESSAGE_201));
	}

	@Operation(summary = "Fetch loans", description = "REST API to fetch existing loans in Eazy")
	@ApiResponse(responseCode = "200", description = "HTTP status OK")
	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetchAccount(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loansDto);
	}

	@Operation(summary = "Update loans", description = "REST API to update Loan in Eazy")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "Update Expectation failed", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
		boolean isUpdatedSuccessfull = iLoansService.updateLoan(loansDto);
		if (isUpdatedSuccessfull) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete loans", description = "REST API to delete a loan in Eazy")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "Delete expectation failed") })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteLoans(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_DELETE));
		}
	}
}
