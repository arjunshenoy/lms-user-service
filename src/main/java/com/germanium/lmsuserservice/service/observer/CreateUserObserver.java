package com.germanium.lmsuserservice.service.observer;

import java.util.List;

import com.germanium.lmsuserservice.model.User;

public interface CreateUserObserver {
	
	
	public void updateUserLoginTable(List<User> user);

}
