package org.amin.fanoos.usermanagement.user.persistence.seed;

import java.util.ArrayList;
import java.util.List;

public class CompositeSeeder {
    private final List<Seeder> seeders = new ArrayList<>();

    public void addSeeder(Seeder seeder) {
        seeders.add(seeder);
    }

    public void runSeeders() {
        seeders.forEach(Seeder::seed);
    }
}
