package org.amin.fanoos.usermanagement.user.application.port.in.query;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.port.in.command.UserAuthInfoCommand;

import javax.validation.Valid;

public interface UserAuthInfoQuery {
    Account getAccount(@Valid UserAuthInfoCommand userAuthInfoCommand);
}
