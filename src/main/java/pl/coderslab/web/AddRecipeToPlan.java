package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRecipeToPlan", urlPatterns = "/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String recipe_id = request.getParameter("recipe");
        String meal_name = request.getParameter("name");
        String display_order = request.getParameter("number");
        String dayName_id = request.getParameter("day");
        String plan_id = request.getParameter("choosePlan");


        try {
            RecipePlan recipePlan = new RecipePlan();
            recipePlan.setRecipe_id(Integer.parseInt(recipe_id));
            recipePlan.setMeal_name(meal_name);
            recipePlan.setDisplay_order(Integer.parseInt(display_order));
            recipePlan.setDay_name_id(Integer.parseInt(dayName_id));
            recipePlan.setPlan_id(Integer.parseInt(plan_id));

            PlanDao planDao = new PlanDao();
            planDao.addRecipeToPlan(recipePlan);


        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin currentUser = (Admin) session.getAttribute("currentUser");
//        String currentUserFirstName = currentUser.getFirstName();
//        request.setAttribute("currentUserFirstName", currentUserFirstName);

        PlanDao planDao = new PlanDao();
        request.setAttribute("plans", planDao.findAll(currentUser));

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipes", recipeDao.findAllByAdminId(currentUser));

        DayNameDao dayNameDao = new DayNameDao();
        request.setAttribute("days", dayNameDao.findAll());

        getServletContext().getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request, response);
    }
}
