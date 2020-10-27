package ru.javawebinar.topjava.service;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestLogger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Rule
    public final TestLogger logger = new TestLogger();

    @Autowired
    private MealService service;

    @Test
    public void delete() throws Exception {
        long startTime = System.nanoTime();
        service.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, USER_ID));

        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test delete in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void deleteNotFound() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test deleteNotFound in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void deleteNotOwn() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1_ID, ADMIN_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test deleteNotOwn in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void create() throws Exception {
        long startTime = System.nanoTime();
        Meal created = service.create(getNew(), USER_ID);
        int newId = created.id();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(service.get(newId, USER_ID), newMeal);
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test create in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void duplicateDateTimeCreate() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, meal1.getDateTime(), "duplicate", 100), USER_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test duplicateDateTimeCreate in time = {} ms", (endTime-startTime)/1000000);
    }


    @Test
    public void get() throws Exception {
        long startTime = System.nanoTime();
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertMatch(actual, admin_meal1);
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test get in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void getNotFound() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test getNotFound in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void getNotOwn() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, ADMIN_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test getNotOwn in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void update() throws Exception {
        long startTime = System.nanoTime();
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        MEAL_MATCHER.assertMatch(service.get(MEAL1_ID, USER_ID), getUpdated());
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test update in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void updateNotOwn() throws Exception {
        long startTime = System.nanoTime();
        assertThrows(NotFoundException.class, () -> service.update(meal1, ADMIN_ID));
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test updateNotOwn in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void getAll() throws Exception {
        long startTime = System.nanoTime();
        MEAL_MATCHER.assertMatch(service.getAll(USER_ID), meals);
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test getAll in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void getBetweenInclusive() throws Exception {
        long startTime = System.nanoTime();
        MEAL_MATCHER.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.JANUARY, 30),
                LocalDate.of(2020, Month.JANUARY, 30), USER_ID),
                meal3, meal2, meal1);
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test getBetweenInclusive in time = {} ms", (endTime-startTime)/1000000);
    }

    @Test
    public void getBetweenWithNullDates() throws Exception {
        long startTime = System.nanoTime();
        MEAL_MATCHER.assertMatch(service.getBetweenInclusive(null, null, USER_ID), meals);
        long endTime = System.nanoTime();
        Logger log = logger.getLogger();
        log.warn("Test getBetweenWithNullDates in time = {} ms", (endTime-startTime)/1000000);
    }
}