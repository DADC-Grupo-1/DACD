package app;

import io.javalin.Javalin;
import models.Product;

public class App {

    public static class Prod{
        public String nombre;
        public Prod(String nombre) {
            this.nombre = nombre;
        }
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);

        app.get("/", ctx -> {

        });


        app.get("/{product}", ctx -> {
            String product = ctx.pathParam("product");
            ctx.json(new Prod(product));
        });
    }
}