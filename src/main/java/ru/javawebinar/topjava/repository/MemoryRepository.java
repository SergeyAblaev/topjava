package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryRepository implements Repository {

    static List MEALS = new ArrayList<>();

    List<Meal> repository = MemoryRepository.MEALS;

    @Override
    public synchronized int create(LocalDateTime datetime, String descript, int calories) {  // synchronized - this correct?
        repository.add(new Meal(datetime,descript,calories,repository.size()));
        return repository.size();  //-1
    }

    @Override
    public Meal read(int id) {
        return repository.get(id);
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {
        synchronized (MEALS){      // or this correct?
        repository.remove(id);
        }

    }
}
