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
	public ResponseEntity<Boolean> sendMail(@RequestBody MailRequestDto request) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(emailService.sendMail(request));
	}

	@PostMapping(value = "/leave/notify", consumes = "application/json")
	public ResponseEntity<Boolean> notifyUsers(@RequestBody MailRequestDto request) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(leaveObserver.sendNotificationEmail(request));
	}
}
