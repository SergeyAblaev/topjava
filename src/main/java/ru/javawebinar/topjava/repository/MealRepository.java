package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    int create(LocalDateTime datetime, String descript, int calories);
    Meal read(int id);
    void update(int id);
    void delete(int id);
    List<Meal> getAllMeals ();
}
