package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.MemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    static MealRepository repository = new MemoryMealRepository();

    {
MealsUtil.initMealsRepository(repository);
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("my redirect to meals");

        Map parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            switch ((String) parameterMap.get("action")) {
                case "update":
                    response.sendRedirect("update.jsp");
                    break;
                case "delete":
                    response.sendRedirect("delete.jsp");
            }
        } else {

            setMealsAndForward(request, response);
            //      response.sendRedirect("meals.jsp");
        }
    }
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterMap();
        int id = Integer.valueOf(request.getParameter( "id" ));
        log.debug("delete meal whitch id = "+id);
        try {
            repository.delete(id);
        } catch (ArrayIndexOutOfBoundsException e ){}
        setMealsAndForward(request, response);
    }

    private void setMealsAndForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", MealsUtil.getUserMeal());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}