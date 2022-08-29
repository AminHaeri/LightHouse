package org.amin.fanoos.usermanagement;

import org.amin.fanoos.usermanagement.manager.Oauth2Manager;
import org.amin.fanoos.usermanagement.manager.SuperUserManager;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class Oauth2ManagerTests {

    @Autowired
    private Oauth2Manager oauth2Manager;

    @Autowired
    private SuperUserManager superUserManager;

    @Test
    public void createNewJwtToken_withSuperUserCredentials_returnsOkAndTokenResult() throws Exception {
        oauth2Manager.postUserAuth(superUserManager.getSuperUser(), superUserManager.getSuperUserRawPassword())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(6)))
                .andExpect(jsonPath("$.access_token", anything()))
                .andExpect(jsonPath("$.refresh_token", anything()))
                .andExpect(jsonPath("$.token_type", is("bearer")))
                .andExpect(jsonPath("$.scope", containsString(ERole.ROLE_SUPERADMIN.name())))
                .andExpect(jsonPath("$.jti", anything()))
                .andExpect(jsonPath("$.expires_in", is(oauth2Manager.tokenValidity - 1)));
    }

    @Test
    public void createNewAuthorizationHeader_withClientIdAndSecretEncoded() {
        assertEquals("Authorization header must be correctly encoded base on clientId and clientSecret",
                "Basic RmFub29zQ2xpZW50OkZhbm9vc1NlY3JldA==",
                oauth2Manager.getAuthorizationHeaderEncoded());
    }
}
