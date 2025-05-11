package DB;

import Api.NProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {
    /*
    Creates and Executes the Querys
     */

    public void InsertQuery(Connection connection, NProduct product) throws SQLException {
        /*
        Creates the INSERT Query
         */
        if (CheckElement(connection, product)){
            try {
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
            } catch (org.sqlite.SQLiteException e) {
                if(e.getMessage().contains("PRIMARY KEY")){
                    System.out.println("Insert failed (primary key already exists)");
                } else {
                    throw e;
                }
            }
        };
    }


    public boolean CheckElement(Connection connection, NProduct product) throws SQLException {
        /*
        Checks if the element is already in the DB
         */
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

    public List<NProduct> GetAllQuery(Connection connection) throws SQLException {
        var statement =  connection.createStatement();
        String Select = "SELECT * FROM MercadonaProducts";
        ResultSet resultSet = statement.executeQuery(Select);
        var meta = resultSet.getMetaData();

        List<NProduct> products = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String display_name = resultSet.getString("display_name");
            String packaging = resultSet.getString("packaging");
            String thumbnail = resultSet.getString("thumbnail");
            String unit_price = resultSet.getString("unit_price");
            String bulk_price = resultSet.getString("bulk_price");
            String reference_format = resultSet.getString("reference_format");


            NProduct producto = new NProduct(id, display_name, packaging, thumbnail, new NProduct.PriceInstructions(unit_price, bulk_price, reference_format));
            products.add(producto);
        }
        return(products);
    }

    public void ClearDB(Connection connection) throws SQLException {
        String Delete = "DELETE FROM MercadonaProducts";
        var statement =  connection.createStatement();
        statement.executeUpdate(Delete);
        System.out.println("DB Clear");
    }
}