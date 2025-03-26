package SpoonacularApi;

import SpoonacularApi.Const;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConnection {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        Javalin app = Javalin.create(/*config*/)
//                .get("/", ctx -> ctx.result("Hello World"))
//                .start(7070);


        SpoonacularApi.Const CONSTANT = new Const();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(CONSTANT.API_URL + CONSTANT.RANDOM))
                .header("x-api-key", CONSTANT.APIKEY)
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(getResponse.body());
    }
}
