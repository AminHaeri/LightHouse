package org.amin.fanoos.usermanagement.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserJsonMapper {

    public static User toUser(JSONObject userJson) throws JSONException {
        JSONArray rolesJson = userJson.getJSONArray("roles");
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < rolesJson.length(); i++) {
            ERole eRole = (ERole) rolesJson.get(i);
            roles.add(new Role(UUID.randomUUID(), eRole));
        }

        Account account = new Account(
                UUID.randomUUID(),
                (String) userJson.get("userName"),
                (String) userJson.get("password"),
                roles);

        return new User(
                UUID.randomUUID(),
                (String) userJson.get("email"),
                (String) userJson.get("firstName"),
                (String) userJson.get("lastName"),
                account
        );
    }
}
