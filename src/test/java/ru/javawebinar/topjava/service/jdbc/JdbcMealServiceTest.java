package ru.javawebinar.topjava.service.jdbc;


import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + JdbcMealServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

}
