package com.germanium.lmsuserservice.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequestDto {

	@JsonProperty("to_address")
	public String [] toAddress;
	
	@JsonProperty("subject")
	public String subject;
	
	@JsonProperty("content")
	public String content;
	
	@JsonProperty("userId")
	public Integer userId;


	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
