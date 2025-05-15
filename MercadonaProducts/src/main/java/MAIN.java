import jakarta.jms.JMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MAIN {

    private static final Logger log = LoggerFactory.getLogger(MAIN.class);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, SQLException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        Runnable ApiRequestStore = () -> {
            control control = new control();
            try {
                control.StartSender();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        };
        scheduler.scheduleAtFixedRate(ApiRequestStore, 0, 24, TimeUnit.HOURS);
    }
}
