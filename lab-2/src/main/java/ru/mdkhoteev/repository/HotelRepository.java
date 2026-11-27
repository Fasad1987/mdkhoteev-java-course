package ru.mdkhoteev.repository;

import ru.mdkhoteev.entity.HotelEntity;

import java.util.List;

public interface HotelRepository {

    int save(HotelEntity entity);

    HotelEntity findById(int id);

    HotelEntity findByName(String name);

    List<HotelEntity> findAll();

    boolean update(HotelEntity entity);

    void deleteById(int id);
}