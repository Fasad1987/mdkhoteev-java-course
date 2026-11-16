package ru.mdkhoteev.service;

import ru.mdkhoteev.entity.Hero;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class OriginSpeciesService {
    public TreeMap<String, Set<String>> groupSpeciesByOrigin(List<Hero> heroes) {
        TreeMap<String, Set<String>> originToSpecies = new TreeMap<>();
        for (Hero hero : heroes) {
            originToSpecies
                    .computeIfAbsent(hero.getOrigin(), origin -> new TreeSet<>())
                    .add(hero.getSpecies());
        }
        return originToSpecies;
    }
}
