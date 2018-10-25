package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal create (Meal meal);

    Meal get (int id);

    void delete (int id);

    void update (Meal meal);

    List<Meal> getAll();

  //  List<MealWithExceed> getMealWithExceed(LocalDate startDate, LocalTime startTime, LocalDate endtDate, LocalTime endTime);
}