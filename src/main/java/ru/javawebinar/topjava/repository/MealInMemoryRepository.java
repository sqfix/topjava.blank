package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Max on 07.05.2017.
 */
public class MealInMemoryRepository implements MealRepository {
    private ConcurrentHashMap<Integer, Meal> mealRepository = new ConcurrentHashMap<Integer, Meal>() {{
        put(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        put(2, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        put(3, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        put(4, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        put(5, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        put(6, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }};

    @Override
    public void create(Meal meal) {
        mealRepository.put(mealRepository.size() + 1, meal);
    }

    @Override
    public Meal read(int id) {
        return mealRepository.get(id);
    }

    @Override
    public void update(Meal meal, int id) {
        mealRepository.put(id, meal);
    }

    @Override
    public void delete(int id) {
        mealRepository.remove(id);
    }
}
