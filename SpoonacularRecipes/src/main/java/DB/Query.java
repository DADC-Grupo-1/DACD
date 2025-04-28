package DB;

import Api.Ingredient;
import Api.Recipe;

import java.sql.Connection;
import java.sql.SQLException;

public class Query {
    /*
    Creates and Executes the Querys
     */
    public void InsertQuery(Connection connection, Recipe recipe) throws SQLException {
        /*
        Creates the INSERT Query
         */
        if (CheckElement(connection, recipe)){
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


    public boolean CheckElement(Connection connection, Recipe recipe) throws SQLException {
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
}
