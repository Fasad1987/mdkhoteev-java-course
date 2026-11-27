package ru.mdkhoteev;

import ru.mdkhoteev.repository.ConnectionFactory;
import ru.mdkhoteev.repository.PropertiesService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        PropertiesService properties = new PropertiesService();

        String password = System.getenv("DATABASE_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("Env DATABASE_PASSWORD не задана");
        }

        ConnectionFactory connectionFactory = new ConnectionFactory(
                properties.get("db.url"),
                properties.get("db.user"),
                password
        );

        try (Connection connection = connectionFactory.getConnection()) {
            if (connection.isValid(5)) {
                System.out.println("Подключение к БД успешно");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка подключения к БД", e);
        }
    }
}