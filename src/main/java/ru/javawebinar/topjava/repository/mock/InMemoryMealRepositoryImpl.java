package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id) {
        Meal meal = repository.get(id);
        if (meal.getUserId() == SecurityUtil.authUserId())
        repository.remove(id);
    }

    @Override
    public Meal get(int id) {
        Meal meal = repository.get(id);
        if (meal.getUserId() == SecurityUtil.authUserId())
            return meal;
        else return null;
    }

    @Override
    public List<Meal> getAll() {
    //    Collection<Meal> myList= repository.values().stream().filter(Meal->Meal.getUserId()>1).sorted().collect(Collectors.toCollection())  ;    // ПРОВЕРЬ!!! реализацию Григория?!!
        //repository.values().stream().sorted((Meal, o2) -> {Meal.getDate()})

/*        List list;
        if (coll instanceof List)
            list = (List)coll;
        else
            list = new ArrayList(coll);*/

        return new ArrayList(repository.values());
    }
}
