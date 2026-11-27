package ru.mdkhoteev.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesService {

    private final Properties properties = new Properties();

    public PropertiesService() {
        try (InputStream inputStream = PropertiesService.class
                .getResourceAsStream("/application.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("application.properties не найден в ресурсах");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException("Ошибка чтения application.properties", e);
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalStateException("В application.properties не задан ключ " + key);
        }
        return value;
    }
}