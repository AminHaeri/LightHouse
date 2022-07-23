package org.amin.fanoos.usermanagement;

import org.amin.fanoos.usermanagement.controller.UserRegistrationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRegistrationController.class)
class UserManagementApplicationTests {

	private final String SIGNUP_REL_PATH = "/api/v1/users";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void createNewUser_withNullBody_returnsOk() throws Exception {
		mockMvc.perform(post(SIGNUP_REL_PATH))
				.andExpect(status().isOk());
	}
}
