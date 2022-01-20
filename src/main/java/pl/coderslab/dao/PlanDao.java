package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDao {


    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "select * from plan  where admin_id =? order by created;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ? WHERE	id = ?;";
    private static final String NUMBER_OF_PLANS_QUERY = "SELECT * FROM plan WHERE admin_id = ?;";
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String READ_LAST_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, recipe.id as recipe_id FROM `recipe_plan` JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?) ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String READ_LAST_PLAN_NAME_QUERY = "SELECT name FROM plan WHERE admin_id = ? ORDER BY id DESC LIMIT 1;";
    private static final String SHOW_PLAN_DETAILS= "SELECT day_name.name as day_name, meal_name, recipe.id AS recipe_id, recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id =?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    /**
     * Get plan by id
     *
     * @param planId
     * @return
     */
    public static Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    /**
     * Return all plan
     *
     * @return
     */
    public List<Plan> findAll(Admin admin) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             )
        {
            statement.setInt(1, admin.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    /**
     * Create plan
     *
     * @param plan
     * @return
     */
    public Plan create(Plan plan,Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            LocalDateTime now = LocalDateTime.now();
            insertStm.setTimestamp(3, Timestamp.valueOf(now));
            insertStm.setInt(4,admin.getId());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Remove plan by id
     *
     * @param planId
     */
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update plan
     *
     * @param plan
     */
    public static void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {

            statement.setInt(3, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int numberOfPlans(Admin admin) {
        int counter = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(NUMBER_OF_PLANS_QUERY)
        ) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public static Map<String, List<LatestPlan>> latestPlanMap(Admin admin) {

        Map<String, List<LatestPlan>> lastPlanMap = new HashMap<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_QUERY)
        ) {
            String day;
            String meal;
            String recipeName;
            String recipeDescription;
            int recipeId;
            LatestPlan latestPlan;

            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    day = resultSet.getString("day_name");
                    meal = resultSet.getString("meal_name");
                    recipeName = resultSet.getString("recipe_name");
                    recipeDescription = resultSet.getString("recipe_description");
                    recipeId = resultSet.getInt("recipe_id");

                    latestPlan = new LatestPlan(day, meal, recipeName, recipeDescription, recipeId);

                    lastPlanMap.computeIfAbsent(day, k -> new ArrayList<>()).add(latestPlan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastPlanMap;
    }

    /**
     * Create recipePlan
     * private static final String CREATE_RECIPE_PLAN_QUERY =
     * "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id)
     * VALUES (?,?,?,?,?);";
     *
     * @param recipePlan
     */

    public RecipePlan addRecipeToPlan(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setInt(1, recipePlan.getRecipe_id());
            insertStm.setString(2, recipePlan.getMeal_name());
            insertStm.setInt(3, recipePlan.getDisplay_order());
            insertStm.setInt(4, recipePlan.getDay_name_id());
            insertStm.setInt(5, recipePlan.getPlan_id());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readLastPlanName(Admin admin) {
        String name = "";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_NAME_QUERY)
        ) {
            statement.setInt(1, admin.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    name = resultSet.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public static Map <String,List<LatestPlan>> showPlanDetails(Plan plan) {

        Map <String,List<LatestPlan>> day_meals = new HashMap<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_PLAN_DETAILS)
        ) {
            statement.setInt(1, plan.getId());
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String dayName = resultSet.getString("day_name");
                    String meal = resultSet.getString("meal_name");
                    String recipeName = resultSet.getString("recipe_name");
                    String recipeDescription = resultSet.getString("recipe_description");
                    Integer recipeId = resultSet.getInt("recipe_id");

                    LatestPlan latestPlan = new LatestPlan(meal, recipeName, recipeDescription, recipeId);
                    day_meals.computeIfAbsent(dayName, k -> new ArrayList<>()).add(latestPlan);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return day_meals;
    }
}
