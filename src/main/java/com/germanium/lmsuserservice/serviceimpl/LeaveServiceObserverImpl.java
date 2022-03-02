package com.germanium.lmsuserservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.MailRequestDto;
import com.germanium.lmsuserservice.service.EmailService;
import com.germanium.lmsuserservice.service.UserService;
import com.germanium.lmsuserservice.service.observer.EmailNotificationObserver;
import com.germanium.lmsuserservice.service.observer.UserRuleStatsObserver;

@Service
public class LeaveServiceObserverImpl implements UserRuleStatsObserver, EmailNotificationObserver {

	private static final String STATS_URL = "/api/v1/leave/leaveStats/";

	@Value("${leave.service.url}")
	private String leaveService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;

	@Override
	public void upadteRuleStatsTable(List<Integer> userList) {
		userList.stream().forEach(user -> {
			restTemplate.postForObject(new StringBuilder(leaveService).append(STATS_URL).append(user).toString(), user,
					Boolean.class);
		});

	}

	@Override
	public boolean sendNotificationEmail(MailRequestDto mailRequest) throws Exception  {
		mailRequest.setToAddress(getObservers(mailRequest.getUserId()));
	
			return emailService.sendMail(mailRequest);
		
	}

	@Override
	public String [] getObservers(Integer userId) {
		User userDetails = userService.findUserById(userId);
		List<String> observerList = new ArrayList<String>();
		observerList.add(userDetails.getEmail());
		
		User departmentHeadDetails = userService.findUserById(userDetails.getDepartment().getHeadId());
		observerList.add(departmentHeadDetails.getEmail());
		 String[] myArray = new String[observerList.size()];
	      observerList.toArray(myArray);
		return  myArray;
	}

}
