package com.germanium.lmsuserservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.germanium.lmsuserservice.model.dto.MailRequest;
import com.germanium.lmsuserservice.service.EmailService;

@RequestMapping("/mail")
@RestController
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	@PostMapping(value = "/send", consumes = "application/json")
	public ResponseEntity<?> sendMail(@RequestBody MailRequest request) throws Exception {
		logger.info("Received request for mail to : {}.", request.toAddress);
		boolean isSend = emailService.sendMail(request.toAddress, request.subject, request.content);
		if (isSend)
			return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
		else
			return ResponseEntity.status(HttpStatus.OK).body("Message sent failed");
	}
}
