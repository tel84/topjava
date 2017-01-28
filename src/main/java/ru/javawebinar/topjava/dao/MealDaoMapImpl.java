package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMapImpl implements MealDao{
    private Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private AtomicInteger atomic = new AtomicInteger(0);


    {MealsUtil.meals.forEach(m -> {
        int id = atomic.getAndIncrement();
        m.setId(id);
        storage.put(id, m);});
    }

    @Override
    public void addMeal(Meal meal) {
        if (Objects.nonNull(meal))
        {
            int id = atomic.getAndIncrement();
            meal.setId(id);
            storage.put(id, meal);}
    }

    @Override
    public void updateMeal(Meal meal) {
        if (Objects.nonNull(meal)) {
            storage.put(meal.getId(), meal);
        }
    }

    @Override
    public void deleteMeal(int id) {
        storage.remove(id);
    }

    @Override
    public List<MealWithExceed> getAllMeals() {
        return MealsUtil.getFilteredWithExceeded(new ArrayList<>(storage.values()), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public Meal getMealById(int id) {
        return storage.get(id);
    }
}
