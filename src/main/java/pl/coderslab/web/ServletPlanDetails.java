package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ServletPlanDetails",urlPatterns = "/app/plan/details")
public class ServletPlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
        String currentUserFirstName = currentUser.getFirstName();
        request.setAttribute("currentUserFirstName", currentUserFirstName);


        int planID = Integer.parseInt(request.getParameter("planID"));
        Plan plan = PlanDao.read(planID);

        Map<String, List<LatestPlan>> day_meal = PlanDao.showPlanDetails(plan);

        request.setAttribute("plan",plan);
        request.setAttribute("day_meal",day_meal);

        getServletContext().getRequestDispatcher("/plandetails.jsp").forward(request,response);
    }
}
