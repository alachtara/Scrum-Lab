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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");

        currentUser.setFirstName(request.getParameter("firstName"));
        currentUser.setLastName(request.getParameter("lastName"));
        currentUser.setEmail(request.getParameter("email"));

        AdminDao.update(currentUser);

        response.sendRedirect(request.getContextPath() + "/app/dashboard");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
      
        String userEmail = request.getParameter("email");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
        String currentUserFirstName = currentUser.getFirstName();
        request.setAttribute("currentUserFirstName", currentUserFirstName);

//        HttpSession session = request.getSession();
//        Admin currentUser = (Admin) session.getAttribute("currentUser");

//        String currentUserFirstName = currentUser.getFirstName();
//        request.setAttribute("currentUserFirstName", currentUserFirstName);
//
//        String currentUserLastName = currentUser.getLastName();
//        request.setAttribute("currentUserFirstName", currentUserLastName);
//
//        String currentUserEmail = currentUser.getEmail();
//        request.setAttribute("currentUserFirstName", currentUserEmail);
        request.setAttribute("currentUser",currentUser);


        getServletContext().getRequestDispatcher("/app-edit-user-data.jsp")
                .forward(request, response);
    }
}
