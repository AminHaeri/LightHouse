package org.amin.fanoos.usermanagement.user.persistence.seed;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.persistence.entity.RoleEntity;
import org.amin.fanoos.usermanagement.user.persistence.repository.RoleEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleDataSeeder implements CommandLineRunner {

    private final RoleEntityRepository repository;

    public RoleDataSeeder(RoleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (Role.ERole role : Role.ERole.values()) {
            if (repository.findByName(role).isEmpty()) {
                RoleEntity roleEntity = new RoleEntity(UUID.randomUUID(), role);

                repository.save(roleEntity);
            }
        }
    }
}
