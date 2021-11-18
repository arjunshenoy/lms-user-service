package com.germanium.lmsuserservice.service;

import com.germanium.lmsuserservice.model.dto.MailRequestDto;

/**
 * @author, Ajin Pius Michel
 */
public interface EmailService {

	public boolean sendMail(MailRequestDto mailRequest) throws Exception;
}
