package com.germanium.lmsuserservice.service;

/**
 * @author, Ajin Pius Michel
 */
public interface EmailService {

	public boolean sendMail(String toAddress, String subject, String content) throws Exception;
}
