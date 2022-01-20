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

@WebServlet(name = "RecipeAddServlet", urlPatterns = "/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String preparation_time = request.getParameter("preparation_time");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        if (name != null && description != null && preparation_time != null && preparation != null && ingredients != null) {
            Recipe recipe = new Recipe();
            try {
                recipe.setName(name);
                recipe.setDescription(description);
                recipe.setPreparation_time(Integer.parseInt(preparation_time));
                recipe.setPreparation(preparation);
                recipe.setIngredients(ingredients);

                HttpSession session = request.getSession();
                Admin currentUser = (Admin) session.getAttribute("currentUser");
                RecipeDao recipeDao = new RecipeDao();
                recipeDao.create(recipe, currentUser);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } else {
            doGet(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/app/recipe/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
        String currentUserFirstName = currentUser.getFirstName();
        request.setAttribute("currentUserFirstName", currentUserFirstName);

        getServletContext().getRequestDispatcher("/app-add-recipe.jsp")
                .forward(request, response);
    }
}
