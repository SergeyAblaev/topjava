package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

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
            ;
        } else
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
            response.sendRedirect("meals.jsp");
    }
}
