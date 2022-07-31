package org.amin.fanoos.usermanagement.user.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages = {
		"org.amin.fanoos.usermanagement.user.web",
		"org.amin.fanoos.usermanagement.user.application"
})
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWebApplication.class, args);
	}

}
