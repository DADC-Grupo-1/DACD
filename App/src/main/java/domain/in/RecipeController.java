package domain.in;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import insfrastructure.store.Connect;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeController {
    static Connect db = new Connect();



    public static JsonArray GetIngridientsJsonArray(ResultSet rs) throws SQLException {
        Gson gson = new Gson();
        JsonArray Ingredients = gson.fromJson(rs.getString("extendedIngredients"), JsonArray.class);
        return Ingredients;
    }

    public static void routes(Javalin app) {
        app.get("/recipe/{id}", RecipeController::GetRecipe);
    }

//esto si no es static va mal, no funciona.
    public static void GetRecipe(Context ctx) throws Exception {
        Connection conn = db.ConnectDB();
        String id = ctx.pathParam("id");
        String GetProducts = "SELECT * FROM Recipes WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(GetProducts);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        JsonArray Ingridients = GetIngridientsJsonArray(rs);
        ctx.result(String.valueOf(Ingridients));
    }



    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .start(3030);
        app.get("/recipe/{id}", ctx -> {
        });
    }
}
