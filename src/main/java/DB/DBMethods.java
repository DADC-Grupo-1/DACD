package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBMethods {
    public static void main(String[] args) {
    }
    public Connection StablishConnection(String URL) {
        /*

         */
            Connection conection = null;
        try{
            conection = DriverManager.getConnection(URL);
            System.out.println("Connected to the database successfully!");
        }catch (Exception e) {
            System.out.println("Couldn't connect to the database, Error: "+ e);
        }
        return conection;
    }



}
