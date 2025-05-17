package domain.in;

import com.google.gson.JsonObject;
import insfrastructure.store.Connect;
import io.javalin.Javalin;
import io.javalin.http.sse.SseClient;

import javax.management.relation.RelationSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductController {

    static Connect db = new Connect();
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .start(3030);

        app.get("/{id}", ctx -> {
        Connection conn = db.ConnectDB();
        String id = ctx.pathParam("id");
        String GetProducts = "SELECT * FROM Products WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(GetProducts);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        ctx.result(rs.getString("name"));

        });

    }


}
