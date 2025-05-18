package domain.in;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import insfrastructure.store.Connect;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeController {
    static Connect db = new Connect();



    public static JsonArray GetIngridientsJsonArray(ResultSet rs) throws SQLException {
        Gson gson = new Gson();
        JsonArray Ingredients = gson.fromJson(rs.getString("extendedIngredients"), JsonArray.class);
        return Ingredients;
    }

    public static JsonObject GetRecipeJsonObject( Connection conn, String id) throws SQLException {
        JsonObject recipe = new JsonObject();
        JsonObject dietary = new JsonObject();

        PreparedStatement ps = conn.prepareStatement("SELECT id,name ,readyInMinutes ,servings, image,vegetarian,vegan,glutenFree ,dairyFree, healthScore ,summary, instructions FROM Recipes WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            recipe.addProperty("id", rs.getString("id"));
            recipe.addProperty("nameClean", rs.getString("name"));
            recipe.addProperty("cookTime", rs.getString("readyInMinutes"));
            recipe.addProperty("servings", rs.getString("servings"));
            recipe.addProperty("image", rs.getString("image"));
            recipe.addProperty("description", rs.getString("summary"));
            dietary.addProperty("vegetarian", rs.getString("vegetarian"));
            dietary.addProperty("vegan", rs.getString("vegan"));
            dietary.addProperty("glutenFree", rs.getString("glutenFree"));
            dietary.addProperty("dairyFree", rs.getString("dairyFree"));

            JsonArray instructions = new JsonArray();
            recipe.add("dietary", dietary);
        }
        return recipe;
    }

    public static void routes(Javalin app) {
        app.get("/recipes/{id}", RecipeController::GetRecipe);
        app.get("/recipes", RecipeController::GetAllRecipes);
    }

    public static void GetRecipe(Context ctx) throws Exception {
        Gson gson = new Gson();
        Connection conn = db.ConnectDB();
        String id = ctx.pathParam("id");
        String GetProducts = "SELECT * FROM Recipes WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(GetProducts);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        JsonObject JsonRecipe = GetRecipeJsonObject(conn, id);
        JsonArray Ingridients = GetIngridientsJsonArray(rs);

        String JsonSteps =rs.getString("instructions");
        JsonArray instructions = new JsonArray();
        for (String paso : JsonSteps.split("\\|")) {
            instructions.add(paso.trim());
        }
        JsonRecipe.add("ingredients", Ingridients);
        JsonRecipe.add("instructions", instructions);
        ctx.result(JsonRecipe.toString());
    }

    public static void GetAllRecipes(Context ctx) throws Exception {
        Connection conn = db.ConnectDB();
        JsonArray recipes = new JsonArray();
        String query = "SELECT id FROM Recipes";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            JsonObject recipe = GetRecipeJsonObject(conn, id);
            recipes.add(recipe);
        }

        ctx.contentType("application/json");
        ctx.result(recipes.toString());
    }
}
