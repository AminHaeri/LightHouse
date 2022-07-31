package org.amin.fanoos.usermanagement.user.persistence.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.persistence.entity.AccountEntity;

public interface AccountMapper {
    Account toDomain(AccountEntity accountEntity);
    AccountEntity toEntity(Account account);
}
