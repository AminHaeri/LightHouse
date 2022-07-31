package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.web.model.SecurityRole;

import java.util.List;

public interface SecurityRoleMapper {
    SecurityRole toSecurityRole(Role domainRole);
    List<SecurityRole> toSecurityRoles(List<Role> domainRoles);
}
