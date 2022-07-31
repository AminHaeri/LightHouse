package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleMapper {
    Role toDomain(RoleEntity roleEntity);
    List<Role> toDomain(List<RoleEntity> roleEntities);

    RoleEntity toEntity(Role role);
    List<RoleEntity> toEntity(List<Role> roles);
}
