package org.amin.fanoos.usermanagement.datafixture;

import com.github.javafaker.Faker;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserFixtures {

    private static final Faker faker = new Faker();

    public static JSONObject newFakeUserRequest(
            boolean isUserName,
            boolean isPassword,
            boolean isEmail,
            boolean isFirstName,
            boolean isLastName,
            ERole eRole) throws JSONException {
        JSONArray fakeRolesJsonArray = new JSONArray();
        List<ERole> fakeRoles = List.of(eRole);
        fakeRoles.forEach(fakeRolesJsonArray::put);

        JSONObject request = new JSONObject();

        if (isUserName)
            request.put("userName", faker.name().username());
        if (isPassword)
            request.put("password", faker.internet().password());
        if (isEmail)
            request.put("email", faker.internet().emailAddress());
        if (isFirstName)
            request.put("firstName", faker.name().firstName());
        if (isLastName)
            request.put("lastName", faker.name().lastName());

        request.put("roles", fakeRolesJsonArray);

        return request;
    }

    public static JSONObject userResponseSuccessful(JSONObject userRequest) throws JSONException {
        JSONObject user = new JSONObject();
        if (userRequest.has("userName"))
            user.put("userName", userRequest.get("userName"));
        if (userRequest.has("email"))
            user.put("email", userRequest.get("email"));
        if (userRequest.has("firstName"))
            user.put("firstName", userRequest.get("firstName"));
        if (userRequest.has("lastName"))
            user.put("lastName", userRequest.get("lastName"));

        user.put("roles", userRequest.get("roles"));

        return new JSONObject()
                .put("message", "User registered successfully.")
                .put("data", user);
    }
}
