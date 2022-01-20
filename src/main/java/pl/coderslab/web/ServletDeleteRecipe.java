package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletDeleteRecipe",urlPatterns = "/app/recipe/delete")
public class ServletDeleteRecipe extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Admin currentUser = (Admin) session.getAttribute("currentUser");
//        String currentUserFirstName = currentUser.getFirstName();
//        request.setAttribute("currentUserFirstName", currentUserFirstName);


        int recipeID = Integer.parseInt(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        boolean deleted = recipeDao.delete(recipeID);    // jeśli przepis jest podpiety do jakiegos planu to nie da sie go usunąc
        request.setAttribute("deleted", deleted);

        getServletContext().getRequestDispatcher("/app/recipe/list").forward(request,response);
//        response.sendRedirect("/app/recipe/list");
    }
}
