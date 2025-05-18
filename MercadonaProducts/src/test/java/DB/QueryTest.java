package DB;

import Api.Category;
import control.MRequest;
import model.URL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

class QueryTest {

    QueryTest() throws URISyntaxException, IOException, InterruptedException {
    }
    Query query = new Query();
    URL urlapi = new URL("49173", "112");
    MRequest mRequest = new MRequest();
    Jsoon jsoon = new Jsoon();

    HttpResponse response = mRequest.Execute(mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() + urlapi.getCATEGORY_ID()));
    Category category = jsoon.Mercadonato_json(response);
/*
    @Test
    void test() throws SQLException {
        MConnect Connect = new MConnect();
        Connection con = Connect.ConnectDB();
        query.InsertQuery(con,category.getCategories().get(0).getProducts().get(0));
    }
*/
}