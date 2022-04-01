package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletDeleteRecipeAlert",urlPatterns = "/app/recipe/delete/alert")
public class ServletDeleteRecipeAlert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeID = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",recipeID);

      getServletContext().getRequestDispatcher("/deleteRecipeAlert.jsp").forward(request,response);

    }
}
