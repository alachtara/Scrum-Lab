package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePlanServlet", value = "/app/plan/delete")
public class DeletePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planID = Integer.parseInt(request.getParameter("planID"));
        PlanDao planDao = new PlanDao();
        boolean deleted = planDao.delete(planID);
        request.setAttribute("deleted", deleted);

        getServletContext().getRequestDispatcher("/app/plan/list").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
