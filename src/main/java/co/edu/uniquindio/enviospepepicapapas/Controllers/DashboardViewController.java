package co.edu.uniquindio.enviospepepicapapas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardViewController {

    @FXML
    private BorderPane contentPane;

    @FXML
    public void initialize() {
        System.out.println("DashboardViewController inicializado correctamente");
    }

    @FXML
    public void onRegistrarEnvio(ActionEvent event) {
        System.out.println("Botón Registrar Envío presionado");
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/EnvioView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de envíos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onHistorialEnvios(ActionEvent event) {
        System.out.println("Botón Historial presionado");
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/HistorialEnviosView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de historial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onGestionPagos(ActionEvent event) {
        System.out.println("Botón Pagos presionado");
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/SelectorEnviosView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir el selector de envíos: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @FXML
    public void onGestionUsuarios(ActionEvent event) {
        System.out.println("Botón Registrar Usuario presionado");
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/UsuarioView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onNotificaciones(ActionEvent event) {
        System.out.println("Botón Registrar Notificacion presionado");
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/NotificacionView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de notificaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarVistaEnContenido(String rutaFXML) throws IOException {
        System.out.println("Intentando cargar en contenido: " + rutaFXML);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = loader.load();

        contentPane.setCenter(root);

        System.out.println("Vista cargada exitosamente en el contenido: " + rutaFXML);
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}