package Api;

import DB.Query;
import com.google.gson.Gson;

import java.net.http.HttpResponse;

public class Jsoon {
    /*
    Return a JSON of the response
     */

    public Sections Mercadonato_jsonSections(HttpResponse<String> response) {
        /*
        Returns a Sections Object
         */
        Gson gson = new Gson();
        return gson.fromJson(response.body(), Sections.class);
    }

    public Category Mercadonato_json(HttpResponse<String> response) {
        /*
        Returns a Category Object
         */
        Gson gson = new Gson();
        return gson.fromJson(response.body(), Category.class);
    }

    public String toJson(NProduct product) {
        Gson gson = new Gson();
        return gson.toJson(product);
    }
}