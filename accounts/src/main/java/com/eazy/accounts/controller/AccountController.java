package com.eazy.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.eazy.accounts.service.IAccountsService;
import com.eazy.core.constants.ApplicationConstants;
import com.eazy.core.dto.ContactInfoDto;
import com.eazy.core.dto.CustomerDto;
import com.eazy.core.dto.ErrorResponseDto;
import com.eazy.core.dto.ResponseDto;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private IAccountsService iAccountsService;

	@Autowired
	ContactInfoDto contactInfoDto;

	@Operation(summary = "Create accounts", description = "REST API to create new Customer Account in Eazy")
	@ApiResponse(responseCode = "201", description = "HTTP status CREATED")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(ApplicationConstants.STATUS_201, ApplicationConstants.MESSAGE_201));
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
					.body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_UPDATE));
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
					.body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_DELETE));
		}
	}

	@Operation(summary = "Contact", description = "REST API to get contact information")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "Delete expectation failed") })
	@GetMapping("/contact-info")
	public ResponseEntity<ContactInfoDto> getContact() {
		return ResponseEntity.status(HttpStatus.OK).body(contactInfoDto);

	}

	@Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() {
		logger.debug("getBuildInfo() method invoked");
		return ResponseEntity.status(HttpStatus.OK).body("V1.0.9");

	}

	public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
		logger.debug("getBuildInfoFallback() method invoked");
		return ResponseEntity.status(HttpStatus.OK).body("0.9");
	}

	@RateLimiter(name="getJavaVersion", fallbackMethod="getJavaVersionFallback")
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(System.getProperty("java.version"));
	}
	public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
		return ResponseEntity.status(HttpStatus.OK).body("java 17");
	}
}
