package com.germanium.lmsuserservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.germanium.lmsuserservice.model.MailRequest;


@RequestMapping("/mail")
@RestController
public class EmailController {
	
	Logger logger = LoggerFactory.getLogger(EmailController.class);

	@PostMapping(value = "/send", consumes = "application/json")
	public MailRequest sendMail(@RequestBody MailRequest request) {
		logger.info("Request for sending mail received.");
		return request;
	}
}
