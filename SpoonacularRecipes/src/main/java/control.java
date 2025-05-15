import com.google.gson.JsonObject;
import control.*;
import jakarta.jms.JMSException;
import model.url;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class control {
    srequest req = new srequest();
    getrecipes getrecipes = new getrecipes();
    activeMQ amq = new activeMQ();

    public void StartSender(int number_recipes, String api_key) throws URISyntaxException, IOException, InterruptedException {
        url url_api = new url(api_key, number_recipes);
        HttpRequest request = req.Get(url_api.getAPIKEY(), url_api.getAPI_URL(), url_api.getRANDOM() + "?number=30");
        HttpResponse response = req.Execute(request);
        List<JsonObject> json_recipes = getrecipes.getRamdomRecipes(response);
        try {
            amq.ActiveMQ(json_recipes);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
