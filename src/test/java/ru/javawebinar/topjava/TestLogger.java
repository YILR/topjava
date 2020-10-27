package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.service.MealServiceTest;

public class TestLogger implements TestRule {
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                logger = LoggerFactory.getLogger(MealServiceTest.class);
                statement.evaluate();
            }
        };
    }
}
