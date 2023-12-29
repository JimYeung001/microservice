package com.eazy.core.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class ContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
}
