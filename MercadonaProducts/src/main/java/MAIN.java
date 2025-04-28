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
        MRequest mRequest = new MRequest();
        URL urlapi = new URL("49173", "112");
        HttpRequest var = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY());
        HttpResponse response  =  mRequest.Execute(var);

        MConnect mConnect = new MConnect();

        Jsoon json = new Jsoon();
        Sections sections = json.Mercadonato_jsonSections(response);
        System.out.println(sections.results.get(0).categories.get(0).name);
        List<Integer> ListCategories = sections.getcategories();

        Query query = new Query();
        Connection conn = mConnect.ConnectDB();

        for (int i = 0; i<ListCategories.size(); i++) {
                            urlapi = new URL("49173", String.valueOf(ListCategories.get(i)));
            HttpRequest Purl = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() +  urlapi.getCATEGORY_ID());
            HttpResponse PResponse = mRequest.Execute(Purl);

            Category Cresponse = json.Mercadonato_json(PResponse);
            System.out.println(PResponse.body());
            for(int j = 0; j< Cresponse.getCategories().get(0).getProducts().size(); j++){
                query.InsertQuery(conn,Cresponse.getCategories().get(0).getProducts().get(j));
            }
        }
        mConnect.CloseDB(conn);
    }
}
