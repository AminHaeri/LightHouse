package org.amin.fanoos.usermanagement.datafixtures;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;

public class UserFixtures {

    private static final Faker faker = new Faker();

    public static JSONObject newFakeUserRequest() throws JSONException {
        return new JSONObject()
                .put("email", faker.internet().emailAddress())
                .put("password", faker.internet().password());
    }

    public static JSONObject userResponseSuccessful(JSONObject userRequest) throws JSONException {
        JSONObject user = new JSONObject()
                .put("email", userRequest.get("email"));

        return new JSONObject()
                .put("message", "User registered successfully.")
                .put("data", user);
    }
}
