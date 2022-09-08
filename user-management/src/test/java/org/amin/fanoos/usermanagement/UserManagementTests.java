package org.amin.fanoos.usermanagement;

import org.amin.fanoos.usermanagement.datafixture.UserFixtures;
import org.amin.fanoos.usermanagement.manager.Oauth2Manager;
import org.amin.fanoos.usermanagement.manager.SuperUserManager;
import org.amin.fanoos.usermanagement.seeder.DataSeeder;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserRegistrationController.class)
//@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class UserManagementTests {

    private final String SIGNUP_REL_PATH = "/api/v1/users";
    private final String HEADER_AUTHORIZATION = "Authorization";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Oauth2Manager oauth2Manager;

    @Autowired
    private SuperUserManager superUserManager;

    @BeforeAll
    public static void runSeeders(@Autowired DataSeeder dataSeeder) {
        dataSeeder.run();
    }

    @Test
    void contextLoads() {
    }

    private String getJwtForSuperUser() throws Exception {
        return oauth2Manager.getJwtToken(
                superUserManager.getSuperUser(),
                superUserManager.getSuperUserRawPassword());
    }

    @Test
    @Transactional
    public void createNewUser_withNullBody_returnsCreated() throws Exception {
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void createNewUser_withValidRequest_returnsSuccessResponse() throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                true,
                true,
                true,
                ERole.ROLE_USER);

        JSONObject userResponse = UserFixtures.userResponseSuccessful(fakeUserRequest);
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(userResponse.toString()));
    }

    @Test
    @Transactional
    public void createNewUser_withoutEmail_returnsErrorResponse() throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                false,
                true,
                true,
                ERole.ROLE_USER);

        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", Matchers.is(notNullValue())))
                .andExpect(jsonPath("$.timestamp", Matchers.is(notNullValue())));
    }

    @Test
    @Transactional
    public void createNewUser_withoutFirstName_returnsSuccessResponse() throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                true,
                false,
                true,
                ERole.ROLE_USER);

        JSONObject userResponse = UserFixtures.userResponseSuccessful(fakeUserRequest);
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(userResponse.toString()));
    }

    @Test
    @Transactional
    public void createNewUser_withSuperAdminRole_returnsErrorResponse() throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                true,
                true,
                true,
                ERole.ROLE_SUPERADMIN);

        String response = messageSource.getMessage(
                "error.unauthorized.roles",
                new Object[] {
                        fakeUserRequest.get("userName"),
                        fakeUserRequest.getJSONArray("roles").get(0),
                        ERole.ROLE_SUPERADMIN.name()
                },
                Locale.getDefault());
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.UNAUTHORIZED.value())))
                .andExpect(jsonPath("$.message", Matchers.is(response)))
                .andExpect(jsonPath("$.timestamp", Matchers.is(notNullValue())));
    }
}
