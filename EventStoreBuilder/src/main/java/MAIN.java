import Listener.MercadonaListener;
import Listener.SpoonacularListener;
import jakarta.jms.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MAIN {
    public static void main(String[] args) throws JMSException, IOException, InterruptedException {

        MercadonaListener mercadonaListener = new MercadonaListener();
        SpoonacularListener spoonacularListener = new SpoonacularListener();

        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);

        String pathm = "eventstore/Mercadona/MercadonaApi";
        String paths = "eventstore/Spoonacular/SpoonacularApi";

        spoonacularListener.StoreRecipes(paths, today);
        mercadonaListener.StoreProduts(pathm, today);

        /*
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

         */
    }

}
