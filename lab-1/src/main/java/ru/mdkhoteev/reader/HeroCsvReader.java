package ru.mdkhoteev.reader;

import ru.mdkhoteev.entity.Hero;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class HeroCsvReader {
    private static final String SEPARATOR = ",";
    private static final int COLUMNS_COUNT = 9;

    public List<Hero> readFromResource(String resourceName) {
        InputStream inputStream = HeroCsvReader.class.getResourceAsStream(resourceName);
        if (inputStream == null) {
            throw new IllegalStateException("Ресурс не найден:" + resourceName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.readLine();
            List<Hero> heroes = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                heroes.add(parseLine(line));
            }
            return heroes;
        } catch (IOException e) {
            System.err.println("Ошибка чтения ресурса " + resourceName + ":" +
                    e.getMessage());
            throw new UncheckedIOException(e);
        }
    }

    private Hero parseLine(String line) {
        String[] columns = line.split(SEPARATOR, -1);
        if (columns.length != COLUMNS_COUNT) {
            throw new IllegalArgumentException("Ожидание длины: " + COLUMNS_COUNT + " Получена длина: " + columns.length + ":" + line);
        }

        return new Hero(
                Integer.parseInt(columns[0]),
                columns[1],
                columns[2],
                columns[3],
                columns[4],
                columns[5],
                columns[6],
                columns[7],
                Instant.parse(columns[8])
        );
    }
}

