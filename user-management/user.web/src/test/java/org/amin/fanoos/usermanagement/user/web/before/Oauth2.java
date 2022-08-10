package org.amin.fanoos.usermanagement.user.web.before;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class Oauth2 {

    private final String OAUTH_REL_PATH = "/oauth/token";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPort userPort;

    @Value("${security.oauth2.client.token-validity}")
    public int tokenValidity;

    @Value("${security.oauth2.client.client-id}")
    public String clientId;

    @Value("${security.oauth2.client.client-secret}")
    public String clientSecret;

    public String getAuthorizationHeaderEncoded() {
        String raw = clientId + ":" + clientSecret;
        String base64Encoded = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
        return "Basic " + base64Encoded;
    }

    public ResultActions postUserAuth(User user, String rawPassword) throws Exception {
        UserInfoCommand userInfoCommand = new UserInfoCommand(user.getAccount().getUserName());
        given(userPort.getUserByUserName(userInfoCommand)).willReturn(user);

        return mockMvc.perform(post(OAUTH_REL_PATH)
                .header("Authorization", this.getAuthorizationHeaderEncoded())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("grant_type", "password")
                .param("username", user.getAccount().getUserName())
                .param("password", rawPassword));
    }

    public String obtainAccessToken(User user, String rawPassword) throws Exception {
        String result = postUserAuth(user, rawPassword)
                .andReturn()
                .getResponse()
                .getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(result).get("access_token").toString();
    }
}
