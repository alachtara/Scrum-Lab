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
import java.util.List;

@WebServlet(name = "ServletPlansList",urlPatterns = "/app/plan/list")
public class ServletPlansList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");

        PlanDao planDao = new PlanDao();
        List<Plan> plans=planDao.findAll(currentUser);

        request.setAttribute("plans",plans);

        getServletContext().getRequestDispatcher("/planslist.jsp").forward(request,response);
    }
}
