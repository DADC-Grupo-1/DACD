import com.google.gson.JsonObject;
import jakarta.jms.JMSException;
import model.URL;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.*;

public class control {
    MRequest mRequest = new MRequest();
    ActiveMQ amq = new ActiveMQ();
    GetProduts gProd = new GetProduts();
    public void StartSender() throws URISyntaxException, IOException, InterruptedException, SQLException, JMSException {
        URL url_api = new URL();
        HttpRequest request = mRequest.Get(url_api.getAPI_URL(), url_api.getCATEGORY());
        HttpResponse response = mRequest.Execute(request);

        List<Integer> ListOfCategoriesids = gProd.GetAllCategoriesID(url_api);
        amq.ActiveMQ(url_api, ListOfCategoriesids);
        }
    }
