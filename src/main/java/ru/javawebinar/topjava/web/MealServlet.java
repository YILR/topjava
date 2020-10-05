package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        String path;
        String action = req.getParameter("action");

        if (action != null && action.equalsIgnoreCase("update")) {
            path = "updateMeal.jsp";
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = MealsUtil.getMealMap().get(id);
            req.setAttribute("meal", meal);

        } else if (action != null && action.equalsIgnoreCase("delete")) {
            path = "/meals.jsp";
            int id = Integer.parseInt(req.getParameter("id"));
            MealsUtil.getMealMap().remove(id);
            List<MealTo> meals = MealsUtil.filterBy();
            req.setAttribute("meals", meals);

        } else {
            path = "/meals.jsp";
            List<MealTo> meals = MealsUtil.filterBy();
            req.setAttribute("meals", meals);
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to updateMeal");
        req.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        String id = req.getParameter("id");
        log.debug(id);

        if (id == null || id.isEmpty())
            MealsUtil.add(new Meal(dateTime, description, calories, MealsUtil.getId()));
        else
            MealsUtil.update(Integer.parseInt(id), new Meal(dateTime, description, calories, Integer.parseInt(id)));

        List<MealTo> meals = MealsUtil.filterBy();
        req.setAttribute("meals", meals);

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
