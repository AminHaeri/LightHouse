package org.amin.fanoos.usermanagement.user.persistence.repository;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Role.ERole name);
}