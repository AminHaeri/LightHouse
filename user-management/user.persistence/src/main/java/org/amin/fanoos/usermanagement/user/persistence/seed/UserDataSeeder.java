package org.amin.fanoos.usermanagement.user.persistence.seed;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.persistence.entity.AccountEntity;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.amin.fanoos.usermanagement.user.persistence.entity.UserEntity;
import org.amin.fanoos.usermanagement.user.persistence.repository.RoleEntityRepository;
import org.amin.fanoos.usermanagement.user.persistence.repository.UserEntityRepository;
import org.amin.fanoos.usermanagement.user.persistence.security.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
public class UserDataSeeder implements Seeder {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataSeeder(UserEntityRepository userEntityRepository,
                          RoleEntityRepository roleEntityRepository,
                          PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seed() {
        if (userEntityRepository.findByAccountEntity_RoleEntities_Name(ERole.ROLE_SUPERADMIN).isEmpty() &&
                roleEntityRepository.findByName(ERole.ROLE_SUPERADMIN).isPresent()) {
            RoleEntity roleEntity = roleEntityRepository.findByName(ERole.ROLE_SUPERADMIN).get();

            AccountEntity accountEntity = new AccountEntity(
                    UUID.randomUUID(),
                    "aminhaeri",
                    passwordEncoder.hashedPassword("fanoos-aminhaeri"),
                    new HashSet<>(List.of(roleEntity)));

            UserEntity userEntity = new UserEntity(
                    UUID.randomUUID(),
                    "aminhaeri@mail.com",
                    "amin",
                    "haeri",
                    accountEntity);

            userEntityRepository.save(userEntity);
        }
    }
}
