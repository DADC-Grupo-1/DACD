package OpenFoodApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConection {


    public Object String (Const url, Const method, String id) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequest  = HttpRequest.newBuilder()
//                .uri(new URI(url + method + id))
                .GET()
                .build();
        return (httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString()));
    }
}
