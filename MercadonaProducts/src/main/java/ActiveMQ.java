import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQ {

    // URL of the JMS server
    private static String url = "tcp://localhost:61616";
    private static String subject = "MERCADONA";
    // Queue Name.You can create any/many queue names as per your requirement.

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("BADBONNY BEIBE");

        producer.send(message);

        System.out.println("Connection created");
        connection.close();
    }
}
