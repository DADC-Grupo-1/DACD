package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sconnect {
    /*
    Creates the connection and disconnection with the DB
     */
    Connection con = null;
    String db = "identifier.sqlite";
    String url = "jdbc:sqlite:" + db;
    ///String url = "jdbc:sqlite:identifier.sqlite";

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