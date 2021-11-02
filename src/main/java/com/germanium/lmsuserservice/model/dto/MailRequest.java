package com.germanium.lmsuserservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequest {

	@JsonProperty("to_address")
	public String toAddress;
	
	@JsonProperty("subject")
	public String subject;
	
	@JsonProperty("content")
	public String content;
}
