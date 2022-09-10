package org.amin.fanoos.usermanagement;

import org.amin.fanoos.usermanagement.datafixture.UserFixtures;
import org.amin.fanoos.usermanagement.manager.Oauth2Manager;
import org.amin.fanoos.usermanagement.manager.SuperUserManager;
import org.amin.fanoos.usermanagement.mapper.UserJsonMapper;
import org.amin.fanoos.usermanagement.seeder.DataSeeder;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.User;
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

    private String getJwtForUser(User user, String rawPassword) throws Exception {
        return oauth2Manager.getJwtToken(user, rawPassword);
    }

    private String getJwtForSuperUser() throws Exception {
        return getJwtForUser(
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
        postNewFakeUser(ERole.ROLE_USER, true);
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
        postNewFakeUser(ERole.ROLE_USER,false);
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
                        ERole.ROLE_SUPERADMIN.name(),
                        fakeUserRequest.get("userName"),
                        fakeUserRequest.getJSONArray("roles").get(0)
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

    @Test
    @Transactional
    public void normalUserCreateNewUser_withAdminRole_returnsErrorResponse() throws Exception {
        JSONObject fakeUserRequest = postNewFakeUser(ERole.ROLE_USER, true);

        JSONObject fakeAdminUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                true,
                true,
                true,
                ERole.ROLE_ADMIN);

        String response = messageSource.getMessage(
                "error.unauthorized.roles",
                new Object[] {
                        fakeUserRequest.getJSONArray("roles").get(0),
                        fakeAdminUserRequest.get("userName"),
                        fakeAdminUserRequest.getJSONArray("roles").get(0)
                },
                Locale.getDefault());

        String token = getJwtForUser(
                UserJsonMapper.toUser(fakeUserRequest),
                (String) fakeUserRequest.get("password")
        );

        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeAdminUserRequest.toString()))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.UNAUTHORIZED.value())))
                .andExpect(jsonPath("$.message", Matchers.is(response)))
                .andExpect(jsonPath("$.timestamp", Matchers.is(notNullValue())));
    }

    private JSONObject postNewFakeUser(ERole role, boolean isFirstName) throws Exception {
        JSONObject fakeUserRequest = UserFixtures.newFakeUserRequest(
                true,
                true,
                true,
                isFirstName,
                true,
                role);

        JSONObject userResponse = UserFixtures.userResponseSuccessful(fakeUserRequest);
        mockMvc.perform(post(SIGNUP_REL_PATH)
                        .header(HEADER_AUTHORIZATION, getJwtForSuperUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fakeUserRequest.toString()))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(userResponse.toString()));
        return fakeUserRequest;
    }
}
