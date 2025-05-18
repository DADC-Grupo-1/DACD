package control;

import com.google.gson.JsonParser;
import model.URL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetProduts {
    private static final Logger log = LoggerFactory.getLogger(GetProduts.class);

    public List<Integer> GetAllCategoriesID(URL url) throws URISyntaxException, IOException, InterruptedException {
        ArrayList<Integer> ids = new ArrayList<>();
        MRequest mRequest = new MRequest();
        Gson gson = new Gson();

        HttpRequest request = mRequest.Get(url.getAPI_URL(), url.getCATEGORY());
        HttpResponse response = mRequest.Execute(request);

        JsonObject jsonObject = gson.fromJson((String) response.body(), JsonObject.class);
        JsonArray jsonProducts = jsonObject.get("results").getAsJsonArray();

        for(int i = 0; i<jsonProducts.size(); i++){
            JsonArray a = jsonProducts.get(i).getAsJsonObject().getAsJsonArray("categories");
            for (int j= 0; j< a.size(); j++){
                ids.add(a.get(j).getAsJsonObject().get("id").getAsInt());
            }
        }
        return ids;
    }

    public  String traducirProducto(String jsonOriginal, String traduccion) {
        JsonObject json = JsonParser.parseString(jsonOriginal).getAsJsonObject();
        json.addProperty("producto", traduccion);
        return json.get("producto").getAsString();}

    public  List<JsonObject> GetAllProducts(URL url, List<Integer> ids) throws URISyntaxException, IOException, InterruptedException {
        MRequest mRequest = new MRequest();
        Gson gson = new Gson();
        ArrayList<JsonObject> products = new ArrayList<>();

        for (Integer i : ids) {
            HttpRequest request = mRequest.Get(url.getAPI_URL(), url.getCATEGORY() + i);
            HttpResponse response = mRequest.Execute(request);
            JsonObject jsonresponse = gson.fromJson((String) response.body(), JsonObject.class);
            JsonArray jsonCategories = jsonresponse.getAsJsonArray("categories");
            for (int j = 0; j < jsonCategories.size(); j++) {
                JsonArray jsonProducts = jsonCategories.get(j).getAsJsonObject().getAsJsonArray("products");
                for (int k = 0; k < jsonProducts.size(); k++) {
                    JsonObject product = jsonProducts.get(k).getAsJsonObject();
                    products.add(product);

                }
            }
        }
        return products;
    }
}
