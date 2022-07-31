package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;

public interface UserMapper {
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}
