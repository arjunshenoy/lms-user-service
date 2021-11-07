package com.germanium.lmsuserservice.service.observer;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface UserRuleStatsObserver {

	public void upadteRuleStatsTable(List<Integer> user);

}
