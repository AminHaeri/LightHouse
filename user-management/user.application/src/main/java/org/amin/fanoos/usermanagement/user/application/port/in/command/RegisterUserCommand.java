package org.amin.fanoos.usermanagement.user.application.port.in.command;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public record RegisterUserCommand(
        @NotEmpty String userName,
        @NotEmpty String password,
        @NotEmpty String email,
        String firstName,
        String lastName,
        @NotEmpty List<ERole> roles
) { }
