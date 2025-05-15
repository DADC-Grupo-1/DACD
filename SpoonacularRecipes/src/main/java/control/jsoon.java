package control;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class jsoon {
    /*
    Converts the JSON response to an GetRecipes Object JSON OBJECT
     */
    public JsonArray getRecipes(HttpResponse response) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        JsonArray jsonRecipes = (gson.fromJson((String) response.body(), JsonObject.class)).getAsJsonArray("recipes");
        return jsonRecipes;
    }
}