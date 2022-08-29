package org.amin.fanoos.usermanagement.user.persistence;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class FlywaySlaveInitializer {

    private String firstDatasourceUrl = "jdbc:mysql://localhost:3306/fanoosdb?allowPublicKeyRetrieval=true&useSSL=false";
    private String firstDatasourceUser = "fanoosuser";
    private String firstDatasourcePassword = "fanoospass";

    private String secondDatasourceUrl = "jdbc:mysql://localhost:3366/fanoosdbtest?allowPublicKeyRetrieval=true&useSSL=false";
    private String secondDatasourceUser = "fanoosusertest";
    String secondDatasourcePassword = "fanoospasstest";

    @PostConstruct
    public void migrateFlyway() {
        Flyway mainFlyway = Flyway.configure()
                .dataSource(firstDatasourceUrl, firstDatasourceUser, firstDatasourcePassword)
                .locations("classpath:db/migration")
                .load();

        Flyway testFlyway = Flyway.configure()
                .dataSource(secondDatasourceUrl, secondDatasourceUser, secondDatasourcePassword)
                .locations("classpath:db/migration")
                .load();

        mainFlyway.migrate();
        testFlyway.migrate();
    }
}
