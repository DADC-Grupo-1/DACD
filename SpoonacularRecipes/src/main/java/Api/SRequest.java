package Api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SRequest {
    /*
    Creates and Execute the Request
     */

    public HttpRequest Get (String apikey, String url, String petition) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest GET  = HttpRequest.newBuilder()
                .uri(new URI(url.toString() + petition.toString()))
                .GET()
                .header("x-api-key", apikey)
                .build();
        System.out.println("You URL is: " + GET);
        return GET;
    }

    public HttpResponse Execute (HttpRequest URL) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse <String> response = httpClient.send(URL, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}