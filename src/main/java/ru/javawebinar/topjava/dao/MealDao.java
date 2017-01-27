package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {
    void addMeal(Meal meal);
    void updateMeal(Meal meal);
    void deleteMeal(int id);
    List<MealWithExceed> getAllMeals();
    Meal getMealById(int id);
}
