package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletRecipeDetails",urlPatterns = "/app/recipe/details")
public class ServletRecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeId= Integer.parseInt(request.getParameter("id"));

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe=recipeDao.read(recipeId);

        request.setAttribute("recipe",recipe);


  getServletContext().getRequestDispatcher("/recipedetails.jsp").forward(request,response);
    }
}
