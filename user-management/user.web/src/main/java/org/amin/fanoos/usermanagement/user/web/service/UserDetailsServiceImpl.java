package org.amin.fanoos.usermanagement.user.web.service;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.port.in.command.UserAuthInfoCommand;
import org.amin.fanoos.usermanagement.user.application.port.in.query.UserAuthInfoQuery;
import org.amin.fanoos.usermanagement.user.web.mapper.SecurityRoleMapper;
import org.amin.fanoos.usermanagement.user.web.model.SecurityRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Adapter
@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAuthInfoQuery userAuthInfoQuery;
    private final SecurityRoleMapper securityRoleMapper;

    public UserDetailsServiceImpl(UserAuthInfoQuery userAuthInfoQuery,
                                  SecurityRoleMapper securityRoleMapper) {
        this.userAuthInfoQuery = userAuthInfoQuery;
        this.securityRoleMapper = securityRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthInfoCommand userAuthInfoCommand = new UserAuthInfoCommand(username);
        Account account = this.userAuthInfoQuery.getAccount(userAuthInfoCommand);
        if (account == null)
            throw new UsernameNotFoundException("Incorrect username/password supplied!");

        List<SecurityRole> securityRoles = securityRoleMapper.toSecurityRoles(account.getRoles());

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(account.getPassword())
                .authorities(securityRoles)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
