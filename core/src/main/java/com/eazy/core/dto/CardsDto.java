package com.eazy.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Cards", description = "Schema to hold cards Information")
public class CardsDto {

	@Schema(description = "Mobile number of the Customer", example = "1234567890")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	@Schema(description = "Card number of the Customer", example = "123456789012")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be 12 digits")
	private String cardNumber;

	@NotEmpty(message = "Card Type can not be null or empty")
	private String cardType;

	@Positive(message = "Total card limit should be great than zero")
	@Schema(description = "Total loan amount", example = "10000")
	private int totalLimit;

	@Positive(message = "the amount used should be great than zero")
	@Schema(description = "Amount used", example = "10000")
	private int amountUsed;

	@PositiveOrZero(message = "Available amount  should be equal or great than zero")
	@Schema(description = "Outstanding amount", example = "9000")
	private int availableAmount;

}
