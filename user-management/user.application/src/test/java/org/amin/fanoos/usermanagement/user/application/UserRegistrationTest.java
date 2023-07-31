package org.amin.fanoos.usermanagement.user.application;

import org.amin.fanoos.usermanagement.user.application.datafixture.UserFixture;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.AddUserCommand;
import org.amin.fanoos.usermanagement.user.application.service.UserRegistrationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRegistrationTest {

    private final UserPort userPort = Mockito.mock(UserPort.class);
    private UserRegistrationService userRegistrationService;

    @BeforeEach
    void setUp() {
        userRegistrationService = new UserRegistrationService(userPort);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenFakeUser_whenInsertUserFromPort_thenCorrectUserReturns() {
        User fakeUser = UserFixture.getFakeUser();
        AddUserCommand addUserCommand = UserFixture.getAddUserCommand(fakeUser);
        RegisterUserCommand registerUserCommand = UserFixture.getRegisterUserCommand(fakeUser);

        Mockito.when(userPort.insertUser(addUserCommand)).thenReturn(fakeUser);

        User expectedUser = userRegistrationService.registerUser(registerUserCommand);
        assertEquals(expectedUser, fakeUser);
    }
}