package com.germanium.lmsuserservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@RequestMapping("/lms-user-service/swagger-resources")
@Controller
@ApiIgnore
public class SwaggerApiResourceController extends ApiResourceController {

	public SwaggerApiResourceController(SwaggerResourcesProvider swaggerResources) {
		super(swaggerResources, "/lms-user-service");
	}

}