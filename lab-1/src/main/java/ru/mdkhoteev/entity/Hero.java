package ru.mdkhoteev.entity;

import java.time.Instant;

public class Hero {
    private final int id;
    private final String name;
    private final String status;
    private final String species;
    private final String type;
    private final String gender;
    private final String origin;
    private final String location;
    private final Instant created;

    public Hero(int id, String name, String status, String species,
                String type, String gender, String origin,
                String location, Instant created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLocation() {
        return location;
    }

    public Instant getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", origin='" + origin + '\'' +
                ", location='" + location + '\'' +
                ", created=" + created +
                '}';
    }
}
