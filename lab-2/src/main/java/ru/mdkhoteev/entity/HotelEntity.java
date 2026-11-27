package ru.mdkhoteev.entity;

public class HotelEntity {

    private Integer id;
    private String name;
    private int stars;

    public HotelEntity() {
    }

    public HotelEntity(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }

    public HotelEntity(Integer id, String name, int stars) {
        this.id = id;
        this.name = name;
        this.stars = stars;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", stars=" + stars +
                '}';
    }
}