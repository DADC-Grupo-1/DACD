package DB;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class MConnectTest {
    MConnect mc = new MConnect();
    @Test
    void connect() {
        Connection con = mc.ConnectDB();
        assertTrue(con != null);
    }

}