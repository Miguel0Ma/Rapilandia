package co.edu.uniquindio.enviospepepicapapas.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InicioSesionController {

    @FXML
    private ComboBox<String> tipoUsuarioComboBox;

    @FXML
    private TextField correoTextField;

    @FXML
    private PasswordField contrasenaPasswordField;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void iniciarSesion() {
        String tipoUsuario = tipoUsuarioComboBox.getValue();
        String correo = correoTextField.getText();
        String contrasena = contrasenaPasswordField.getText();

        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            mensajeLabel.setText("Por favor seleccione un tipo de usuario.");
            return;
        }

        if (correo == null || correo.isEmpty()) {
            mensajeLabel.setText("Por favor ingrese su correo.");
            return;
        }

        if (contrasena == null || contrasena.isEmpty()) {
            mensajeLabel.setText("Por favor ingrese su contraseña.");
            return;
        }

        // Aquí puedes agregar la lógica de autenticación
        // Por ahora, solo mostramos un mensaje de éxito
        mensajeLabel.setText("Inicio de sesión exitoso para " + tipoUsuario + ": " + correo);
        mensajeLabel.setStyle("-fx-text-fill: green;");
    }
}
