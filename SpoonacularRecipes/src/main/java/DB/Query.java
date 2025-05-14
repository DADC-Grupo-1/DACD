package DB;
import Api.Ingredient;
import Api.Recipe;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Query {
    /*
    Creates and Executes the Querys
     */
    public void InsertQueryRecipe(Connection connection, Recipe recipe) throws SQLException {
        /*
        Creates the INSERT Query
         */
        if (CheckElementRecipe(connection, recipe)){
            var statement =  connection.createStatement();
            String Insert = "INSERT INTO SpoonacularRamdomRecipes (id, title, readyInMinutes, servings, image, vegetarian, vegan, glutenFree, dairyFree, veryHealthy, veryPopular, sustainable, healthScore, summary)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            var preparedStatement = connection.prepareStatement(Insert);

            preparedStatement.setInt(1, recipe.getId());
            preparedStatement.setString(2, recipe.getTitle());
            preparedStatement.setInt(3, recipe.getReadyInMinutes());
            preparedStatement.setInt(4, recipe.getServings());
            preparedStatement.setString(5, recipe.getImage());
            preparedStatement.setInt(6, recipe.isVegetarian() ? 1 : 0);
            preparedStatement.setInt(7, recipe.isVegan() ? 1 : 0);
            preparedStatement.setInt(8, recipe.isGlutenFree() ? 1 : 0);
            preparedStatement.setInt(9, recipe.isDairyFree() ? 1 : 0);
            preparedStatement.setInt(10,recipe.isVeryHealthy() ? 1 : 0);
            preparedStatement.setInt(11, recipe.isVeryPopular() ? 1 : 0);
            preparedStatement.setInt(12, recipe.isSustainable() ? 1 : 0);
            preparedStatement.setDouble(13, recipe.getHealthScore());
            preparedStatement.setString(14, recipe.getSummary());
            preparedStatement.execute();
        };
    }

    public void InsertQueryIngredient(Connection connection, Ingredient ingredient, int Recipe_id) throws SQLException {
        var statement =  connection.createStatement();
        String Insert = "INSERT OR IGNORE INTO RecipesIngridients (Ingridient_id, recipe_id,name,amout,unit,meta) VALUES (?,?,?,?,?,?);";
        var preparedStatement = connection.prepareStatement(Insert);

        preparedStatement.setInt(1, ingredient.getId());
        preparedStatement.setInt(2, Recipe_id);
        preparedStatement.setString(3, ingredient.getNameClean());
        preparedStatement.setString(4, ingredient.getAmount());
        preparedStatement.setString(5, ingredient.getUnit());
        preparedStatement.setString(6, String.join(",",ingredient.getMeta()));
        preparedStatement.execute();
    }


    public boolean CheckElementRecipe(Connection connection, Recipe recipe) throws SQLException {
        /*
        Checks if the element is already in the DB
         */
        String Select = "SELECT id FROM  SpoonacularRamdomRecipes WHERE id = (?)";
        var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setInt(1, recipe.getId());
        var resultSet = preparedStatement.executeQuery();

        if (resultSet.getInt("id") == recipe.getId()) {
            System.out.println("The Recipe is in the database");
            return false;
        }
        return true;
    }

    private List<Ingredient> GetIngredients(int id, Connection connection) throws SQLException {
        var statement =  connection.createStatement();
        List<Ingredient> ingredients = new ArrayList<>();
        String Select = "SELECT * FROM RecipesIngridients WHERE recipe_id = (?)";
        var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setInt(1, id);
        ResultSet IngredientResultSet = preparedStatement.executeQuery();

        while (IngredientResultSet.next()) {
            int recipe_id = IngredientResultSet.getInt("Ingridient_id");
            ///int ingredient_id = IngredientResultSet.getInt("recipe_id"); Parece que no hace falta
            String name = IngredientResultSet.getString("name");
            String amount = IngredientResultSet.getString("amount");
            String unit = IngredientResultSet.getString("unit");
            String meta = IngredientResultSet.getString("meta");

            Ingredient ingredient_toAdd = new Ingredient(recipe_id, name, amount, unit, meta);
            ingredients.add(ingredient_toAdd);
        }
        ///System.out.println(resultSet.getString("name"));
        return ingredients;
    }


    public List<Recipe> GetAllRecipes(Connection connection) throws SQLException {
        var statement =  connection.createStatement();
        String Select = "SELECT * FROM SpoonacularRamdomRecipes";
        ResultSet resultSet = statement.executeQuery(Select);

        List<Recipe> recipes = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            Integer readyInMinutes = resultSet.getInt("readyInMinutes");
            Integer servings = resultSet.getInt("servings");
            String image = resultSet.getString("image");
            Boolean vegetarian = resultSet.getBoolean("vegetarian");
            Boolean vegan = resultSet.getBoolean("vegan");
            Boolean glutenFree = resultSet.getBoolean("glutenFree");
            Boolean dairyFree = resultSet.getBoolean("dairyFree");
            Boolean veryHealthy = resultSet.getBoolean("veryHealthy");
            Boolean veryPopular = resultSet.getBoolean("veryPopular");
            Boolean sustainable = resultSet.getBoolean("sustainable");
            Float healthScore = resultSet.getFloat("healthScore");
            String summary = resultSet.getString("summary");
            List<Ingredient> RecipeIngredients = GetIngredients(id, connection);
            Recipe recipe = new Recipe(id, title, readyInMinutes, servings, image, vegetarian, vegan, glutenFree, dairyFree, veryHealthy, veryPopular, sustainable, healthScore, summary, RecipeIngredients);
            recipes.add(recipe);
        }
        return(recipes);
    }

    public List<String> GetAllRecipesIDs(Connection connection) throws SQLException {
        var statement =  connection.createStatement();
        String Select = "SELECT id FROM  SpoonacularRamdomRecipes";
        ResultSet resultSet = statement.executeQuery(Select);
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(String.valueOf(resultSet.getString("id")));
        }
        return ids;
    }
}
