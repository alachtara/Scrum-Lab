package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Admin currentUser = AdminDao.read(email);
        if (BCrypt.checkpw(password, currentUser.getPassword()) && currentUser.getEnable()==1) {
            session.setAttribute("currentUser", currentUser);
            response.sendRedirect(request.getContextPath() + "/app/dashboard");
        } else if (!BCrypt.checkpw(password, currentUser.getPassword())) {
            request.setAttribute("userMessage", "Błędne logowanie, spróbuj ponownie");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (currentUser.getEnable()!=1) {
            request.setAttribute("userMessage", "Użytkownik zablokowany");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
