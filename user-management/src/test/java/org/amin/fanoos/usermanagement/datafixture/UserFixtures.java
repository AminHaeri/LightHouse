package org.amin.fanoos.usermanagement.datafixture;

import com.github.javafaker.Faker;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserFixtures {

    private static final Faker faker = new Faker();

    public static JSONObject newFakeUserRequest() throws JSONException {
        JSONArray fakeRolesJsonArray = new JSONArray();
        List<ERole> fakeRoles = List.of(ERole.ROLE_USER);
        fakeRoles.forEach(fakeRolesJsonArray::put);

        return new JSONObject()
                .put("userName", faker.name().username())
                .put("password", faker.internet().password())
                .put("email", faker.internet().emailAddress())
                .put("firstName", faker.name().firstName())
                .put("lastName", faker.name().lastName())
                .put("roles", fakeRolesJsonArray);
    }

    public static JSONObject userResponseSuccessful(JSONObject userRequest) throws JSONException {
        JSONObject user = new JSONObject()
                .put("userName", userRequest.get("userName"))
                .put("email", userRequest.get("email"))
                .put("firstName", userRequest.get("firstName"))
                .put("lastName", userRequest.get("lastName"))
                .put("roles", userRequest.get("roles"));

        return new JSONObject()
                .put("message", "User registered successfully.")
                .put("data", user);
    }
}
