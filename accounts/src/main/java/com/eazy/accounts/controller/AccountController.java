package com.eazy.accounts.controller;

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

import com.eazy.accounts.constants.AccountsConstants;
import com.eazy.accounts.dto.CustomerDto;
import com.eazy.accounts.dto.ErrorResponseDto;
import com.eazy.accounts.dto.ResponseDto;
import com.eazy.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(name = "CRUD REST API for Accounts Resource", description = "CRUD REST API for accounts resource UPDATE, CREATE, DELETE, and FETCH")
@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
public class AccountController {

	@Autowired
	private IAccountsService iAccountsService;

	@Operation(summary = "Create accounts", description = "REST API to create new Customer Account in Eazy")
	@ApiResponse(responseCode = "201", description = "HTTP status CREATED")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "Fetch accounts", description = "REST API to fetch existing Customer Account in Eazy")
	@ApiResponse(responseCode = "200", description = "HTTP status OK")
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccount(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@Operation(summary = "Update accounts", description = "REST API to update Customer Account in Eazy")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "Update Expectation failed", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountsDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdatedSuccessfull = iAccountsService.updateAccount(customerDto);
		if (isUpdatedSuccessfull) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete accounts", description = "REST API to delete an Customer Account in Eazy")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "Delete expectation failed") })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccounts(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}
}
