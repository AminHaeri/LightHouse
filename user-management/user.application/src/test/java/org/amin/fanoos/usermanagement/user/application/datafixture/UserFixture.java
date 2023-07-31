package org.amin.fanoos.usermanagement.user.application.datafixture;

import com.github.javafaker.Faker;
import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.application.port.out.command.AddUserCommand;

import java.util.List;
import java.util.UUID;

public class UserFixture {

    private static final Faker faker = new Faker();

    public static User getFakeUser() {
        String fakeUserName = faker.name().username();
        List<Role> roles = List.of(new Role(UUID.randomUUID(), ERole.ROLE_USER));
        Account fakeAccount = new Account(
                UUID.randomUUID(),
                fakeUserName,
                faker.internet().password(),
                roles
        );
        return new User(
                UUID.randomUUID(),
                faker.internet().emailAddress(),
                faker.name().firstName(),
                faker.name().lastName(),
                fakeAccount);
    }

    public static AddUserCommand getAddUserCommand(User user) {
        List<ERole> eRoles = user.getAccount().getRoles().stream().map(Role::getName).toList();
        return new AddUserCommand(
                user.getAccount().getUserName(),
                user.getAccount().getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                eRoles
        );
    }

    public static RegisterUserCommand getRegisterUserCommand(User user) {
        List<ERole> eRoles = user.getAccount().getRoles().stream().map(Role::getName).toList();
        return new RegisterUserCommand(
                user.getAccount().getUserName(),
                user.getAccount().getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                eRoles
        );
    }
}
