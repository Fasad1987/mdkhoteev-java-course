package ru.mdkhoteev.repository;

import org.flywaydb.core.Flyway;

public class DatabaseMigrator {
    private final String url;
    private final String user;
    private final String password;
    private final String schema;

    public DatabaseMigrator(String url, String user, String password, String schema) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.schema = schema;
    }

    public void runMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .schemas(schema)
                .defaultSchema(schema)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
        System.out.println("Миграции применены");
    }
}