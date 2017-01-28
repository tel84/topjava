package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMapImpl;
import ru.javawebinar.topjava.model.Meal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final Logger LOG = getLogger(MealServlet.class);
    private MealDao mealDao = new MealDaoMapImpl();
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("inside MealServlet doGet method");
        String forward="";
        String action = request.getParameter("action");

        if (action == null){
            LOG.debug("display mealList");
            forward = LIST_MEAL;
            request.setAttribute("mealList", mealDao.getAllMeals());
        } else if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            LOG.debug("delete meal with id = " + id);
            mealDao.deleteMeal(id);
            forward = LIST_MEAL;
            request.setAttribute("mealList", mealDao.getAllMeals());

        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(request.getParameter("id"));
            LOG.debug("edit meal with id = " + id);
            Meal meal = mealDao.getMealById(id);
            request.setAttribute("meal", meal);
        } else {
            forward = INSERT_OR_EDIT;
        }

       request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("inside MealServlet doPost method");
        request.setCharacterEncoding("UTf-8");
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        String date = request.getParameter("dateTime");

        if (description!=null && !description.isEmpty() && calories!=null && !calories.isEmpty() && date!=null && !date.isEmpty()) {
            Meal meal  = new Meal();
            meal.setDescription(description);
            meal.setCalories(Integer.parseInt(calories));
            meal.setDateTime(LocalDateTime.parse(date.replace("T", " "), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            if (id == null || id.isEmpty()) {
                mealDao.addMeal(meal);
                LOG.debug("meal with id=" + meal.getId() + " added");
            } else {
                meal.setId(Integer.parseInt(id));
                mealDao.updateMeal(meal);
                LOG.debug("meal with id=" + meal.getId() + " updated");
            }
        }
        request.getSession().setAttribute("mealList", mealDao.getAllMeals());
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);

    }
}
