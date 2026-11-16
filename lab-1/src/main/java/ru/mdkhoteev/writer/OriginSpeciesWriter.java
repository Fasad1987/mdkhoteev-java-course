package ru.mdkhoteev.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class OriginSpeciesWriter {
    public void writeToFile(TreeMap<String, Set<String>> originToSpecies, Path outputPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {

            for (Map.Entry<String, Set<String>> entry : originToSpecies.entrySet()) {
                writer.write(entry.getKey() + " -> " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи " + outputPath + e.getMessage());
            throw new UncheckedIOException(e);
        }

    }
}
