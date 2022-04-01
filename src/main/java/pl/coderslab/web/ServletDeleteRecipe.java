package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletDeleteRecipe",urlPatterns = "/app/recipe/delete")
public class ServletDeleteRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeID = Integer.parseInt(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        boolean deleted = recipeDao.delete(recipeID);
        request.setAttribute("deleted", deleted);

        getServletContext().getRequestDispatcher("/app/recipe/list").forward(request,response);
    }
}
