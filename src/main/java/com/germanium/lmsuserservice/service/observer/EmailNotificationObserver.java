package com.germanium.lmsuserservice.service.observer;

import com.germanium.lmsuserservice.model.dto.MailRequestDto;

public interface EmailNotificationObserver {

	public boolean sendNotificationEmail(MailRequestDto mailRequest) throws Exception;

	public String [] getObservers(Integer userId);

}
