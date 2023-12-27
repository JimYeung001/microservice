package com.eazy.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account Information")
public class AccountsDto {

	@NotEmpty(message = "Account number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
	private Long accountNumber;

	@Schema(description = "Account Type of Eazy", example = "Savings")
	@NotEmpty(message = "Account type can not be a null or empty")
	private String accountType;

	@Schema(description = "Account Branch Address", example = "123 Main Street, NY")
	@NotEmpty(message = "Branch address can not be a null or empty")
	private String branchAddress;
}
