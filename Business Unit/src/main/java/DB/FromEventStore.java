package DB;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Schemas.Product;

public class FromEventStore {
    /*
    Primero prubo con uno y después debo de unificarlo para usarlo con los dos
     */
    static String path  ="eventstore/Mercadona/MercadonaApi/";

    public static void MercadonaProductstoDB(Connection conn, Product product) throws SQLException {
        List<String> Denegate = List.of("Pack-6", "Pack-8", "Pack-2", "Pack-9", "Pack-4", "Pack-3", "Pack-12", "Pack-5", "Pack-16", "Pack-10");
        if (CheckElement(conn, product)){
            var statement =  conn.createStatement();
            String Insert =  "INSERT INTO Products (id, name, packaging, unit_price, bulk_price,format,photo) VALUES (?,?,?,?,?,?,?)";
            var preparedStatement =  conn.prepareStatement(Insert);

            preparedStatement.setString(1, product.id);
            preparedStatement.setString(2, product.display_name);
            if (product.packaging != null && !Denegate.contains(product.packaging)){
                preparedStatement.setString(3, product.packaging);
            }else {

            }
            preparedStatement.setString(4, product.getUnit_price());
            preparedStatement.setString(5, product.getBulk_price());
            preparedStatement.setString(6, product.getReference_format());
            preparedStatement.setString(7, product.getThumbnail());
            preparedStatement.execute();
        };
    }

    public static boolean CheckElement(Connection connection, Product product) throws SQLException {
        /*
        Checks if the element is already in the DB
         */
        String Select = "SELECT id FROM Products WHERE id = (?)";
        var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setString(1, product.id);
        var resultSet = preparedStatement.executeQuery();

        if (resultSet.getString("id") == product.id) {
            System.out.println("The product is in the database");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, SQLException {

        Connect connect = new Connect();

        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = LocalDate.now().format(yyyyMMdd);
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new FileReader(path + today + ".events"));
        String linea;

        Connection conn = connect.ConnectDB();

        while ((linea = reader.readLine()) != null) {
            Product product = gson.fromJson(linea, Product.class);
            ///System.out.println(product);
            MercadonaProductstoDB(conn, product);
        }
    }
}
