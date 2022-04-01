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

@WebServlet(name = "ServletEditRecipe",urlPatterns = "/app/recipe/edit")
public class ServletEditRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String recipeName = request.getParameter("recipe_name");
        String recipeDescription = request.getParameter("recipe_description");
        int preparationTime = Integer.parseInt(request.getParameter("preparation_time"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        int recipeID = Integer.parseInt(request.getParameter("recipeID"));

        Recipe recipe =new Recipe();
        recipe.setId(recipeID);
        recipe.setName(recipeName);
        recipe.setIngredients(ingredients);
        recipe.setDescription(recipeDescription);
        recipe.setPreparation_time(preparationTime);
        recipe.setPreparation(preparation);

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update(recipe);

        response.sendRedirect("/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int recipeID = Integer.parseInt(request.getParameter("id"));

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe=recipeDao.read(recipeID);
        request.setAttribute("recipe",recipe);

        getServletContext().getRequestDispatcher("/editrecipe.jsp").forward(request,response);
    }
}
