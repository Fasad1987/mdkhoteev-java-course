package ru.mdkhoteev;

import ru.mdkhoteev.entity.HotelEntity;
import ru.mdkhoteev.repository.ConnectionFactory;
import ru.mdkhoteev.repository.DatabaseMigrator;
import ru.mdkhoteev.repository.JdbcHotelRepository;
import ru.mdkhoteev.repository.PropertiesService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PropertiesService properties = new PropertiesService();

        String password = System.getenv("DATABASE_PASSWORD");
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("Env DATABASE_PASSWORD не задана");
        }

        String url = properties.get("db.url");
        String user = properties.get("db.user");
        String schema = properties.get("db.schema");

        DatabaseMigrator databaseMigrator = new DatabaseMigrator(url, user, password, schema);
        databaseMigrator.runMigrations();

        ConnectionFactory connectionFactory = new ConnectionFactory(url, user, password, schema);

        try {
            JdbcHotelRepository repository = new JdbcHotelRepository(connectionFactory);
            demonstrate(repository);
        } finally {
            connectionFactory.close();
        }
    }

    private static void demonstrate(JdbcHotelRepository repository) {
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