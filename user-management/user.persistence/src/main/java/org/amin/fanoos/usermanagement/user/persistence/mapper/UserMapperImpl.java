package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    private final AccountMapper accountMapper;

    public UserMapperImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public User toDomain(UserEntity userEntity) {
        if (userEntity == null)
            return null;

        return User.builder()
                .id(userEntity.getUId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .account(accountMapper.toDomain(userEntity.getAccountEntity()))
                .build();
    }

    @Override
    public UserEntity toEntity(User user) {
        if (user == null)
            return null;

        return UserEntity.builder()
                .uId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .accountEntity(accountMapper.toEntity(user.getAccount()))
                .build();
    }
}
