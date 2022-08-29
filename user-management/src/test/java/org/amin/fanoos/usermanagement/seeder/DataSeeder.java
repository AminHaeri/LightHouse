package org.amin.fanoos.usermanagement.seeder;

import org.amin.fanoos.usermanagement.user.persistence.seed.CompositeSeeder;
import org.amin.fanoos.usermanagement.user.persistence.seed.RoleDataSeeder;
import org.amin.fanoos.usermanagement.user.persistence.seed.UserDataSeeder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {
    private final RoleDataSeeder roleDataSeeder;
    private final UserDataSeeder userDataSeeder;

    public DataSeeder(RoleDataSeeder roleDataSeeder, UserDataSeeder userDataSeeder) {
        this.roleDataSeeder = roleDataSeeder;
        this.userDataSeeder = userDataSeeder;
    }

    public void run() {
        CompositeSeeder compositeSeeder = new CompositeSeeder();
        compositeSeeder.addSeeder(roleDataSeeder);
        compositeSeeder.addSeeder(userDataSeeder);

        compositeSeeder.runSeeders();
    }
}
