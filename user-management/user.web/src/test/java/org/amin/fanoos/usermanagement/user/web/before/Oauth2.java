package org.amin.fanoos.usermanagement.user.web.before;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Oauth2 {

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
}
