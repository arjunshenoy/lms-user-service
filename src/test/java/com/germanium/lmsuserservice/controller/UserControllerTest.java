package com.germanium.lmsuserservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.service.UserService;

public class UserControllerTest {

	@Mock
	UserService userService;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@InjectMocks
	private UserController userController;

	@Test(groups = "mocked")
	public void getUserProfiles_RetursStatusCodeOK() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		User user1 = new User();
		user1.setFirstName("Arjun");
		user1.setDateOfJoining(formatter.parse("2001/02/01"));
		user1.setDob(formatter.parse("2001/02/01"));
		user1.setPhoneNumber("+918129953172");
		user1.setEmail("asasas@gmail.com");

		User user2 = new User();
		user2.setFirstName("Chinmay");
		user2.setDateOfJoining(formatter.parse("2001/02/01"));
		user2.setDob(formatter.parse("2001/02/01"));
		user2.setPhoneNumber("+918129953172");
		user2.setEmail("asasas@gmail.com");

		List<User> userList = List.of(user1, user2);

		when(userService.getUsers()).thenReturn(userList);
	var responseEntity = userController.getUsers();
	assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test(groups = "mocked", expectedExceptions = ResourceNotFoundException.class)
	public void getUsersById_shouldReturnStatuscodeNotFound() {
		when(userService.findUserById(isA(Integer.class))).thenThrow(ResourceNotFoundException.class);
		userController.getUserById(1);
	}
	
	@Test(groups = "mocked")
	public void getUsersById_shouldReturnStatuscodeOK() throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		User user1 = new User();
		user1.setFirstName("Arjun");
		user1.setDateOfJoining(formatter.parse("2001/02/01"));
		user1.setDob(formatter.parse("2001/02/01"));
		user1.setPhoneNumber("+918129953172");
		user1.setEmail("asasas@gmail.com");
		when(userService.findUserById(isA(Integer.class))).thenReturn(user1);
		var responseEntity = userController.getUserById(1);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

	}

@Test(groups = "mocked")
public void updateUserProfile_shouldReturnStatuscodeOK() throws ParseException {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	User user1 = new User();
	user1.setFirstName("Arjun");
	user1.setDateOfJoining(formatter.parse("2001/02/01"));
	user1.setDob(formatter.parse("2001/02/01"));
	user1.setPhoneNumber("+918129953172");
	user1.setEmail("asasas@gmail.com");
	when(userService.findUserById(isA(Integer.class))).thenReturn(user1);
	userController.getUserById(1);
	
	doNothing().when(userService).updateUser(Mockito.any(Integer.class), Mockito.any(User.class));
	
	UserController spy = Mockito.spy(userController);
	
	var responseEntity = spy.updateUser(1, user1);
	assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

}

}


