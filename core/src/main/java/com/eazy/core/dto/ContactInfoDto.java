package com.eazy.core.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="accounts")
public record ContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {

}
