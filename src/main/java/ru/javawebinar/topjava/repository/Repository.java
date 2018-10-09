package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface Repository {
    int create(LocalDateTime datetime, String descript, int calories);
    Meal read(int id);
    void update(int id);
    void delete(int id);
}
