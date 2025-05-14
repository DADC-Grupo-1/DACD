import Api.*;
import DB.Query;
import DB.Sconnect;
import com.google.gson.annotations.JsonAdapter;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetIngredients {
    public static void main(String[] args) throws SQLException, URISyntaxException, IOException, InterruptedException {
        Query query = new Query();
        Sconnect sconnect = new Sconnect();
        Jsoon jsoon = new Jsoon();
        SRequest sRequest = new SRequest();

        Connection conn = sconnect.ConnectDB();
        var statement =  conn.createStatement();
        String Select = "SELECT * FROM SpoonacularRamdomRecipes";
        ResultSet resultSet = statement.executeQuery(Select);

        List<Recipe> List_Of_Recipes = query.GetAllRecipes(conn);


        System.out.println(jsoon.toJson(List_Of_Recipes.get(0)));
    /*

        int sum = 0;
        for (int i = 0; i < List_Of_Recipes.size(); i++) {

            System.out.println(List_Of_Recipes.get(i).getextendedIngredients().size());
            sum = List_Of_Recipes.get(i).getextendedIngredients().size() + sum;
        }
        System.out.println(sum);






        System.out.println(resultSet.getInt("id"));

        var st =  conn.createStatement();
        List<Ingredient> ingredients = new ArrayList<>();
        String sl = "SELECT * FROM RecipesIngridients WHERE Recipe_id = (?)";
        var preparedStatement = conn.prepareStatement(sl);
        preparedStatement.setInt(1, resultSet.getInt("id"));
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
/*

        query.InsertQueryIngredient(conn, recipe.getextendedIngredients().get(0),
                recipe.getId());


        ESTE DOCUMENTO SE TIENE QUE ELIMINAR PORQUE NO SIRVE APRA NADA, SON SOLO PRUEBAS QUE ESTOY HACIENDO
        PARA VER COMO ES QUE DEBERÍA DE HACER EL CÓDIGO
     */
    }
}
