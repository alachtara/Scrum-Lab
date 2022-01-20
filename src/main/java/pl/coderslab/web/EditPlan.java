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

@WebServlet(name = "EditPlan", urlPatterns = "/app/plan/edit" )
public class EditPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
        String currentUserFirstName = currentUser.getFirstName();
        request.setAttribute("currentUserFirstName", currentUserFirstName);

        Plan plan = new Plan();
        plan.setId(Integer.parseInt(request.getParameter("id")));
        plan.setName(request.getParameter("planName"));
        plan.setDescription(request.getParameter("planDescription"));

        PlanDao.update(plan);
//        response.sendRedirect(request.getContextPath() + "/app/plan/list");
        response.sendRedirect("/app/plan/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String planId = request.getParameter("planID");

        Plan read = PlanDao.read(Integer.parseInt(planId));
        request.setAttribute("plan", read);
        getServletContext().getRequestDispatcher("/app-edit-schedules.jsp").forward(request, response);
    }
}
