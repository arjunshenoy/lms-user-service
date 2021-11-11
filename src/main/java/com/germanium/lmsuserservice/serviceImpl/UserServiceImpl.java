package com.germanium.lmsuserservice.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.ImportUserDTO;
import com.germanium.lmsuserservice.model.dto.MailRequestDto;
import com.germanium.lmsuserservice.repository.UserRepository;
import com.germanium.lmsuserservice.service.observer.CreateUserObserver;
import com.germanium.lmsuserservice.service.observer.EmailNotificationObserver;
import com.germanium.lmsuserservice.service.observer.UserRuleStatsObserver;
import com.germanium.lmsuserservice.service.EmailService;
import com.germanium.lmsuserservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LoginService loginService;

	@Autowired
	private CreateUserObserver createUserObserver;

	@Autowired
	private EmailServiceImpl emailObserver;

	@Autowired
	private LeaveServiceObserverImpl ruleStatsObserver;

	@Override
	public List<User> getUsers() {
		return (List<User>) userRepo.findAll();
	}

	@Override
	public User findUserById(Integer userId) {
		Optional<User> optionalUser = userRepo.findById(userId);
		if (!optionalUser.isPresent()) {
			throw new ResourceNotFoundException("User With User Id: Not Found " + userId);
		}
		return optionalUser.get();
	}

	@Override
	@Transactional
	public List<User> createUser(List<User> user) {
		List<User> savedUserDetails = (List<User>) userRepo.saveAll(user);
		createUserObserver.updateUserLoginTable(savedUserDetails);
		List<Integer> userIdList = savedUserDetails.stream().map(mapper -> mapper.getEmployeeId())
			.collect(Collectors.toList());
		ruleStatsObserver.upadteRuleStatsTable(userIdList);
		savedUserDetails.stream().forEach( userObject -> {
			MailRequestDto mailRequestDto = new MailRequestDto();
			mailRequestDto.setContent(new StringBuilder("Your details are added to LMS, \n User Name : ")
					.append(userObject.getEmail())
					.append("\n Password : ")
					.append(userObject.getEmail())
					.append(userObject.getDob().getYear()).toString());
			mailRequestDto.setSubject("LMS User Credentails");
			mailRequestDto.setToAddress(new String [] {userObject.getEmail()});
			emailObserver.sendNotificationEmail(mailRequestDto);
		});
		
		return savedUserDetails;

	}

	@Override
	public void updateUser(Integer userId, User user) {
		if (!userRepo.existsById(userId)) {
			throw new ResourceNotFoundException("User With User Id: Not Found " + userId);
		}
		// userRepo.deleteById(userId);
		userRepo.save(user);

	}

	@Override
	public List<User> importUserData(List<ImportUserDTO> importUserData) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		Function<ImportUserDTO, User> converter = new Function<ImportUserDTO, User>() {

			public User apply(ImportUserDTO t) {
				User user = new User();
				// user.setEmployeeId(Integer.parseInt(t.getEmployeeId()));
				user.setFirstName(t.getFirstName());
				user.setMiddleName(t.getMiddleName());
				user.setLastName(t.getLastName());
				user.setPhoneNumber(t.getPhoneNumber());
				user.setAddress(t.getAddress());
				try {
					user.setDateOfJoining(formatter.parse(t.getDateOfJoining()));
					user.setDob(formatter.parse(t.getDob()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				user.setGender(t.getGender());
				user.setRole(t.getRole());
				user.setDepartmentId(Integer.parseInt(t.getDepartmentId()));
				user.setActive(Boolean.parseBoolean(t.getIsActive()));
				user.setPermanent(Boolean.parseBoolean(t.getIsPermanent()));
				user.setEmail(t.getEmail());
				return user;
			}
		};

		List<User> userProfiles = importUserData.stream().map(converter).collect(Collectors.<User>toList());
		List<User> savedUsers = (List<User>) userRepo.saveAll(userProfiles);
		createUserObserver.updateUserLoginTable(savedUsers);
		List<Integer> userIdList = savedUsers.stream().map(mapper -> mapper.getEmployeeId())
				.collect(Collectors.toList());
		ruleStatsObserver.upadteRuleStatsTable(userIdList);
		return savedUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		if (!userRepo.existsById(userId)) {
			throw new ResourceNotFoundException("User With User Id: Not Found " + userId);
		}
		userRepo.deleteById(userId);

	}

	@Override
	public void deleteUsers(List<String> ids) {
		for (String id : ids) {
			deleteUser(Integer.valueOf(id));
		}
	}

}
