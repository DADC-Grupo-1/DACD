import domain.in.RecipeController;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        RecipeController.routes(app);
    }
}


/*
/app
├── /backend (API RESTful y lógica de negocio)
│   ├── /core                   (Lógica de negocio)
│   │   ├── /domain             (Entidades y objetos de valor)
│   │   ├── /services           (Casos de uso y servicios)
│   │   └── /ports              (Interfaces de puertos: entrada y salida)
│   ├── /adapters               (Adaptadores: implementación de puertos)
│   │   ├── /inbound            (Controladores para la API REST)
│   │   ├── /outbound           (Repositorios, servicios externos)
│   │   └── /config             (Configuración, dependencias)
│   └── /App.java               (Configuración e inicialización del servidor backend)
├── /frontend                   (Frontend: interfaz de usuario)
│   ├── /src
│   │   ├── /components         (Componentes reutilizables)
│   │   ├── /pages              (Vistas o páginas)
│   │   └── /services           (Servicios para interactuar con el backend)
│   └── /index.html             (Archivo principal HTML)
└── /build.gradle / pom.xml     (Archivo de configuración de build para backend y frontend)
 */
