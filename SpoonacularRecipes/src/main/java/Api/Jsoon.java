package Api;

import com.google.gson.Gson;

import java.net.http.HttpResponse;

public class Jsoon {
    /*
    Converts the JSON response to an GetRecipes Object
     */

    public GetRecipes Spoon_json(HttpResponse<String> response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), GetRecipes.class);
    }

    public String toJson(Recipe recipe) {
        Gson gson = new Gson();
        return gson.toJson(recipe);
    }
}