package org.amin.fanoos.usermanagement.user.application.service;

import com.github.javafaker.Faker;
import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.UserAuthInfoCommand;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAuthServiceTest {

    private final Faker faker = new Faker();

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
    void getAccount() {
        String fakeUserName = faker.name().username();
        List<Role> roles = List.of(new Role(UUID.randomUUID(), ERole.ROLE_USER));
        Account fakeAccount = new Account(
                UUID.randomUUID(),
                fakeUserName,
                faker.internet().password(),
                roles
        );
        User fakeUser = new User(
                UUID.randomUUID(),
                faker.internet().emailAddress(),
                faker.name().firstName(),
                faker.name().lastName(),
                fakeAccount);
        Mockito.when(userPort.getUserByUserName(new UserInfoCommand(fakeUserName))).thenReturn(fakeUser);

        Account account = userAuthService.getAccount(new UserAuthInfoCommand(fakeUserName));
        assertEquals(account, fakeUser.getAccount());
    }
}