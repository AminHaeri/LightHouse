package org.amin.fanoos.usermanagement.user.application.port.out;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;

public interface UserPort {
    User getUserByUserName(UserInfoCommand userInfoCommand);
}
