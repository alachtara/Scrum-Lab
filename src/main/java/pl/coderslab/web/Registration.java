package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class Registration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameForm = request.getParameter("name");
        String surnameForm = request.getParameter("surname");
        String emailForm = request.getParameter("email");
        String passwordForm = request.getParameter("password");
        String repasswordForm = request.getParameter("repassword");

        if (!passwordForm.equals(repasswordForm)) {
            request.setAttribute("errorMessage", "Hasła nie są jednakowe. Spróbuj ponownie.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }


        Admin admin = new Admin();
        admin.setFirstName(nameForm);
        admin.setLastName(surnameForm);
        admin.setEmail(emailForm);
        admin.setPassword(passwordForm);
        Admin result = AdminDao.create(admin);

        if (result == null) {
            request.setAttribute("errorMessage", "Rejestracja nie przebiegła poprawnie. Spróbuj ponownie");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        response.sendRedirect("/login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

    }

}
