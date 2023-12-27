package com.eazy.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response Error", description = "Schema to hold error Information")
public class ErrorResponseDto {
	
	@Schema(description ="API path invoked by clients")
	private String apiPath;
	private HttpStatus errorCode;
	private Object errorMessage;
	private LocalDateTime errorTime;

}
