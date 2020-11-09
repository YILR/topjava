package ru.javawebinar.topjava.service.jpa;

import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + JpaUserServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }
}
