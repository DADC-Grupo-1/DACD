import Api.*;
import DB.MConnect;
import DB.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MAIN {

    private static final Logger log = LoggerFactory.getLogger(MAIN.class);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {

        /*
        Insertar los productos en una categoría del mercadona.

                MRequest mRequest = new MRequest();
        URL urlapi = new URL( "https://tienda.mercadona.es/api", "49173",
                "/products/", "/categories/", "99");
        HttpRequest var = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() + urlapi.getCATEGORY_ID());
        HttpResponse response  =  mRequest.Execute(var);

        MConnect mConnect = new MConnect();

        Jsoon json = new Jsoon();
        Category category = json.Mercadonato_json(response);
        System.out.println(category.getcategories());

        Query query = new Query();

        for (int i = 0; i < category.categories.get(0).products.size(); i++) {
            var conn = mConnect.ConnectDB();
            query.InsertQuery(conn, category.categories.get(0).products.get(i));
        }

         */
        /*

        Insertar un producto con su id, hay que cambiar un par de cosas para que funcione
       URL urlapi = new URL( "https://tienda.mercadona.es/api", "49173",
                "/products/", "/categories/", "99");
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
        URL urlapi = new URL("49173", "112");
        HttpRequest var = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY());
        HttpResponse response  =  mRequest.Execute(var);

        MConnect mConnect = new MConnect();

        Jsoon json = new Jsoon();
        Sections sections = json.Mercadonato_jsonSections(response);
        System.out.println(sections.results);
        ///category.getresults();
        ///System.out.println("-----------------------");
        ///List<Integer> ListCategories = sections.getcategories();
        //System.out.println(ListCategories);
        //System.out.println(ListCategories.size());




/*
        Query query = new Query();
        Connection conn = mConnect.ConnectDB();
        for (int i = 0; i<ListCategories.size(); i++) {
                            urlapi = new URL("49173", String.valueOf(ListCategories.get(i)));
            HttpRequest Purl = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() +  urlapi.getCATEGORY_ID());
            HttpResponse PResponse = mRequest.Execute(Purl);

            Category Cresponse = json.Mercadonato_json(PResponse);
            System.out.println(PResponse.body());
            for(int j = 0; j< Cresponse.categories.get(0).products.size(); j++){
                query.InsertQuery(conn,Cresponse.categories.get(0).products.get(j));
            }
        }
        mConnect.CloseDB(conn);
/*
        System.out.println(category.categories.get(0).products.size());

        MConnect mConnect = new MConnect();
        Query query = new Query();
        for(int i = 0; i < category.categories.get(0).products.size(); i++) {
            Connection conn = mConnect.ConnectDB();
            query.InsertQuery(conn, category.categories.get(0).products.get(i));
        }
*/
    }
}
