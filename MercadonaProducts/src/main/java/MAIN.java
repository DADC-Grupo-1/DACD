import Api.*;
import DB.MConnect;
import DB.Query;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class MAIN {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
/*
        URL urlapi = new URL( "https://tienda.mercadona.es/api", "49173",
                "/products/", "/categories/");

        MRequest mConnection = new MRequest();
        HttpRequest var = mConnection.Get(urlapi.getAPI_URL(), urlapi.getPRODUCT()+ urlapi.getPRODUCT_ID());
        HttpResponse response  =  mConnection.Execute(var);

        Jsoon mjson = new Jsoon();

        Product product = mjson.Mercadonato_json(response);

        System.out.println(product.toString());

        MConnect connection = new MConnect();

        Connection conn = connection.ConnectDB();

        Query q = new Query();
        q.CheckElement(conn, product);
*/
        MRequest mRequest = new MRequest();
        URL urlapi = new URL( "https://tienda.mercadona.es/api", "49173",
                "/products/", "/categories/", "118");
        HttpRequest var = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() + urlapi.getCATEGORY_ID());
        HttpResponse response  =  mRequest.Execute(var);


        Jsoon json = new Jsoon();
        Category category = json.Mercadonato_json(response);
        System.out.println(response.body());
        System.out.println(category.categories.get(0).products.size());


        MConnect mConnect = new MConnect();
        Query query = new Query();
        for(int i = 0; i < category.categories.get(0).products.size(); i++) {
            Connection conn = mConnect.ConnectDB();
            query.InsertQuery(conn, category.categories.get(0).products.get(i));
        }
    }
}
