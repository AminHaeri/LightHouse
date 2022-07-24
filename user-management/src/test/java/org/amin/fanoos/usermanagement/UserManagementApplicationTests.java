package org.amin.fanoos.usermanagement;

import com.github.javafaker.Faker;
import org.amin.fanoos.usermanagement.controller.UserRegistrationController;
import org.amin.fanoos.usermanagement.datafixtures.UserFixtures;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRegistrationController.class)
class UserManagementApplicationTests {

	private final String SIGNUP_REL_PATH = "/api/v1/users";

	@Autowired
	private MockMvc mockMvc;

	private Faker faker = new Faker();

	@Test
	void contextLoads() {
	}

	@Test
	public void createNewUser_withNullBody_returnsOk() throws Exception {
		mockMvc.perform(post(SIGNUP_REL_PATH))
				.andExpect(status().isOk());
	}

	@Test
	public void createNewUser_withUserEmailAndPassword_returnsJson() throws Exception {
		JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest();
		JSONObject userResponse = UserFixtures.userResponseSuccessful(fakeUserRequest);
		mockMvc.perform(post(SIGNUP_REL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(fakeUserRequest.toString()))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json(userResponse.toString()));
	}
}
