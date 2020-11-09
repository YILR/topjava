package ru.javawebinar.topjava.service.jdbc;

import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + JdbcUserServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }
}
