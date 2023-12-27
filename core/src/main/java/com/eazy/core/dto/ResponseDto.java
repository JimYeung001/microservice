package com.eazy.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response", description = "Schema to hold successfull Information")
public class ResponseDto {

	@Schema(description = "Status Code for response")
	private String statusCode;

	@Schema(description = "Response Message")
	private String statusMsg;
}
