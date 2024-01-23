package com.eazy.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eazy.core.dto.AccountsMsgDto;

@Configuration
public class MessageFunctions {

	private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

	@Bean
	public Function<AccountsMsgDto, AccountsMsgDto> email() {
		return accountsMsgDto -> {
			logger.info("sending email with the details : " + accountsMsgDto.toString());
			return accountsMsgDto;
		};
	}

	@Bean
	public Function<AccountsMsgDto, Long> sms() {
		return accountsMsgDto -> {
			logger.info("sending sms with the details : " + accountsMsgDto.toString());
			return accountsMsgDto.accountNumber();
		};
	}

}
