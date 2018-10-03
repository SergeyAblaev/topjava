package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> result = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        System.out.println(result);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        //   List<UserMeal> filteredlist = mealList.stream().filter(el->el.getTime().isBefore(endTime)&&el.getTime().isAfter(startTime)).collect(Collectors.toList());
        List<UserMeal> filteredlist = mealList.stream().filter(el -> el.getTime().isBefore(endTime) && el.getTime().isAfter(startTime)).collect(Collectors.toList());
        //   Map<Date,Integer> map = mealList.stream().collect(Collectors.groupingBy(UserMeal->)) ;  How?!

        Map<LocalDate, Integer> mymap = new TreeMap<>();
        for (UserMeal el : mealList
        ) {
            Integer caloriesFromDate = mymap.get(el.getDate());
            caloriesFromDate = (caloriesFromDate == null)? 0:caloriesFromDate ;
            mymap.put(el.getDate(), (Integer) el.getCalories() + caloriesFromDate );
        }

        Map<LocalDate, Integer> mymap2 = mealList.stream().collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));




        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        for (UserMeal el : filteredlist
        ) {
            boolean exceed = false;
            if (mymap.get(el.getDate()) > 2000) exceed = true;
            userMealWithExceedList.add(new UserMealWithExceed(el.getDateTime(), el.getDescription(), el.getCalories(), exceed));
        }

        return userMealWithExceedList;
    }
}
