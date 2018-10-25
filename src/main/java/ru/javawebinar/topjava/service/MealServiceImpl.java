package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);    // create update ОДИНАКОВЫ  - верно ли?
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }

/*    @Override
    public List<Meal> getMealWithExceed(LocalDate startDate, LocalTime startTime, LocalDate endtDate, LocalTime endTime) {
        return null;
    }*/
}