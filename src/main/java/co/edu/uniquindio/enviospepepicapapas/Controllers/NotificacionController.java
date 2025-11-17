package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class NotificacionController {
    @FXML private TextField txtMensaje;
    @FXML private ComboBox<String> cbCanal;
    @FXML private Label lblResultado;
    @FXML private Button btnVolver;

    private Rapilandia empresa;

    @FXML
    public void initialize() {
        empresa = new Rapilandia("Rapilandia Express");

        // Cargar los canales disponibles (implementaciones del Bridge)
        cbCanal.setItems(FXCollections.observableArrayList("Email", "SMS"));
    }

    // Enviar notificación
    @FXML
    private void onEnviar() {
        String mensaje = txtMensaje.getText();
        String canalSeleccionado = cbCanal.getValue();

        if (mensaje.isEmpty() || canalSeleccionado == null) {
            mostrarAlerta("Campos vacíos", "Debes escribir un mensaje y elegir un canal.", Alert.AlertType.WARNING);
            return;
        }

        NotificacionSender sender;

        // Determinar la implementación del Bridge
        if (canalSeleccionado.equals("Email")) {
            sender = new EmailSender();
        } else {
            sender = new SmsSender();
        }

        // Crear notificación (Bridge)
        Notificacion notificacion = new NotificacionEnvio(sender);
        notificacion.notificar(mensaje, "cliente@ejemplo.com");

        lblResultado.setText("✅ Estado: Notificación enviada por " + canalSeleccionado);
    }




    // Mostrar alertas genéricas
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
