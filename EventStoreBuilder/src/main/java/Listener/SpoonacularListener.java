package Listener;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.File;
import java.io.IOException;

public class SpoonacularListener {

    private static String url = "tcp://localhost:61616";
    private static String subjectS = "SpoonacularRecipes";
    SafeInFile file = new SafeInFile();

    public void StoreRecipes (String path, String actualdate) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("Hugo");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(subjectS);
        MessageConsumer consumer = session.createConsumer(destination);

        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File event = new File(path, actualdate +".events");
        event.createNewFile();

        consumer.setMessageListener(message -> {
            try {
                String content = ((TextMessage) message).getText();
                file.SafeInFile(path + "/" + actualdate + ".events", content);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Successfully saved recipe");
    }
}
