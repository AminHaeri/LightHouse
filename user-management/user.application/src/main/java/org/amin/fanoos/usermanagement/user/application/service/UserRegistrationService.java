package org.amin.fanoos.usermanagement.user.application.service;

import org.amin.fanoos.usermanagement.user.application.UseCase;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.application.port.in.usecase.RegisterUserUseCase;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.AddUserCommand;

import javax.transaction.Transactional;

@UseCase
@Transactional
public class UserRegistrationService implements RegisterUserUseCase {

    private final UserPort userPort;

    public UserRegistrationService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public User registerUser(RegisterUserCommand registerUserCommand) {
        AddUserCommand addUserCommand = new AddUserCommand(
                registerUserCommand.userName(),
                registerUserCommand.password(),
                registerUserCommand.email(),
                registerUserCommand.firstName(),
                registerUserCommand.lastName(),
                registerUserCommand.roles());

        return userPort.insertUser(addUserCommand);
    }
}
