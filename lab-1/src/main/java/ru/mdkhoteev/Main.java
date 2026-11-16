package ru.mdkhoteev;

import ru.mdkhoteev.entity.Hero;
import ru.mdkhoteev.reader.HeroCsvReader;
import ru.mdkhoteev.service.OriginSpeciesService;
import ru.mdkhoteev.writer.OriginSpeciesWriter;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        HeroCsvReader heroCsvReader = new HeroCsvReader();
        List<Hero> heroes = heroCsvReader.readFromResource("/characters.csv");


        OriginSpeciesService originSpeciesService = new OriginSpeciesService();
        TreeMap<String, Set<String>> originToSpecies =
                originSpeciesService.groupSpeciesByOrigin(heroes);


        OriginSpeciesWriter originSpeciesWriter = new OriginSpeciesWriter();
        Path outputPath = Path.of("lab-1/src/main/resources/origin-spicies.txt");
        originSpeciesWriter.writeToFile(originToSpecies, outputPath);
    }
}
