package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.web.model.SecurityRole;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityRoleMapperImpl implements SecurityRoleMapper {

    @Override
    public SecurityRole toSecurityRole(Role domainRole) {
        if (domainRole == null)
            return null;

        return SecurityRole.builder()
                .name(domainRole.getName())
                .build();
    }

    @Override
    public List<SecurityRole> toSecurityRoles(List<Role> domainRoles) {
        if (domainRoles == null || domainRoles.isEmpty())
            return null;

        return domainRoles.stream().map(this::toSecurityRole).collect(Collectors.toList());
    }
}
