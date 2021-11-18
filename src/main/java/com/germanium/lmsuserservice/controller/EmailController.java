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

import com.germanium.lmsuserservice.model.dto.MailRequestDto;
import com.germanium.lmsuserservice.service.EmailService;
import com.germanium.lmsuserservice.serviceImpl.LeaveServiceObserverImpl;

@RequestMapping("/mail")
@RestController
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private LeaveServiceObserverImpl leaveObserver;

	@PostMapping(value = "/send", consumes = "application/json")
	public ResponseEntity<?> sendMail(@RequestBody MailRequestDto request) throws Exception {
		logger.info("Received request for mail to : {}.", request.toAddress);
		boolean isSend = emailService.sendMail(request);
		if (isSend)
			return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
		else
			return ResponseEntity.status(HttpStatus.OK).body("Message sent failed");
	}

	@PostMapping(value = "/leave/notify", consumes = "application/json")
	public ResponseEntity<?> notifyUsers(@RequestBody MailRequestDto request) throws Exception {
//		logger.info("Received request for mail to : {}.", request.toAddress);
		boolean isSend = leaveObserver.sendNotificationEmail(request);
		if (isSend)
			return ResponseEntity.status(HttpStatus.OK).body(request);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail couldn't sent.");
	}
}
