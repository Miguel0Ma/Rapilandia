package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Administrador;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void iniciarSesion() throws IOException {
        String tipoUsuario = tipoUsuarioComboBox.getValue();
        String email = correoTextField.getText();
        String password = contrasenaPasswordField.getText();

        if (tipoUsuario == null || tipoUsuario.isEmpty()) {
            mensajeLabel.setText("Por favor seleccione un tipo de usuario.");
            return;
        }

        if (email == null || email.isEmpty()) {
            mensajeLabel.setText("Por favor ingrese su correo.");
            return;
        }

        if (password == null || password.isEmpty()) {
            mensajeLabel.setText("Por favor ingrese su contraseña.");
            return;
        }
        DataBase dataBase = DataBase.getDataBase();

        if (tipoUsuario.equals("Repartidor")) {
            boolean loginExitoso = false;
            for (Repartidor repartidor : dataBase.getRepartidores()) {
                if (repartidor.getEmail().equalsIgnoreCase(email) && repartidor.getPassword().equals(password)) {
                    loginExitoso = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/DashboardView.fxml"));
                    Parent root = loader.load();
                    DashboardViewController controller = loader.getController();
                    controller.setTipoUsuario(tipoUsuario); // Configurar permisos según el tipo de usuario
                    Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Rapilandia Express - Dashboard");
                    break;
                }
            }

            if (!loginExitoso) {
                mensajeLabel.setText("Credenciales incorrectas para repartidor.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        } else {
            mensajeLabel.setText("Tipo de usuario no soportado por ahora.");
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }
        if(tipoUsuario.equals("Administrador")) {
            boolean loginExitoso = false;
            for (Administrador administrador:dataBase.getAdministradores()){
                if(administrador.getEmail().equalsIgnoreCase(email)&&administrador.getPassword().equals(password)) {
                    loginExitoso = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/DashboardView.fxml"));
                    Parent root = loader.load();
                    DashboardViewController controller = loader.getController();
                    Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Rapilandia Express - Dashboard");
                    break;

                }
            }
        }
        if(tipoUsuario.equals("Cliente")) {
            boolean loginExitoso = false;
            for (Cliente cliente: dataBase.getClientes()){
                if(cliente.getEmail().equalsIgnoreCase(email)&&cliente.getPassword().equals(password)) {
                    loginExitoso = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/DashboardView.fxml"));
                    Parent root = loader.load();
                    DashboardViewController controller = loader.getController();
                    Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Rapilandia Express - Dashboard");
                    break;


                }
            }
        }

    }
}
