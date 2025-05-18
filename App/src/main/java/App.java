import domain.in.RecipeController;
import insfrastructure.store.FromEventStore;
import io.javalin.Javalin;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void RunTableFromEventStore() throws SQLException, IOException {
        FromEventStore store = new FromEventStore();
        store.RunMercadona();
        store.RunRecipe();
    }

    public static void main(String[] args) throws SQLException, IOException {
        try {
            RunTableFromEventStore();
        } catch (IOException | SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("429")) {
                System.err.println("⚠️ Límite alcanzado (429): La API no es capaz de procesar tantas peticiones");
            } else {
                e.printStackTrace(); // otros errores los mostramos igualmente
            }
        }
        Javalin app = Javalin.create().start(7070);
        RecipeController.routes(app);
    }
}