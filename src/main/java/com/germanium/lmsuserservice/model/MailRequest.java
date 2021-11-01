package com.germanium.lmsuserservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequest {

	@JsonProperty("to_address")
	public String toAddress;
	
	@JsonProperty("from_address")
	public String fromAddress;
	
	@JsonProperty("content")
	public String content;
}
