package com.germanium.lmsuserservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.MailRequestDto;
import com.germanium.lmsuserservice.repository.UserRepository;
import com.germanium.lmsuserservice.service.observer.CreateUserObserver;
import com.germanium.lmsuserservice.service.observer.EmailNotificationObserver;
import com.germanium.lmsuserservice.service.observer.UserRuleStatsObserver;
import com.germanium.lmsuserservice.serviceImpl.LeaveServiceObserverImpl;
import com.germanium.lmsuserservice.serviceImpl.LoginService;
import com.germanium.lmsuserservice.serviceImpl.UserServiceImpl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringBootTest
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo;

	@Mock
	private LoginService loginService;
	
	@Mock 
	private CreateUserObserver createUserObserver;
	
	@Mock 
	private LeaveServiceObserverImpl userRuleStatsObserver;
	
	@Mock
	private EmailNotificationObserver emailObserver;

	@InjectMocks
	private UserServiceImpl userService;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test(groups = "mocked")
	public void saveUserProfilescreateUser() throws ParseException {

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

		when(userRepo.saveAll(isA(List.class))).thenReturn(userList);
		doNothing().when(createUserObserver).updateUserLoginTable(isA(List.class));
		doNothing().when(userRuleStatsObserver).upadteRuleStatsTable(isA(List.class));
		doNothing().when(emailObserver).sendNotificationEmail(isA(MailRequestDto.class));
		List<User> returnedUserList = userService.createUser(userList);

		assertNotNull(returnedUserList);
		assertEquals(returnedUserList.size(), 2);
		assertEquals(returnedUserList.get(0).getFirstName(), "Arjun");
		assertEquals(returnedUserList.get(1).getFirstName(), "Chinmay");

	}

	@Test(groups = "mocked")
	public void getUserProfileById() throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		User user1 = new User();
		user1.setFirstName("Arjun");
		user1.setDateOfJoining(formatter.parse("2001/02/01"));
		user1.setDob(formatter.parse("2001/02/01"));
		user1.setPhoneNumber("+918129953172");
		user1.setEmail("asasas@gmail.com");
		Optional<User> optionalUser = Optional.of(user1);
		when(userRepo.findById(isA(Integer.class))).thenReturn(optionalUser);

		User returnedUser = userService.findUserById(1);

		assertNotNull(returnedUser);
		assertEquals(returnedUser.getFirstName(), "Arjun");
		assertEquals(returnedUser.getPhoneNumber(), "+918129953172");
	}

	@Test(groups = "mocked", expectedExceptions = ResourceNotFoundException.class)
	public void getUserProfileById_ReturnsNotFoundException() {
		Optional<User> optionalUser = Optional.empty();
		when(userRepo.findById(isA(Integer.class))).thenReturn(optionalUser);
		userService.findUserById(1);
	}

	@Test(groups = "mocked")
	public void deleteUserProfileById() throws ParseException {

		when(userRepo.existsById(isA(Integer.class))).thenReturn(true);
		userService.deleteUser(1);
		ArgumentCaptor<Integer> userId = ArgumentCaptor.forClass(Integer.class);
		verify(userRepo).deleteById(userId.capture());
		assertEquals(userId.getValue(), Integer.valueOf(1));

	}

	@Test(groups = "mocked", expectedExceptions = ResourceNotFoundException.class)
	public void updateUserProfile_ReturnsNotFoundException() {
		User userProfile = new User();
		when(userRepo.existsById(isA(Integer.class))).thenReturn(false);
		userService.updateUser(1, userProfile);
	}

	@Test(groups = "mocked")
	public void updateUserProfile_verifySave() {
		InOrder inOrder = Mockito.inOrder(userRepo);
		User userProfile = new User();
		when(userRepo.existsById(isA(Integer.class))).thenReturn(true);
		userService.updateUser(1, userProfile);

		inOrder.verify(userRepo).save(userProfile);
	}
}
