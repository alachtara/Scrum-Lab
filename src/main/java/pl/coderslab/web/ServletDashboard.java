package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LatestPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/app/dashboard")
public class ServletDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
        String currentUserFirstName = currentUser.getFirstName();
        request.setAttribute("currentUserFirstName", currentUserFirstName);

        int numberOfRecipes = RecipeDao.recipesCounter(currentUser);
        request.setAttribute("numberOfRecipes", numberOfRecipes);

        int numberOfPlans = PlanDao.numberOfPlans(currentUser);
        request.setAttribute("numberOfPlans", numberOfPlans);

        String nameOfLatestPlan = PlanDao.readLastPlanName(currentUser);
        request.setAttribute("nameOfLatestPlan", nameOfLatestPlan);

        Map<String, List<LatestPlan>> mapaPosilkow = PlanDao.latestPlanMap(currentUser);
        request.setAttribute("mapaPosilkow", mapaPosilkow);

        getServletContext().getRequestDispatcher("/userzone/dashboard.jsp").forward(request, response);
    }
}
