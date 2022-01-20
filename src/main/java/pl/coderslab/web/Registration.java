package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.Year;
import java.util.Enumeration;

@WebServlet("/register")
public class Registration extends HttpServlet {

    public static void main(String[] args) {

    }

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

        boolean emailExist = AdminDao.checkIfEmailExist(emailForm);
        Object result = null;
        if (emailExist) {
            request.setAttribute("errorMessage", "Użytkownik o podanym emailu już istnieje.");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            Admin admin = new Admin();
            admin.setFirstName(nameForm);
            admin.setLastName(surnameForm);
            admin.setEmail(emailForm);
            admin.setPassword(passwordForm);

            result = AdminDao.create(admin);
        }

        if (result == null) {
            request.setAttribute("errorMessage", "Rejestracja nie przebiegła poprawnie. Spróbuj ponownie");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }

        response.sendRedirect("/login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);

    }

}
