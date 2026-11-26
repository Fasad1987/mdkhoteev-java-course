package ru.mdkhoteev;

import ru.mdkhoteev.repository.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection connection = connectionFactory.getConnection()) {
            if (connection.isValid(5)) {
                System.err.println("Подключение к БД успешно");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
