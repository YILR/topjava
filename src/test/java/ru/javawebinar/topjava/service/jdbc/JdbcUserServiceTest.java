package ru.javawebinar.topjava.service.jdbc;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.TestMatcher;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Test
    @Ignore
    @Override
    public void createWithException() {
        super.createWithException();
    }

    @Test
    public void testRoles(){
        TestMatcher<User> userTestMatcher =  TestMatcher.usingIgnoringFieldsComparator("registered", "meals");
        List<User> all = service.getAll();
        userTestMatcher.assertMatch(all, UserTestData.admin, UserTestData.user);
    }
}