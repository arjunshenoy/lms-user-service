package com.germanium.lmsuserservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequestDto {

	@JsonProperty("to_address")
	public String toAddress;
	
	@JsonProperty("subject")
	public String subject;
	
	@JsonProperty("content")
	public String content;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
