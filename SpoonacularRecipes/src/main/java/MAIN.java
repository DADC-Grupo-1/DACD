import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MAIN {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        Runnable ApiRequestStore = () -> {
            control api = new control();
            try {
                api.StartSender(10, "7722089f43bb44119b24d0cb3c6ea523");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        };
        scheduler.scheduleAtFixedRate(ApiRequestStore, 0, 24, TimeUnit.HOURS);


    }
}