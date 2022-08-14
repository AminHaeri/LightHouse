package org.amin.fanoos.usermanagement.user.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterUserResponseDTO {

    private UUID uId;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private List<ERole> roles;
}
