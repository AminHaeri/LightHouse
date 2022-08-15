package org.amin.fanoos.usermanagement.user.web;

import org.amin.fanoos.usermanagement.user.web.datafixture.UserFixtures;
import org.amin.fanoos.usermanagement.user.web.manager.Oauth2Manager;
import org.amin.fanoos.usermanagement.user.web.manager.SuperUserManager;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(UserRegistrationController.class)
//@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = UserWebApplication.class)
class UserWebApplicationTests {

    private final String SIGNUP_REL_PATH = "/api/v1/users";
    private final String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Oauth2Manager oauth2Manager;

    @Autowired
    private SuperUserManager superUserManager;

    @Test
    void contextLoads() {
    }

    private String getJwtForSuperUser() throws Exception {
        return oauth2Manager.getJwtToken(
                superUserManager.getSuperUser(),
                superUserManager.getSuperUserRawPassword());
    }

    @Test
    public void createNewUser_withNullBody_returnsCreated() throws Exception {
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser()))
                .andExpect(status().isCreated());
    }

    @Test
    public void createNewUser_withUserEmailAndPassword_returnsJson() throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest();
        JSONObject userResponse = UserFixtures.userResponseSuccessful(fakeUserRequest);
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(userResponse.toString()));
    }
}
