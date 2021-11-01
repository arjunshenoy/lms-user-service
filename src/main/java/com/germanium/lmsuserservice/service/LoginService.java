package com.germanium.lmsuserservice.service;

import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.model.Login;
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
}
