package Api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MRequest {

    public HttpRequest Get (String url, String petition) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest GET  = HttpRequest.newBuilder()
                .uri(new URI(url.toString() + petition.toString()))
                .GET()
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


/*
    public static void main (String [] args) throws URISyntaxException, IOException, InterruptedException {

        URL urlapi = new URL( "https://tienda.mercadona.es/api", "49173",
                "/products/", "/categories/");

        MConnection mConnection = new MConnection();
        HttpRequest var = mConnection.Get(urlapi.getAPI_URL(), urlapi.getPRODUCT() + urlapi.getPRODUCT_ID());
        HttpResponse response  = mConnection.Execute(var);
        System.out.println(response.body());

        Gson gson = new Gson();
        Product item = gson.fromJson((String) response.body(), Product.class);
        System.out.println(item.toString());

    }
 */