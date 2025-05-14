import Api.Jsoon;
import Api.Recipe;
import DB.Query;
import DB.Sconnect;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.sql.SQLException;
import java.util.List;

public class ActiveMQ {
    private static String url = "tcp://localhost:61616";
    private static String subject = "SpoonacularRecipes";

    public static void main(String[] args) throws JMSException, SQLException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();

        System.out.println("Connection created");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(subject);
        MessageProducer producer = session.createProducer(destination);

        Sconnect sconnect = new Sconnect();
        Query query = new Query();
        Jsoon jsoon = new Jsoon();

        java.sql.Connection conn = sconnect.ConnectDB();
        List<Recipe> recipes = query.GetAllRecipes(conn);

        for (Recipe recipe : recipes) {
            TextMessage message = session.createTextMessage(jsoon.toJson(recipe));
            producer.send(message);
        }
        connection.close();
    }
}

/*
Maneras de las que puedo obtimizar el código
- Puedo hacer que la función de guardar la información en un fichero sea unificada y sea válida para las dos opciones
- Obtimizar la base de datos para que contenga información necesaria
- Implementar más el código limpio
 */