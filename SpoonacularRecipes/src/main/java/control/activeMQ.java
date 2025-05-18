package control;

import com.google.gson.JsonObject;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class activeMQ {
    private static String url = "tcp://localhost:61616";
    private static String subject = "SpoonacularRecipes";

    public void ActiveMQ(List<JsonObject> recipes) throws JMSException, IOException, URISyntaxException, InterruptedException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();

        System.out.println("Connection created");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(subject);
        MessageProducer producer = session.createProducer(destination);
        for (JsonObject recipe : recipes) {
            TextMessage message = session.createTextMessage(recipe.toString());
            producer.send(message);
        }
        connection.close();
    }
    }