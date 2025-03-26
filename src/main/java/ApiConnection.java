import OpenFoodApi.Const;
import OpenFoodApi.Item;
import com.google.gson.Gson;

import javax.naming.Context;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiConnection {
    public static void main (String[] args) throws URISyntaxException, IOException, InterruptedException {
//        Javalin app = Javalin.create(/*config*/)
//                .get("/", ctx -> ctx.result("Hello World"))
//                .start(7070);


        Const CONSTANT = new Const();
        HttpRequest getRequest  = HttpRequest.newBuilder()
                .uri(new URI(CONSTANT.API_URL + CONSTANT.PRICE + "/33423"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse <String> getResponse= httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());


        System.out.println(getResponse.body());

        Gson gson = new Gson();

        Item item = gson.fromJson(getResponse.body(), Item.class);

        System.out.println(item.to_string());




    }
}
