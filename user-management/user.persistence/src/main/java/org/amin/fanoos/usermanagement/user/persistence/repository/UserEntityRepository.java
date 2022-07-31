package org.amin.fanoos.usermanagement.user.persistence.repository;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByAccountEntity_RoleEntities_Name(ERole name);
    Optional<UserEntity> findByAccountEntity_UserName(String userName);

//    @Query("select u from user u join u.accountEntity a where a.userName = :userName")
//    Optional<UserEntity> findByUserName(@Param("userName") String userName);
}