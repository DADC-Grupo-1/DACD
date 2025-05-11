import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MAIN {


    private static String url = "tcp://localhost:61616";
    private static String subjectM = "MercadonaProducts";
    private static String subjectS = "SpoonacularRecipes";


    private static void SafeInFile(String archivo, String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(texto);
            writer.newLine();  // Añadir nueva línea
        } catch (IOException e) {
            System.err.println("Error escribiendo en archivo: " + e.getMessage());
        }
        System.out.println("Escrito");
    }

    private static void StoreRecipes (String path, String actualdate) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("Pepe");
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
                SafeInFile(path + "/" + actualdate + ".events", content);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Successfully saved recipe");
    }


    private static void StoreProduts (String path, String actualdate) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("Pepe");
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
                SafeInFile(path + "/" + actualdate + ".events", content);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Successfully saved product");
    }

    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID("Varela");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(subjectM);
        MessageConsumer consumer = session.createConsumer(destination);

        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        String path = "eventstore/Mercadona/MercadonaApi";

        ////StoreRecipes("eventstore/Spoonacular/SpoonacularApi", today);
        ///StoreProduts("eventstore/Mercadona/MercadonaApi", today);

        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        File event = new File(path, today +".events");
        event.createNewFile();

        FileWriter fw = new FileWriter(event,true);

        consumer.setMessageListener(message -> {
            try {
                String texto = ((TextMessage) message).getText();
                SafeInFile("eventstore/Mercadona/MercadonaApi/" + today + ".events", texto);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
