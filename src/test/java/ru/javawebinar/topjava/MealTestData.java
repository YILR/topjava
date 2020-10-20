package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final List<Meal> meals = Arrays.asList(
            new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ).stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());

    public static final Meal MEAL = meals.get(6);
    public static final int MEAL_ID = 1;
    public static final LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 31);
    public static final LocalDate endDate = LocalDate.of(2020, Month.MARCH, 31);



    public static Meal getNew(){
        return new Meal(LocalDateTime.of(2020, Month.OCTOBER, 15, 13, 0), "Обед", 1000);
    }

    public static Meal getUpdated(){
        Meal updated = new Meal(MEAL);
        System.out.println(MEAL);
        updated.setDescription("Updated");
        updated.setCalories(666);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected){
        assertThat(actual).isEqualTo(expected);
    }
}
