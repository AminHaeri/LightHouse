package org.amin.fanoos.usermanagement.user.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication(scanBasePackages = {
		"org.amin.fanoos.usermanagement.user.web",
		"org.amin.fanoos.usermanagement.user.application"
})
@EnableAuthorizationServer
public class UserWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWebApplication.class, args);
	}

}
