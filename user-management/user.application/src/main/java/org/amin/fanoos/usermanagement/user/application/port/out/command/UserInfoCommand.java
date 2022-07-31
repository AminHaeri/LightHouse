package org.amin.fanoos.usermanagement.user.application.port.out.command;

import javax.validation.constraints.NotEmpty;

public record UserInfoCommand(
        @NotEmpty String userName
) {}
