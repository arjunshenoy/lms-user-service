package com.germanium.lmsuserservice.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.model.Login;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.UserSecurityDto;
import com.germanium.lmsuserservice.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> user = loginRepo.findById(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		return user.map(UserSecurityDto::new).get();

	}

	public Login getLoggedinUserDetails(String username) {
		Optional<Login> user = loginRepo.findById(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		return user.get();
	}

	public void changeUserPassword(String user, String password) {
		loginRepo.updateUserPassword(user, password);

	}

	public boolean checkIfValidOldPassword(Login login, String oldPassword) {

		return (login.getPassword().equals(oldPassword) ? true : false);
	}

	@Transactional
	public void addUserLoginDetails(List<User> userList) {
		List<Login> loginList = new ArrayList<Login>();
		userList.stream().forEach(user -> {
			if (loginRepo.existsById(user.getEmail())) {
				new UsernameNotFoundException("User with same username already exists: " + user.getEmail());
			}
			Login userLogin = new Login();
			userLogin.setUserName(user.getEmail());
			userLogin.setaActive(true);
			userLogin.setId(user.getEmployeeId());
			userLogin.setPassword(new StringBuilder(user.getEmail()).append(user.getDob().getYear()).toString());
			userLogin.setRoles(user.getRole());
			loginList.add(userLogin);
		});

		loginRepo.saveAll(loginList);

	}
}
