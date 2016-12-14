package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (UserMealWithExceed meal : filteredWithExceeded) {
            System.out.println(meal);
        }
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map< LocalDate, Integer> localeDateMap = new HashMap<>();
        List<UserMeal> mealsBetweenStartTimeAndEndTime = new ArrayList<>();
        for (UserMeal meal : mealList) {
            Integer calories = localeDateMap.putIfAbsent(meal.getDateTime().toLocalDate(), meal.getCalories());
            if (calories != null)
                localeDateMap.put(meal.getDateTime().toLocalDate(), meal.getCalories() + calories);
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                mealsBetweenStartTimeAndEndTime.add(meal);
        }
        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal meal : mealsBetweenStartTimeAndEndTime)
            result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), caloriesPerDay < localeDateMap.get(meal.getDateTime().toLocalDate())));

        return result;
    }
}
