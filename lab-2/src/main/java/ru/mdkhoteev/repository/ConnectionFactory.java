package ru.mdkhoteev.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private final String url;
    private final String user;
    private final String password;

    public ConnectionFactory() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConnectionFactory.class
                .getResourceAsStream("/application.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("Файл properties не найден");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException("Ошибка чтения properties", e);
        }

        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        password = System.getenv("DATABASE_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Env DATABASE_PASSWORD не задана"
            );
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}