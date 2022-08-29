package org.amin.fanoos.usermanagement.user.persistence.seed;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.amin.fanoos.usermanagement.user.persistence.repository.RoleEntityRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleDataSeeder implements Seeder {

    private final RoleEntityRepository repository;

    public RoleDataSeeder(RoleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void seed() {
        for (ERole role : ERole.values()) {
            if (repository.findByName(role).isEmpty()) {
                RoleEntity roleEntity = new RoleEntity(UUID.randomUUID(), role);

                repository.save(roleEntity);
            }
        }
    }
}
