package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Максим on 20.01.2017.
 */
public class MealServlet extends HttpServlet {
    public static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("display mealsWithExcededList");
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.MIN, LocalTime.MAX, 2000);
        request.getSession().setAttribute("mealList", filteredWithExceeded);

       request.getRequestDispatcher("/meals.jsp").forward(request, response);
       // response.sendRedirect("meals.jsp");
    }
}
