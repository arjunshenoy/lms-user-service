package com.germanium.lmsuserservice.service.observer;

import java.util.List;

import com.germanium.lmsuserservice.model.dto.MailRequestDto;

public interface EmailNotificationObserver {

	public boolean sendNotificationEmail(MailRequestDto mailRequest);

	public String [] getObservers(Integer userId);

}
