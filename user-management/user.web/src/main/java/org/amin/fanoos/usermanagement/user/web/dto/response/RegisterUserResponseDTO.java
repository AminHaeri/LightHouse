package org.amin.fanoos.usermanagement.user.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;

import java.util.List;

@Data
@AllArgsConstructor
public class RegisterUserResponseDTO {

    private final String message = "User registered successfully.";

    private UserData data;

    @Data
    @AllArgsConstructor
    public static class UserData {
        private String userName;

        private String email;

        private String firstName;

        private String lastName;

        private List<ERole> roles;
    }
}
