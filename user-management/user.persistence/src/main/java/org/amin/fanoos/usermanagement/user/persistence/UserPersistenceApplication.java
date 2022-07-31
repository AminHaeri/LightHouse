package org.amin.fanoos.usermanagement.user.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EnableJpaRepositories
public class UserPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPersistenceApplication.class, args);
	}

}
