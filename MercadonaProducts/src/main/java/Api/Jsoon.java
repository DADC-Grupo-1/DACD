package Api;

import com.google.gson.Gson;

import java.net.http.HttpResponse;

public class Jsoon {

    public Sections Mercadonato_jsonSections(HttpResponse<String> response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), Sections.class);
    }

    public Category Mercadonato_json(HttpResponse<String> response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), Category.class);
    }
}