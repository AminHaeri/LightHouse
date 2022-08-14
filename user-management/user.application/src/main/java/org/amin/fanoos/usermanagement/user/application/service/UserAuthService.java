package org.amin.fanoos.usermanagement.user.application.service;

import org.amin.fanoos.usermanagement.user.application.Query;
import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.UserAuthInfoCommand;
import org.amin.fanoos.usermanagement.user.application.port.in.query.UserAuthInfoQuery;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;

@Query
public class UserAuthService implements UserAuthInfoQuery {

    private final UserPort userPort;

    public UserAuthService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public Account getAccount(UserAuthInfoCommand userAuthInfoCommand) {
        UserInfoCommand userInfoCommand = new UserInfoCommand(userAuthInfoCommand.userName());
        User user = userPort.getUserByUserName(userInfoCommand);

        return user == null ? null : user.getAccount();
    }
}
