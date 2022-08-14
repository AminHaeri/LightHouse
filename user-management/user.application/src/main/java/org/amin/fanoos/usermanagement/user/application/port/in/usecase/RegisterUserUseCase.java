package org.amin.fanoos.usermanagement.user.application.port.in.usecase;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;

import javax.validation.Valid;

public interface RegisterUserUseCase {
    User registerUser(@Valid RegisterUserCommand registerUserCommand);
}
