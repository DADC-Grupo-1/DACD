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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MAIN {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

       /// Runnable ApiRequestStore = () -> {
            SRequest sr = new SRequest();
            URL url = new URL("321", "7722089f43bb44119b24d0cb3c6ea523");
            HttpRequest request = null;
            try {
                request = sr.Get(url.getAPIKEY(), url.getAPI_URL(), url.getRANDOM() + "?number=50");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            HttpResponse response = null;
            try {
                response = sr.Execute(request);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Jsoon jsoon = new Jsoon();
            GetRecipes recipe = jsoon.Spoon_json(response);

            System.out.println(recipe.getRecipes().get(0));
            Sconnect sc = new Sconnect();
            Connection conn = sc.ConnectDB();

            Query query = new Query();
            for (Recipe r : recipe.getRecipes()) {
                try {
                    query.InsertQueryRecipe(conn, r);
                    for (int i = 0; i < r.getextendedIngredients().size(); i++) {
                        System.out.println("Inserting Ingredient");
                        query.InsertQueryIngredient(conn, r.getextendedIngredients().get(i), r.getId());
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            sc.CloseDB(conn);
        ///};
        ///scheduler.scheduleAtFixedRate(ApiRequestStore, 0, 24, TimeUnit.HOURS);

    }
}