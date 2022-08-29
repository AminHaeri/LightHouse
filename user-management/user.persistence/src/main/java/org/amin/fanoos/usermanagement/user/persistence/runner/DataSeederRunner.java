package org.amin.fanoos.usermanagement.user.persistence.runner;

import org.amin.fanoos.usermanagement.user.persistence.seed.CompositeSeeder;
import org.amin.fanoos.usermanagement.user.persistence.seed.RoleDataSeeder;
import org.amin.fanoos.usermanagement.user.persistence.seed.UserDataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeederRunner implements CommandLineRunner {

    private final RoleDataSeeder roleDataSeeder;
    private final UserDataSeeder userDataSeeder;

    public DataSeederRunner(RoleDataSeeder roleDataSeeder, UserDataSeeder userDataSeeder) {
        this.roleDataSeeder = roleDataSeeder;
        this.userDataSeeder = userDataSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        CompositeSeeder compositeSeeder = new CompositeSeeder();
        compositeSeeder.addSeeder(roleDataSeeder);
        compositeSeeder.addSeeder(userDataSeeder);

        compositeSeeder.runSeeders();
    }
}
