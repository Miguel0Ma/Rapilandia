package co.edu.uniquindio.enviospepepicapapas.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/co/edu/uniquindio/enviospepepicapapas/InicioSesion.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
            stage.setTitle("Rapilandia Express - Dashboard");
            stage.setScene(scene);
            stage.show();

            System.out.println("Aplicación iniciada correctamente");

        } catch (IOException e) {
            System.err.println("Error al cargar FXML: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}