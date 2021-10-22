package com.germanium.lmsuserservice.controller;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.germanium.lmsuserservice.model.User;
import com.germanium.lmsuserservice.model.dto.ImportUserDTO;
import com.germanium.lmsuserservice.service.UserService;
import com.opencsv.bean.CsvToBeanBuilder;

@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	public static final Logger logger = LogManager.getLogger();

	@Autowired
	private UserService userService;

	@GetMapping("profiles")
	public ResponseEntity<List<User>> getUsers() {
		List<User> entity = userService.getUsers();
		return ResponseEntity.ok().body(entity);
	}

	@GetMapping("profiles/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok().body(userService.findUserById(userId));
	}

	@PostMapping("profiles")
	public ResponseEntity<List<User>> createUser(@Valid @RequestBody List<User> userProfile) {
		List<User> userProfileDetails = userService.createUser(userProfile);
		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION).body(userProfileDetails);
	}

	@PutMapping("/profiles/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") final Integer profileId,
			@Valid @RequestBody User userProfile) {
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION)
				.body(userService.updateUser(profileId, userProfile));

	}

	@PostMapping(value = "/profiles/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Transactional
	public ResponseEntity<List<User>> importUserData(@RequestParam("file") MultipartFile file) throws Exception {

		logger.debug("API REQUEST - importUser. Import file = {}", file.getName());
		Reader reader = new InputStreamReader(file.getInputStream());

		List<ImportUserDTO> userDTO = new CsvToBeanBuilder<ImportUserDTO>(reader).withType(ImportUserDTO.class).build()
				.parse();

		List<User> createdUsers = userService.importUserData(userDTO);
		return ResponseEntity.status(HttpStatus.OK).body(createdUsers);
	}

}
