package org.amin.fanoos.usermanagement.user.persistence.repository;

import org.amin.fanoos.usermanagement.user.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {
}