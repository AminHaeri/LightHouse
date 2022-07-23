package org.amin.fanoos.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserManagementApplicationTests {

	private final String SIGNUP_BASE_URL = "http://localhost";
	private final String SIGNUP_REL_PATH = "/api/v1/users";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getSignupFullUrl() {
		return SIGNUP_BASE_URL + ":" + port + SIGNUP_REL_PATH;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void createNewUser_returnsOk() {
		ResponseEntity<String> response = restTemplate.postForEntity(getSignupFullUrl(), null, String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}
