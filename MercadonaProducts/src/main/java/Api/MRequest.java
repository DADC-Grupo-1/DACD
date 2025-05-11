package Api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MRequest {
    /*
    Creates and Execute the Request
     */

    public HttpRequest Get (String url, String petition) throws URISyntaxException{
        /*
        Creates the Request, method GET
         */
        HttpRequest GET  = HttpRequest.newBuilder()
                .uri(new URI(url.toString() + petition.toString()))
                .GET()
                .build();
        System.out.println("You URL is: " + GET);
        return GET;
    }

    public HttpResponse<String> Execute (HttpRequest URL) throws IOException, InterruptedException {
        /*
        Execute the Request, using the API URL. Returns the HttpResponse
         */
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(URL, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}