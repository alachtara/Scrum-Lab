package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddPlan", urlPatterns = "/app/plan/add")
public class AddPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");

        Plan plan = new Plan();
        plan.setName(request.getParameter("planName"));
        plan.setDescription(request.getParameter("planDescription"));

        PlanDao planDao = new PlanDao();
        planDao.create(plan,currentUser);

        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app-add-schedules.jsp")
                .forward(request, response);
    }
}
