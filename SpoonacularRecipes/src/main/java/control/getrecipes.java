package control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class getrecipes {

    public List<JsonObject> getRamdomRecipes(HttpResponse response) throws URISyntaxException, IOException, InterruptedException {
        srequest sRequest = new srequest();
        jsoon jsoon = new jsoon();
        List<JsonObject> listOfRecipes = new ArrayList<>();
/*
        HttpRequest request = sRequest.Get(url.getAPIKEY(), url.getAPI_URL(),url.getRANDOM() + "?number=5");
        HttpResponse response = sRequest.Execute(request);

 */
        JsonArray jsonRecipes = jsoon.getRecipes(response);
        for (int i = 0; i < jsonRecipes.size(); i++) {
            listOfRecipes.add(jsonRecipes.get(i).getAsJsonObject());
        }
        return listOfRecipes;
    }
}
