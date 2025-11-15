package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Administrador;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;
import javafx.collections.FXCollections;
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
    private Button registrarseButton;

    @FXML
    public void initialize() {
        tipoUsuarioComboBox.setItems(FXCollections.observableArrayList(
                "Cliente", "Repartidor", "Administrador"
        ));
    }

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
        boolean loginExitoso = false;

        // Login para Repartidor
        if (tipoUsuario.equals("Repartidor")) {
            for (Repartidor repartidor : dataBase.getRepartidores()) {
                if (repartidor.getEmail().equalsIgnoreCase(email) && repartidor.getPassword().equals(password)) {
                    loginExitoso = true;
                    abrirDashboard(tipoUsuario);
                    break;
                }
            }
            if (!loginExitoso) {
                mensajeLabel.setText("Credenciales incorrectas para repartidor.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        }

        // Login para Administrador
        else if (tipoUsuario.equals("Administrador")) {
            for (Administrador administrador : dataBase.getAdministradores()) {
                if (administrador.getEmail().equalsIgnoreCase(email) && administrador.getPassword().equals(password)) {
                    loginExitoso = true;
                    abrirDashboard(tipoUsuario);
                    break;
                }
            }
            if (!loginExitoso) {
                mensajeLabel.setText("Credenciales incorrectas para administrador.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        }

        // Login para Cliente
        else if (tipoUsuario.equals("Cliente")) {
            for (Cliente cliente : dataBase.getClientes()) {
                if (cliente.getEmail().equalsIgnoreCase(email) && cliente.getPassword().equals(password)) {
                    loginExitoso = true;
                    abrirDashboard(tipoUsuario);
                    break;
                }
            }
            if (!loginExitoso) {
                mensajeLabel.setText("Credenciales incorrectas para cliente.");
                mensajeLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }

    // Método auxiliar para abrir el dashboard (evita duplicación de código)
    private void abrirDashboard(String tipoUsuario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/DashboardView.fxml"));

        if (loader.getLocation() == null) {
            mensajeLabel.setText("Error: No se encontró DashboardView.fxml");
            mensajeLabel.setStyle("-fx-text-fill: red;");
            System.err.println("Archivo no encontrado: /co/edu/uniquindio/enviospepepicapapas/DashboardView.fxml");
            return;
        }

        Parent root = loader.load();
        DashboardViewController controller = loader.getController();
        controller.setTipoUsuario(tipoUsuario);

        Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Rapilandia Express - Dashboard");
    }

    @FXML
    private void irARegistro() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/RegistroUsuario.fxml"));

            if (loader.getLocation() == null) {
                mensajeLabel.setText("Error: No se encontró RegistroUsuarioView.fxml");
                mensajeLabel.setStyle("-fx-text-fill: red;");
                System.err.println("Archivo no encontrado en: /co/edu/uniquindio/enviospepepicapapas/RegistroUsuario.fxml");
                return;
            }

            Parent root = loader.load();
            Stage stage = (Stage) registrarseButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Rapilandia Express - Registro de Usuario");

        } catch (IOException e) {
            mensajeLabel.setText("Error al cargar registro: " + e.getMessage());
            mensajeLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}
