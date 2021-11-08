package com.germanium.lmsuserservice.service.observer;

import com.germanium.lmsuserservice.model.dto.MailRequestDto;

public interface EmailNotificationObserver {
	
	public void sendNotificationEmail(MailRequestDto mailRequest);

}
