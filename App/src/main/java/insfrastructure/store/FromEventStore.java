package insfrastructure.store;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.model.Ingredient;
import domain.model.Product;
import domain.model.Recipe;

public class FromEventStore {
    /*
    Primero prubo con uno y después debo de unificarlo para usarlo con los dos
     */
    static String pathm  ="eventstore/Mercadona/MercadonaApi/";
    static String paths  ="eventstore/Spoonacular/SpoonacularApi";

    public static void MercadonaProductstoDB(Connection conn, Product product, Product.PriceInstructions price_instructions) throws SQLException {
        String CreateTablaIfNotExists = "CREATE TABLE IF NOT EXISTS Products(id INT, name Text, packaging TEXT, unit_price REAL,bulk_price REAL,format CHAR(2),photo STRING);";
        conn.createStatement().execute(CreateTablaIfNotExists);

        List<String> Denegate = List.of("Pack-6", "Pack-8", "Pack-2", "Pack-9", "Pack-4", "Pack-3", "Pack-12", "Pack-5", "Pack-16", "Pack-10");
        var statement =  conn.createStatement();
        String Insert =  "INSERT INTO Products (id, name, packaging, unit_price, bulk_price,format,photo) VALUES (?,?,?,?,?,?,?)";
        var preparedStatement =  conn.prepareStatement(Insert);
        if (CheckElement(conn, product) && !(product.getPackaging() == null || Denegate.contains(product.getPackaging()))){

            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getDisplay_name());
            preparedStatement.setString(3, product.getPackaging());
            preparedStatement.setString(4, price_instructions.getUnit_price());
            preparedStatement.setString(5, price_instructions.getBulk_price());
            preparedStatement.setString(6, price_instructions.getReference_format());
            preparedStatement.setString(7, product.getThumbnail());
            preparedStatement.execute();
        };
    }

    public static void RecipestoDB(Connection conn, Recipe recipe, String Ingredients) throws SQLException {
        var statement =  conn.createStatement();
        String CreateTablaIfNotExists = "CREATE TABLE IF NOT EXISTS Recipes (id INT,name TEXT,readyInMinutes INT,servings INT, image TEXT,vegetarian INT,vegan INT,glutenFree INT,dairyFree INT,veryHealthy INT,veryPopular INT,sustainable INT,healthScore REAL,summary TEXT,extendedIngredients TEXT)";
        statement.execute(CreateTablaIfNotExists);

        String Insert =  "INSERT INTO Recipes (id,name ,readyInMinutes ,servings, image,vegetarian,vegan,glutenFree ,dairyFree ,veryHealthy ,veryPopular ,sustainable ,healthScore ,summary ,extendedIngredients) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        var preparedStatement =  conn.prepareStatement(Insert);

        preparedStatement.setString(1, recipe.getId());
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
        preparedStatement.setString(15, Ingredients);
        preparedStatement.execute();

    }

    public static boolean CheckElement(Connection connection, Product product) throws SQLException {
        /*
        Checks if the element is already in the DB
         */
        String Select = "SELECT id FROM Products WHERE id = (?)";
        var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setString(1, product.getId());
        var resultSet = preparedStatement.executeQuery();

        if (resultSet.getString("id") == product.getId()) {
            System.out.println("The product is in the database");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, SQLException {

        Connect connect = new Connect();

        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(paths + "/20250515" + ".events"));
        String linea;

        Connection conn = connect.ConnectDB();

        while ((linea = reader.readLine()) != null) {
            Recipe recipe = gson.fromJson(linea, Recipe.class);
            JsonArray JsonIngredients = (gson.fromJson(linea, JsonObject.class)).getAsJsonArray("extendedIngredients");
            RecipestoDB(conn, recipe, String.valueOf(JsonIngredients));
        }
/*
        String Select  =  "SELECT * FROM Recipes WHERE id = (?)";
        var preparedStatement = conn.prepareStatement(Select);
        preparedStatement.setString(1, "664636");
        ResultSet resultSet = preparedStatement.executeQuery();

        JsonArray Ingredients = gson.fromJson(resultSet.getString("extendedIngredients"), JsonArray.class);

        for (JsonElement ingredient : Ingredients) {
            Ingredient ing = gson.fromJson(ingredient, Ingredient.class);
            System.out.println(ing.getNameClean());
        }
 */
    }
}

/*

Mercadona
        Connect connect = new Connect();

        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(paths + today + ".events"));
        String linea;

        Connection conn = connect.ConnectDB();

        while ((linea = reader.readLine()) != null) {
            Product product = gson.fromJson(linea, Product.class);
            JsonObject Jsonproduct = (gson.fromJson(linea, JsonObject.class)).getAsJsonObject("price_instructions");
            Product.PriceInstructions price_instructions = gson.fromJson(Jsonproduct, Product.PriceInstructions.class);
            System.out.println(price_instructions.getUnit_price());
            MercadonaProductstoDB(conn, product, price_instructions);
        }

 */