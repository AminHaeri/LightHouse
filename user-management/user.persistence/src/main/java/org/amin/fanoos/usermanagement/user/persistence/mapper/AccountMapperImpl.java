package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class AccountMapperImpl implements AccountMapper {

    private final RoleMapper roleMapper;

    public AccountMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Account toDomain(AccountEntity accountEntity) {
        if (accountEntity == null)
            return null;

        return Account.builder()
                .id(accountEntity.getUId())
                .userName(accountEntity.getUserName())
                .password(accountEntity.getPassword())
                .roles(roleMapper.toDomain(new ArrayList<>(accountEntity.getRoleEntities())))
                .build();
    }

    @Override
    public AccountEntity toEntity(Account account) {
        if (account == null)
            return null;

        return AccountEntity.builder()
                .uId(account.getId())
                .userName(account.getUserName())
                .password(account.getPassword())
                .roleEntities(new HashSet<>(roleMapper.toEntity(account.getRoles())))
                .build();
    }
}
