package insfrastructure.store;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.model.Ingredient;
import domain.model.Product;
import domain.model.Recipe;

public class FromEventStore {
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

    public static void RecipestoDB(Connection conn, Recipe recipe, String Ingredients, List<String> steps) throws SQLException {
        String StepsString = String.join("| ", steps);
        var statement =  conn.createStatement();
        String CreateTablaIfNotExists = "CREATE TABLE IF NOT EXISTS Recipes (id INT,name TEXT,readyInMinutes INT,servings INT, image TEXT,vegetarian INT,vegan INT,glutenFree INT,dairyFree INT,healthScore REAL,summary TEXT,extendedIngredients TEXT, instructions TEXT);)";
        statement.execute(CreateTablaIfNotExists);

        String Insert =  "INSERT INTO Recipes (id,name ,readyInMinutes ,servings, image,vegetarian,vegan,glutenFree ,dairyFree ,healthScore ,summary ,extendedIngredients,instructions) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        preparedStatement.setDouble(10, recipe.getHealthScore());
        preparedStatement.setString(11, recipe.getSummary());
        preparedStatement.setString(12, Ingredients);
        preparedStatement.setString(13, StepsString);
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

    public static void RunMercadona() throws IOException, SQLException {
        Connect connect = new Connect();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        Gson gson = new Gson();

        BufferedReader reader = new BufferedReader(new FileReader(pathm  + "/" +  today + ".events"));
        String linea;

        Connection conn = connect.ConnectDB();

        while ((linea = reader.readLine()) != null) {
            Product product = gson.fromJson(linea, Product.class);
            JsonObject Jsonproduct = (gson.fromJson(linea, JsonObject.class)).getAsJsonObject("price_instructions");
            Product.PriceInstructions price_instructions = gson.fromJson(Jsonproduct, Product.PriceInstructions.class);
            MercadonaProductstoDB(conn, product, price_instructions);
        }
        conn.close();
    }

    public static void RunRecipe() throws IOException, SQLException {
        Connect connect = new Connect();
        Translate translate = new Translate();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(paths + "/" + today+ ".events"));
        String linea;

        Connection conn = connect.ConnectDB();

        while ((linea = reader.readLine()) != null) {
            Recipe recipe = gson.fromJson(linea, Recipe.class);
            JsonArray JsonIngredients = (gson.fromJson(linea, JsonObject.class)).getAsJsonArray("extendedIngredients");
            for (int i = 0; i < JsonIngredients.size(); i++) {
                String EnglishNameClean = JsonIngredients.get(i).getAsJsonObject().get("nameClean").getAsString();
                String SpanishNameClean = translate.traducirPalabra(EnglishNameClean);
                JsonIngredients.get(i).getAsJsonObject().addProperty("nameClean", SpanishNameClean);
                Double unit_price = obtenerPrecioMaximoPorPalabras(SpanishNameClean);
                JsonIngredients.get(i).getAsJsonObject().addProperty("price", unit_price);


            }
            JsonArray JsonInstructions = (gson.fromJson(linea, JsonObject.class)).getAsJsonArray("analyzedInstructions");
            JsonArray JsonSteps = JsonInstructions.get(0).getAsJsonObject().getAsJsonArray("steps");



            List<String> steps = new ArrayList<>();
            for (JsonElement JsonElement : JsonSteps) {
                steps.add(JsonElement.getAsJsonObject().get("step").getAsString());
            }
            RecipestoDB(conn, recipe, String.valueOf(JsonIngredients), steps);
        }
        conn.close();
    }


    public static Double obtenerPrecioMaximoPorPalabras(String texto) throws SQLException {
        String[] palabras = texto.split("\\s+");
        StringBuilder sql = new StringBuilder("SELECT MIN(unit_price) AS precio_maximo FROM Products WHERE ");
        Connect connect = new Connect();
        Connection conn = connect.ConnectDB();

        for (int i = 0; i < palabras.length; i++) {
            sql.append("name LIKE ?");
            if (i < palabras.length - 1) {
                sql.append(" OR ");
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < palabras.length; i++) {
                stmt.setString(i + 1, "%" + palabras[i] + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("precio_maximo");
                }
            }
        }

        return null;
    }
    public static void main(String[] args) throws IOException, SQLException {
        RunMercadona();
        RunRecipe();
        }
    }