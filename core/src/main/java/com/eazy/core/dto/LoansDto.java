package com.eazy.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Schema to hold loans Information")
public class LoansDto {

	@Schema(description = "Mobile number of the Customer", example = "1234567890")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	@Schema(description = "Loan number of the Customer", example = "123456789012")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must be 12 digits")
	private String loanNumber;

	@NotEmpty(message = "Loan Type can not be null or empty")
	private String loanType;

	@Positive(message = "Total Loan should be great than zero")
	@Schema(description = "Total loan amount", example = "10000")
	private int totalLoan;

	@Positive(message = "Loan amount paid should be great than zero")
	@Schema(description = "Loan amount", example = "10000")
	private int amountPaid;

	@PositiveOrZero(message = "Outstanding amount paid should be equal or great than zero")
	@Schema(description = "Outstanding amount", example = "9000")
	private int outstandingAmount;

}
