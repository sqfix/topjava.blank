package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Max on 07.05.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");


        req.setAttribute("meals",
                MealsUtil.getFilteredWithExceeded(MealsUtil.getTestHardCodedMealList(),
                        LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        //resp.sendRedirect("meals.jsp");
    }
}
