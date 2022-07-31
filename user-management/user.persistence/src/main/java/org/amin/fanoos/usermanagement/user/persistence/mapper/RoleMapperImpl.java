package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toDomain(RoleEntity roleEntity) {
        if (roleEntity == null)
            return null;

        return Role.builder()
                .id(roleEntity.getUId())
                .name(roleEntity.getName())
                .build();
    }

    @Override
    public List<Role> toDomain(List<RoleEntity> roleEntities) {
        if (roleEntities == null || roleEntities.isEmpty())
            return null;

        return roleEntities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public RoleEntity toEntity(Role role) {
        if (role == null)
            return null;

        return RoleEntity.builder()
                .uId(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public List<RoleEntity> toEntity(List<Role> roles) {
        if (roles == null || roles.isEmpty())
            return null;

        return roles.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
