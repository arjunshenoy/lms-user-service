package com.germanium.lmsuserservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.germanium.lmsuserservice.model.Login;
import com.germanium.lmsuserservice.model.dto.UpdatePasswordDto;
import com.germanium.lmsuserservice.serviceimpl.LoginService;


@RestController
public class LoginController {

	@Autowired
	LoginService loginservice;
	
	@PostMapping("/updatePassword")
	public ResponseEntity<String> changeUserPassword(@Valid @RequestBody UpdatePasswordDto passwordDto)
			throws Exception {
		Login login = loginservice
				.getLoggedinUserDetails(SecurityContextHolder.getContext().getAuthentication().getName());

		if (!loginservice.checkIfValidOldPassword(login, passwordDto.getOldPassword())) {
			throw new Exception("Invalid Password");
		}

		loginservice.changeUserPassword(login.getUserName(), passwordDto.getNewPassword());
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION).body("Password Updated Successfully");

	}

}
