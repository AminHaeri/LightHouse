package org.amin.fanoos.usermanagement.user.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.out.UserPort;
import org.amin.fanoos.usermanagement.user.application.port.out.command.UserInfoCommand;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;
import org.amin.fanoos.usermanagement.user.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@Component
public class UserRepository implements UserPort {

    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserRepository(
            UserEntityRepository userEntityRepository,
            UserMapper userMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUserName(UserInfoCommand userInfoCommand) {
        UserEntity userEntity =
                userEntityRepository.findByAccountEntity_UserName(userInfoCommand.userName()).orElse(null);

        return userMapper.toDomain(userEntity);
    }
}
