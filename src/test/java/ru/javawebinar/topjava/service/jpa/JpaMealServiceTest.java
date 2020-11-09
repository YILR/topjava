package ru.javawebinar.topjava.service.jpa;


import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + JpaMealServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

}
