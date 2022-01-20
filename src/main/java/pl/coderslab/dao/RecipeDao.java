package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;



import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe (name, ingredients, description, preparation_time, preparation, created, updated, admin_id) VALUES (?,?,?,?,?,?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String FIND_ALL_USER_RECIPES_QUERY = "SELECT * FROM recipe where admin_id = ?;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, preparation_time =?, preparation = ?, updated = ? WHERE id = ?;";
    private static final String RECIPES_COUNTER_QUERY = "SELECT COUNT(*) FROM recipe where admin_id = ?;";
    private static final String FIND_SEARCHED_RECIPES_QUERY = "SELECT id, name, description FROM `recipe` WHERE name LIKE ?;";


    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    setRecipeToFindAllAndRead(resultSet, recipe);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;

    }


    /**
     * Return all recipes
     *
     * @return
     */
    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeList.add(setRecipeToFindAllAndRead(resultSet, recipeToAdd));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipeList;
    }


    /**
     * Return all recipes by admin_id
     * @param admin
     * @return
     */
    public List<Recipe> findAllByAdminId(Admin admin) {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_RECIPES_QUERY);
             ) {
            statement.setInt(1, admin.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipeToAdd = new Recipe();
                    recipeList.add(setRecipeToFindAllAndRead(resultSet, recipeToAdd));
                }
            } catch (Exception e) {
            e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Recipe setRecipeToFindAllAndRead(ResultSet resultSet, Recipe recipe) throws Exception {
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setIngredients(resultSet.getString("ingredients"));
        recipe.setDescription(resultSet.getString("description"));
        recipe.setCreated((resultSet.getTimestamp("created")).toLocalDateTime());
        recipe.setUpdated((resultSet.getTimestamp("updated")).toLocalDateTime());
        recipe.setPreparation_time(resultSet.getInt("preparation_time"));
        recipe.setPreparation(resultSet.getString("preparation"));
        recipe.setAdminId(resultSet.getInt("admin_id"));

        return recipe;
    }


    /**
     * Create recipe
     *
     * @param recipe
     * @param admin
     * @return
     */
    public Recipe create(Recipe recipe, Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            setRecipeToCreateOrUpdate(recipe, insertStm);
            LocalDateTime now = LocalDateTime.now();
            insertStm.setTimestamp(6, Timestamp.valueOf(now));
            insertStm.setTimestamp(7, Timestamp.valueOf(now));
            recipe.setCreated(now);
            recipe.setUpdated(now);

            insertStm.setInt(8, admin.getId());

            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
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
     * Remove recipe by id
     *
     * @param recipeId
     */
    public boolean delete(Integer recipeId) {
        boolean deleted = false;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            statement.executeUpdate();

            deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    /**
     * Update recipe
     *
     * @param recipe
     */
    public void update(Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            statement.setInt(7, recipe.getId());
            LocalDateTime now = LocalDateTime.now();
            statement.setTimestamp(6, Timestamp.valueOf(now));
            recipe.setUpdated(now);
            setRecipeToCreateOrUpdate(recipe, statement);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * set statement to create or update recipe
     *
     * @param recipe
     * @param statement
     */

    public PreparedStatement setRecipeToCreateOrUpdate(Recipe recipe, PreparedStatement statement) throws SQLException {

        statement.setString(1, recipe.getName());
        statement.setString(2, recipe.getIngredients());
        statement.setString(3, recipe.getDescription());
        statement.setInt(4, recipe.getPreparation_time());
        statement.setString(5, recipe.getPreparation());

        return statement;
    }

    /**
     * number of recipes created by logged user (admin)
     */

    public static int recipesCounter(Admin admin) {
        int result = 0;
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(RECIPES_COUNTER_QUERY)) {
            statement.setInt(1, admin.getId());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("COUNT(*)");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Recipe> findSearchedRecipes(String searchText){
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
        PreparedStatement prepStat = connection.prepareStatement(FIND_SEARCHED_RECIPES_QUERY)){
            prepStat.setString(1, "%"+searchText+"%");
            try(ResultSet resSet = prepStat.executeQuery()){
                while (resSet.next()){
                    Recipe recipe = new Recipe();
                    recipe.setId(resSet.getInt(1));
                    recipe.setName(resSet.getString(2));
                    recipe.setDescription(resSet.getString(3));
                    recipeList.add(recipe);
                }
                return recipeList;
            } catch (SQLException ex){
                ex.printStackTrace();
                return recipeList;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            return recipeList;
        }
    }
}
