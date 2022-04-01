package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserDataEdit", urlPatterns = "/app/user/edit")
public class UserDataEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("currentUser");
        Admin userToUpdate = new Admin();

        userToUpdate.setId(admin.getId());
        userToUpdate.setFirstName(request.getParameter("firstName"));
        userToUpdate.setLastName(request.getParameter("lastName"));
        userToUpdate.setEmail(request.getParameter("email"));

        boolean result = AdminDao.update(userToUpdate);
        if (result) {

            session.setAttribute("currentUser", userToUpdate);

            response.sendRedirect(request.getContextPath() + "/app/dashboard");
        } else {
            request.setAttribute("errorMessage", "Użytkownik o podanym emailu już istnieje.");
            request.getRequestDispatcher("/app-edit-user-data.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/app-edit-user-data.jsp")
                .forward(request, response);
    }
}
