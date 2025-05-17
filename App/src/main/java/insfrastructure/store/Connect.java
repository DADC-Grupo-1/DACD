package insfrastructure.store;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    String db = "identifier.sqlite";
    String url = "jdbc:sqlite:" + db;

    public Connection ConnectDB() {
        /*
        Connects to DB
         */
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connected!");
        }catch (Exception e){
            System.out.println("Couldn't connect"+ e);
        }
        return conn;
    }

    public void CloseDB(Connection con) {
        /*
        Disconnects from the DB
         */
        try{
            if(con != null){
                con.close();
                System.out.println("Closed!");
            }
        }catch (Exception e){
            System.out.println("Couldn't close" + e);
        }
    }
}
