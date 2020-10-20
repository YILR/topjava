package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, UserTestData.USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
//        assertTrue(created.equals(newMeal));
//        assertTrue(service.get(newId, UserTestData.USER_ID).equals(newMeal));
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, UserTestData.USER_ID), newMeal);
    }


    @Test
    public void delete() {
        service.delete(MEAL_ID, UserTestData.USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, UserTestData.USER_ID));
    }

    @Test
    public void deleteNotFound(){
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID, UserTestData.NOT_FOUND));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, UserTestData.USER_ID);
//        assertTrue(service.get(updated.getId(), UserTestData.USER_ID).equals(updated));
        assertMatch(service.get(updated.getId(), UserTestData.USER_ID), updated);
    }

    @Test
    public void duplicateDateCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 1000), UserTestData.USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(startDate, endDate, UserTestData.USER_ID);
        List<Meal> meals = MealTestData.meals.stream().filter(meal -> Util.isBetweenHalfOpen(meal.getDate(), startDate, endDate)).collect(Collectors.toList());
//        all.equals(meals);
        assertMatch(meals, all);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, UserTestData.USER_ID);
//        meal.equals(meal);
        assertMatch(MEAL, meal);
    }

    @Test
    public void getNotFound(){
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, UserTestData.NOT_FOUND));
    }


    @Test
    public void getAll() {
        List<Meal> all = service.getAll(UserTestData.USER_ID);
        assertMatch(meals, all);
//        all.equals(meals);
    }




}