package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

/**
 * Created by Max on 07.05.2017.
 */
public interface MealRepository {

    //create/read/update/delete
    void create(Meal meal);

    Meal read(int id);

    void update(Meal meal, int id);

    void delete(int id);
}
