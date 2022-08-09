package org.amin.fanoos.usermanagement.user.web;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = UserWebApplication.class)
public class Oauth2Tests {

    private final String OAUTH_REL_PATH = "/oauth/token";
    private final String HEADER_AUTHORIZATION = "Basic RmFub29zQ2xpZW50OkZhbm9vc1NlY3JldA==";

    @Value("${security.oauth2.client.token-validity}")
    private int tokenValidity;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPort userPort;

    @Test
    public void getOath2Token() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String hashedPassword = encoder.encode("fanoos-aminhaeri");

        Role role = new Role(UUID.randomUUID(), ERole.ROLE_SUPERADMIN);
        Account account = new Account(
                UUID.randomUUID(),
                "aminhaeri",
                hashedPassword,
                List.of(role));
        User user = new User(
                UUID.randomUUID(),
                "aminhaeri@mail.com",
                "amin",
                "haeri",
                account);

        UserInfoCommand userInfoCommand = new UserInfoCommand("aminhaeri");
        given(userPort.getUserByUserName(userInfoCommand)).willReturn(user);

        mockMvc.perform(post(OAUTH_REL_PATH)
                        .header("Authorization", HEADER_AUTHORIZATION)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("grant_type", "password")
                        .param("username", "aminhaeri")
                        .param("password", "fanoos-aminhaeri"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(6)))
                .andExpect(jsonPath("$.access_token", anything()))
                .andExpect(jsonPath("$.refresh_token", anything()))
                .andExpect(jsonPath("$.token_type", is("bearer")))
                .andExpect(jsonPath("$.scope", containsString(ERole.ROLE_SUPERADMIN.name())))
                .andExpect(jsonPath("$.jti", anything()))
                .andExpect(jsonPath("$.expires_in", is(this.tokenValidity - 1)));
    }
}
