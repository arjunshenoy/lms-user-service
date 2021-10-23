package com.germanium.lmsuserservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.germanium.lmsuserservice.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User resource", produces = "application/json")
public interface UserServiceApi {

	@ApiOperation(value = "Get a user profile by profile ID", nickname = "getFindUserById", notes = "Returns a single user profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = User.class),
			@ApiResponse(code = 401, message = "Invalid / missing / expired token"),
			@ApiResponse(code = 403, message = "Missing service permission"),
			@ApiResponse(code = 404, message = "Matching Profile not found") })
	ResponseEntity<User> getUserById(
			@ApiParam(value = "ID of the  user profile", required = true) @PathVariable("userId") Integer userId);

}
