package ru.mdkhoteev;

import ru.mdkhoteev.entity.HotelEntity;
import ru.mdkhoteev.repository.ConnectionFactory;
import ru.mdkhoteev.repository.HotelRepositoryJdbc;
import ru.mdkhoteev.repository.PropertiesService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

        HotelRepositoryJdbc repository = new HotelRepositoryJdbc(connectionFactory);
        demonstrate(repository);
    }

    private static void demonstrate(HotelRepositoryJdbc repository) {
        int id = repository.save(new HotelEntity("Astoria", 5));
        System.out.println("save -> id = " + id);

        System.out.println("findById -> " + repository.findById(id));
        System.out.println("findByName -> " + repository.findByName("Astoria"));

        HotelEntity hotel = repository.findById(id);
        hotel.setStars(4);
        System.out.println("update -> " + repository.update(hotel));
        System.out.println("после update -> " + repository.findById(id));

        System.out.println("update несуществующего -> " + repository.update(new HotelEntity(999_999, "Ghost", 1)));

        List<HotelEntity> all = repository.findAll();
        System.out.println("findAll -> " + all);

        repository.deleteById(id);
        System.out.println("после deleteById -> findById = " + repository.findById(id));

        repository.deleteById(id);
        System.out.println("повторный deleteById не падает");
    }
}