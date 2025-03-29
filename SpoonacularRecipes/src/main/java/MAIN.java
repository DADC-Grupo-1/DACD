import Api.*;
import DB.Query;
import DB.Sconnect;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class MAIN {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
        SRequest sr = new SRequest();
        URL url = new URL("321","123", "7722089f43bb44119b24d0cb3c6ea523");
        HttpRequest request= sr.Get(url.getAPIKEY(), url.getAPI_URL(), url.getRANDOM() + "?number=100");
        HttpResponse response  =  sr.Execute(request);

        ///System.out.println(response.body());
        Jsoon jsoon = new Jsoon();
        GetRecipes recipe = jsoon.Spoon_json(response);

        System.out.println(recipe.recipes.get(0));
        Sconnect sc = new Sconnect();
        Connection conn = sc.ConnectDB();
        Query query = new Query();
        for (Recipe r : recipe.recipes) {
            query.InsertQuery(conn, r);
        }








    }
}
