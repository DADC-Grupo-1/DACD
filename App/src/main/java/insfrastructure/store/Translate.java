package insfrastructure.store;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translate {
    private static final String API_KEY = "3f00d7aa-f3fc-41cc-b143-881eb8da00a9:fx";

    public String traducirPalabra(String palabra) throws IOException {
        String urlStr = "https://api-free.deepl.com/v2/translate";
        String parametros = "auth_key=" + URLEncoder.encode(API_KEY, "UTF-8")
                + "&text=" + URLEncoder.encode(palabra, "UTF-8")
                + "&source_lang=EN&target_lang=ES";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = parametros.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder respuesta = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                respuesta.append(linea.trim());
            }
        }

        JsonObject obj = JsonParser.parseString(respuesta.toString()).getAsJsonObject();
        JsonArray translations = obj.getAsJsonArray("translations");
        JsonObject primera = translations.get(0).getAsJsonObject();
        return primera.get("text").getAsString();
    }
}
