package ru.javawebinar.topjava.service.datajpa;

import org.junit.AfterClass;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest " + DataJpaUserServiceTest.class.getSimpleName() +" Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }
}
