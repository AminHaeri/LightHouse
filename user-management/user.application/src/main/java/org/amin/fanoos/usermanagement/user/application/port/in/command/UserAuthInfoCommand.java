package org.amin.fanoos.usermanagement.user.application.port.in.command;

import javax.validation.constraints.NotEmpty;

public record UserAuthInfoCommand(
        @NotEmpty String userName
) {}
