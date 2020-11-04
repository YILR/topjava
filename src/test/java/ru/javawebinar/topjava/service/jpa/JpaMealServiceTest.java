package ru.javawebinar.topjava.service.jpa;


import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB, Profiles.JPA})
public class JpaMealServiceTest extends MealServiceTest{

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + JpaMealServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

}
