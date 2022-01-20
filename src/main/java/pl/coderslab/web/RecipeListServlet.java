package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RecipeListServlet", urlPatterns = "/app/recipe/list")
public class RecipeListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipes", recipeDao.findAllByAdminId(currentUser));

        getServletContext().getRequestDispatcher("/app-recipes.jsp").forward(request, response);
    }
}
