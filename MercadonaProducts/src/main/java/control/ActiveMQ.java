package control;

import model.URL;
import com.google.gson.JsonObject;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class ActiveMQ {

    private static String url = "tcp://localhost:61616";
    private static String subject = "MercadonaProducts";
    GetProduts task = new GetProduts();

    public void ActiveMQ(URL url_api, List<Integer> ListOfCategoriesids) throws JMSException, URISyntaxException, IOException, SQLException, InterruptedException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();
        System.out.println("Connection created");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(subject);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        List<JsonObject> products = task.GetAllProducts(url_api, ListOfCategoriesids);
        for (JsonObject product : products) {
            TextMessage message = session.createTextMessage(product.toString());
            producer.send(message);
        }
        connection.close();
        producer.close();
        session.close();
    }
}
