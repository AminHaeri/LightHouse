package org.amin.fanoos.usermanagement.user.application;

import org.amin.fanoos.usermanagement.user.application.datafixture.UserFixture;
import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.UserAuthInfoCommand;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.amin.fanoos.usermanagement.user.application.service.UserAuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAuthTest {

    private final UserPort userPort = Mockito.mock(UserPort.class);
    private UserAuthService userAuthService;

    @BeforeEach
    void setUp() {
        userAuthService = new UserAuthService(userPort);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenFakeUser_whenGetAccountFromPort_thenCorrectAccountReturns() {
        User fakeUser = UserFixture.getFakeUser();
        Mockito
                .when(userPort.getUserByUserName(new UserInfoCommand(fakeUser.getAccount().getUserName())))
                .thenReturn(fakeUser);

        Account account = userAuthService.getAccount(new UserAuthInfoCommand(fakeUser.getAccount().getUserName()));
        assertEquals(account, fakeUser.getAccount());
    }
}