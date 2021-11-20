package com.germanium.lmsuserservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures REST MVC on top of provided by Spring Boot MVC features, plus
 * messages component and Swagger.
 * 
 * @see <a href=
 *      "https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-auto-configuration">Spring
 *      MVC Auto-configuration</a> for details.
 *
 */
@Configuration
public class RestApiConfig implements WebMvcConfigurer {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
