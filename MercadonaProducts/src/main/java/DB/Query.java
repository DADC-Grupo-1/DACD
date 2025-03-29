package DB;

import Api.NProduct;
import Api.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    public void InsertQuery(Connection connection, NProduct product) throws SQLException {
        /*

         */
        if (CheckElement(connection, product)){
            var statement =  connection.createStatement();
            String Insert = "INSERT INTO MercadonaProducts (id, display_name, packaging, unit_price, bulk_price, reference_format, thumbnail) VALUES (?,?,?,?,?,?,?);";
            var preparedStatement = connection.prepareStatement(Insert);

            preparedStatement.setString(1, product.id);
            preparedStatement.setString(2, product.display_name);
            preparedStatement.setString(3, product.packaging);
            preparedStatement.setString(4, product.getUnit_price());
            preparedStatement.setString(5, product.getBulk_price());
            preparedStatement.setString(6, product.getReference_format());
            preparedStatement.setString(7, product.getThumbnail());
            preparedStatement.execute();
        };
    }


    public boolean CheckElement(Connection connection, NProduct product) throws SQLException {
        /*

         */
        ///var statement =  connection.createStatement();
        String Select = "SELECT id FROM  MercadonaProducts WHERE id = (?)";
        var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setString(1, product.id);
        var resultSet = preparedStatement.executeQuery();

        if (resultSet.getString("id") == product.id) {
            System.out.println("The product is in the database");
            return false;
        }
        return true;
    }
}


/*
    public void InsertQuery(Connection connection, Product product) throws SQLException {
///if (CheckElement(connection, product)){
var statement =  connection.createStatement();
String Insert = "INSERT INTO MercadonaProducts VALUES (?,?,?,?,?,?,?,?,?,?,?);";
var preparedStatement = connection.prepareStatement(Insert);

            preparedStatement.setInt(1, product.id);
            preparedStatement.setString(2, product.display_name);
            preparedStatement.setString(3, product.packaging);
            preparedStatement.setString(4, product.getUnit_price());
        preparedStatement.setString(5, product.getBulk_price());
        preparedStatement.setString(6, product.getReference_format());
        preparedStatement.setString(7, product.getZoom());
        preparedStatement.setString(8, product.getRegural());
        preparedStatement.setString(9, product.getThumbnail());
        preparedStatement.setInt(10, product.getperspective());
        preparedStatement.setString(11, product.ean);
            preparedStatement.execute();

            connection.close();
///};
    }


    public boolean CheckElement(Connection connection, Product product) throws SQLException {
///var statement =  connection.createStatement();
String Select = "SELECT ean FROM  MercadonaProducts WHERE ean = (?)";
var preparedStatement = connection.prepareStatement(Select);
        preparedStatement.setString(1, product.ean);
var resultSet = preparedStatement.executeQuery();

        if (resultSet.getString("ean").equals(product.ean)) {
        System.out.println("The product is in the database");
            return true;
                    }
                    return false;
                    }
                    }
 */