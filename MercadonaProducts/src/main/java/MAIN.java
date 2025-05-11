import Api.*;
import DB.MConnect;
import DB.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MAIN {

    private static final Logger log = LoggerFactory.getLogger(MAIN.class);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        Runnable ApiRequestStore = () -> {
            MRequest mRequest = new MRequest();
            URL urlapi = new URL("49173", "29");
            MConnect mConnect = new MConnect();
            Jsoon json = new Jsoon();
            Query query = new Query();

            HttpRequest request = null;
            try {
                request = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            HttpResponse<String> response  = null;
            try {
                response = mRequest.Execute(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Sections sections = json.Mercadonato_jsonSections(response);
            System.out.println(sections.results.get(0).categories.get(0).name);
            List<Integer> ListCategories = sections.getcategories();
            Connection conn = mConnect.ConnectDB();

            for (int i = 0; i<ListCategories.size(); i++) {
                urlapi = new URL("49173", String.valueOf(ListCategories.get(i)));
                HttpRequest Purl = null;
                try {
                    Purl = mRequest.Get(urlapi.getAPI_URL(), urlapi.getCATEGORY() +  urlapi.getCATEGORY_ID());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                HttpResponse PResponse = null;
                try {
                    PResponse = mRequest.Execute(Purl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Category Cresponse = json.Mercadonato_json(PResponse);
                System.out.println(PResponse.body());

                for(int j = 0; j < Cresponse.getCategories().size(); j++){
                    for(int k = 0; k < Cresponse.getCategories().get(j).getProducts().size(); k++){
                        try {
                            query.InsertQuery(conn, Cresponse.getCategories().get(j).getProducts().get(k));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(10 * 60 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            conn = mConnect.ConnectDB();
            try {
                query.ClearDB(conn);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        };
        scheduler.scheduleAtFixedRate(ApiRequestStore, 0, 24, TimeUnit.HOURS);
    }
}
