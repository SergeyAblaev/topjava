package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemoryMealRepository   implements MealRepository {

    static List<Meal> repository = new ArrayList<>();

    @Override
    public synchronized int create(LocalDateTime datetime, String descript, int calories) {  // synchronized - this correct?
        repository.add(new Meal(datetime,descript,calories,repository.size()));
        return repository.size();  //-1
    }

    @Override
    public Meal read(int id) throws ArrayIndexOutOfBoundsException  {
        return repository.get(id);
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id)  throws ArrayIndexOutOfBoundsException {
        synchronized (repository) {      // or this correct?
        repository.remove(id);
        }


    }

    @Override
    public List<Meal> getAllMeals() {
        return repository;
    }


}
