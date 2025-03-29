import java.sql.*;

public class DBconnection {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:identifier.sqlite";

        var select = "SELECT * FROM MercadonaProducts";

        try(Connection connection = DriverManager.getConnection(url)){
            if (connection != null) {
                System.out.println("Connected to database");
            }
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery(select);
            while(rs.next()){
                System.out.printf("DATA:"+
                        rs.getInt("id")+
                        rs.getString("display_name")+
                        rs.getString("packaging")+
                        rs.getDouble("unit_price")+
                        rs.getDouble("bulk_price")+
                        rs.getString("reference_format")+
                        rs.getString("photo_zoom")+
                        rs.getString("photo_regular")+
                        rs.getString("photo_thumbnail")+
                        rs.getInt("perspective"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
