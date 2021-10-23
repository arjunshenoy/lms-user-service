package com.germanium.lmsuserservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.germanium.lmsuserservice.exceptions.ResourceNotFoundException;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.ImportUserDTO;
import com.germanium.lmsuserservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

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
	public List<User> createUser(List<User> user) {
		return (List<User>) userRepo.saveAll(user);
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
				return user;
			}
		};

		List<User> userProfiles = importUserData.stream().map(converter).collect(Collectors.<User>toList());
		return (List<User>) userRepo.saveAll(userProfiles);
	}

	@Override
	public void deleteUser(Integer userId) {
		if (!userRepo.existsById(userId)) {
			throw new ResourceNotFoundException("User With User Id: Not Found " + userId);
		}
		userRepo.deleteById(userId);

	}

}
