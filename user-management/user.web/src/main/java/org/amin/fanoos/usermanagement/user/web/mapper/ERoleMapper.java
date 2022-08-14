package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;

import java.util.List;

public interface ERoleMapper {
    Role toRole(ERole eRole);
    List<Role> toRoles(List<ERole> eRoles);

    ERole toERole(Role role);
    List<ERole> toERoles(List<Role> roles);
}
