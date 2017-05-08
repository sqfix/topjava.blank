package ru.javawebinar.topjava.repository.mock;

import com.google.common.collect.Lists;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GKislin
 * 15.09.2015.
 */

public class InMemoryMealRepositoryImpl implements MealRepository {
    /**
     * A food belongs users!
     * In local repository saving as Map <userId, List<Meal>>
     */
    private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    //private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        List<Meal> meals = repository.get(userId);
        if (meals == null) {
            meals = new ArrayList<>();
            meal.setId(1);
            meals.add(meal);
            repository.put(userId, meals);
        } else {
            meal.setId(meals.size() + 1);
            meals.add(meal);
            repository.put(userId, meals);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) throws NullPointerException {
        List<Meal> meals = repository.get(userId);
        for (Meal m : meals) {
            if (m.getId() == id) {
                meals.remove(m);
                repository.put(userId, meals);
                return true;
            }
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = repository.get(userId);
        for (Meal m : meals) {
            if (m.getId() == id) {
                return m;
            }
        }
        throw new NotFoundException("Meal is not found!");
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        List<Meal> meals = repository.get(userId);
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return Lists.reverse(meals);
    }
}

