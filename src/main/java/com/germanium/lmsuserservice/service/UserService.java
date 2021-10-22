package com.germanium.lmsuserservice.service;

import java.util.List;

import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.ImportUserDTO;

public interface UserService {

	public List<User> getUsers();

	public User findUserById(Integer userId);
	
	public List<User> createUser(List<User> user);
	
	public User updateUser(Integer userId, User user);

	public List<User> importUserData(List<ImportUserDTO> userData);
}
