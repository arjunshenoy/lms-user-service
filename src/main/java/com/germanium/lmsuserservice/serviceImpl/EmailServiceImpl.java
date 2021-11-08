package com.germanium.lmsuserservice.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.model.dto.MailRequestDto;
import com.germanium.lmsuserservice.service.EmailService;
import com.germanium.lmsuserservice.service.observer.EmailNotificationObserver;

/**
 * @author, Ajin Pius Michel
 */

@Service
public class EmailServiceImpl implements EmailService, EmailNotificationObserver {

	private static Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public boolean sendMail(String toAddress, String subject, String content) throws Exception {
		try {
			if (StringUtils.isEmpty(toAddress) || StringUtils.isEmpty(content)) {
				logger.error("Null toAddress or content received.");
				return false;
			}
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(toAddress);
			message.setSubject(subject);
			message.setText(content);
			emailSender.send(message);
			logger.info("Message sent.");
			return true;
		} catch (Exception exception) {
			logger.error("Mail sending falied. {}", exception);
			throw new Exception("Email not sent");
		}
	}

	//This method is used to send Login password to new users
	@Override
	public void sendNotificationEmail(MailRequestDto mailRequest) {
		
		try {
			sendMail(mailRequest.getToAddress(), mailRequest.getSubject(), mailRequest.getContent());
		} catch (Exception e) {
			
			logger.error("Mail sending falied. {}", e);
			e.printStackTrace();
		}
		
		
	}
}
