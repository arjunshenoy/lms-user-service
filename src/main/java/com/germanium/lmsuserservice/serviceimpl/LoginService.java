package com.germanium.lmsuserservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.model.Login;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.UserSecurityDto;
import com.germanium.lmsuserservice.repository.LoginRepository;
import com.germanium.lmsuserservice.service.observer.CreateUserObserver;

@Service
public class LoginService implements UserDetailsService, CreateUserObserver {

	@Autowired
	private LoginRepository loginRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static String LOGIN_ERROR= "Bad Credentials";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> user = loginRepo.findById(username);
		if (user.isEmpty()) {
			throw new BadCredentialsException(LOGIN_ERROR);
		} else {
			return user.map(UserSecurityDto::new).get();
		}

	}

	public Login getLoggedinUserDetails(String username) {
		Optional<Login> user = loginRepo.findById(username);
		if (user.isEmpty()) {
			throw new BadCredentialsException(LOGIN_ERROR);
		}
		return user.get();
	}

	public void changeUserPassword(String user, String password) {
		loginRepo.updateUserPassword(user, password);

	}

	public boolean checkIfValidOldPassword(Login login, String oldPassword) {

		return (login.getPassword().equals(oldPassword)); //check this test case
	}

	@Transactional
	public void addUserLoginDetails(List<User> userList) {
		List<Login> loginList = new ArrayList<>(); //check this
		userList.stream().forEach(user -> {
			if (loginRepo.existsById(user.getEmail())) {
				throw new BadCredentialsException(LOGIN_ERROR);
			}
			Login userLogin = new Login();
			userLogin.setUserName(user.getEmail());
			userLogin.setaActive(true);
			userLogin.setId(user.getEmployeeId());

			String password = bCryptPasswordEncoder.encode(new StringBuilder(user.getEmail()).append(user.getDob().getYear()).toString());
			userLogin.setPassword(password);
			userLogin.setRoles(user.getRole());
			loginList.add(userLogin);
		});

		loginRepo.saveAll(loginList);

	}

//below update method is same as add user.
	@Override
	public void updateUserLoginTable(List<User> userList) {
		List<Login> loginList = new ArrayList<>();
		userList.stream().forEach(user -> {
			if (loginRepo.existsById(user.getEmail())) {
				throw new BadCredentialsException(LOGIN_ERROR);
			}
			Login userLogin = new Login();
			userLogin.setUserName(user.getEmail());
			userLogin.setaActive(true);
			userLogin.setId(user.getEmployeeId());
			String password = bCryptPasswordEncoder.encode(new StringBuilder(user.getEmail()).append(user.getDob().getYear()).toString());
			userLogin.setPassword(password);
			userLogin.setRoles(user.getRole());
			loginList.add(userLogin);
		});

		loginRepo.saveAll(loginList);
	}
}
