package com.germanium.lmsuserservice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.germanium.lmsuserservice.service.observer.UserRuleStatsObserver;

@Service
public class LeaveServiceObserverImpl implements UserRuleStatsObserver {

	
	private static final String STATS_URL ="/api/v1/leave/addLeaveStats/";
	

	@Value("${leave.service.url}")
	private String leaveService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public void upadteRuleStatsTable(List<Integer> userList) {
		userList.stream().forEach(user -> {
			restTemplate.postForObject(new StringBuilder(leaveService).append(STATS_URL).append(user).toString(), user, Integer.class);
		});

	}

}
