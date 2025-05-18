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
    }

}
