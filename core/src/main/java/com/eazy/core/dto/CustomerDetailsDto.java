package com.eazy.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetails", description = "Schema to hold Customer details Information")
public class CustomerDetailsDto {

	@Schema(description = "Name of the Customer", example = "Ezy Jhon")
	@NotEmpty(message = "Name can not be null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should between 5 to 30 chars")
	private String name;

	@NotEmpty(message = "Email can not be null or empty")
	@Email(message = "Email address should be valid")
	private String email;

	@Schema(description = "Mobile number of the Customer", example = "1234567890")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	@Schema(description = "Accounts details of the Customer")
	private AccountsDto accounts;

	@Schema(description = "Loans details of the Customer")
	private LoansDto loans;

	@Schema(description = "Cards details of the Customer")
	private CardsDto cards;
}
