package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ERoleMapperImpl implements ERoleMapper {

    @Override
    public Role toRole(ERole eRole) {
        return new Role(null, eRole);
    }

    @Override
    public List<Role> toRoles(List<ERole> eRoles) {
        if (eRoles == null || eRoles.isEmpty())
            return null;

        return eRoles.stream().map(this::toRole).collect(Collectors.toList());
    }

    @Override
    public ERole toERole(Role role) {
        return role.getName();
    }

    @Override
    public List<ERole> toERoles(List<Role> roles) {
        if (roles == null || roles.isEmpty())
            return null;

        return roles.stream().map(this::toERole).collect(Collectors.toList());
    }
}
