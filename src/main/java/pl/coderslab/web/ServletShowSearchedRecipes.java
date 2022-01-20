package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servletShowSearchedRecipes")
public class ServletShowSearchedRecipes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switchToView(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switchToView(request, response);
    }

    private void switchToView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText")==null  ?  "" : request.getParameter("searchText") ;
        List<Recipe> recipeList = RecipeDao.findSearchedRecipes(searchText);
        request.setAttribute("recipeList", recipeList);
        request.getRequestDispatcher("/recipes.jsp").forward(request, response);
    }
}
