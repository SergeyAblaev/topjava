package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealRestController {
    static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        return service.create(meal);
    }


    public Meal get(int id) {
        return service.get(id);
    }


    public void delete(int id) {
        service.delete(id);
    }


    public void update(Meal meal) {
        service.update(meal);
    }


    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }


}