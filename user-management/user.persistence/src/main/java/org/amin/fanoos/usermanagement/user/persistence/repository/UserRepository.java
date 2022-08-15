package org.amin.fanoos.usermanagement.user.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.amin.fanoos.usermanagement.user.application.Adapter;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.AddUserCommand;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.amin.fanoos.usermanagement.user.persistence.entity.AccountEntity;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;
import org.amin.fanoos.usermanagement.user.persistence.mapper.UserMapper;
import org.amin.fanoos.usermanagement.user.persistence.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
@Adapter
public class UserRepository implements UserPort {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserRepository(
            UserEntityRepository userEntityRepository,
            RoleEntityRepository roleEntityRepository,
            PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUserName(UserInfoCommand userInfoCommand) {
        UserEntity userEntity =
                userEntityRepository.findByAccountEntity_UserName(userInfoCommand.userName()).orElse(null);

        return userMapper.toDomain(userEntity);
    }

    @Override
    public User insertUser(AddUserCommand addUserCommand) {
        List<RoleEntity> roleEntities = findRoles(addUserCommand.roles());
        AccountEntity accountEntity = new AccountEntity(
                UUID.randomUUID(),
                addUserCommand.userName(),
                passwordEncoder.hashedPassword(addUserCommand.password()),
                new HashSet<>(roleEntities)
        );
        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                addUserCommand.email(),
                addUserCommand.firstName(),
                addUserCommand.lastName(),
                accountEntity
        );

        return userMapper.toDomain(userEntityRepository.save(userEntity));
    }

    private List<RoleEntity> findRoles(List<ERole> eRoles) {
        if (eRoles == null)
            return null;

        List<RoleEntity> roleEntities = new ArrayList<>();
        eRoles.forEach(eRole -> roleEntities.add(roleEntityRepository.findByName(eRole).orElseThrow()));

        return roleEntities;
    }
}
