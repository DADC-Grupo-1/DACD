package Listener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SafeInFile {

    public void SafeInFile(String archivo, String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(texto);
            writer.newLine();  // Añadir nueva línea
        } catch (IOException e) {
            System.err.println("Error escribiendo en archivo: " + e.getMessage());
        }
        System.out.println("Escrito");
    }

}
