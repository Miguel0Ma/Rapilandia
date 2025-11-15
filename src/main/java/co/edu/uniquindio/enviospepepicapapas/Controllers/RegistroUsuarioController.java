package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroUsuarioController {

    @FXML
    private ComboBox<String> tipoUsuarioComboBox;

    @FXML
    private VBox idContainer;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmarPasswordField;

    @FXML
    private Label mensajeLabel;

    @FXML
    private Button registrarButton;

    @FXML
    private Button limpiarButton;

    @FXML
    private Button volverLoginButton;

    private DataBase dataBase;

    @FXML
    public void initialize() {
        dataBase = DataBase.getDataBase();

        // Poblar ComboBox con opciones
        tipoUsuarioComboBox.setItems(FXCollections.observableArrayList("Cliente", "Repartidor"));

        System.out.println("RegistroUsuarioController inicializado correctamente");

        // Listener para mostrar/ocultar campo ID según el tipo de usuario
        tipoUsuarioComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Cliente".equals(newValue)) {
                idContainer.setVisible(true);
                idContainer.setManaged(true);
            } else {
                idContainer.setVisible(false);
                idContainer.setManaged(false);
                idTextField.clear();
            }
        });
    }

    @FXML
    private void registrarUsuario() {
        // Validar campos
        if (!validarCampos()) {
            return;
        }

        String tipoUsuario = tipoUsuarioComboBox.getValue();
        String nombre = nombreTextField.getText().trim();
        String email = emailTextField.getText().trim();
        String password = passwordField.getText();

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmarPasswordField.getText())) {
            mostrarMensaje("Las contraseñas no coinciden", true);
            return;
        }

        // Validar email único
        if (emailExiste(email)) {
            mostrarMensaje("El correo electrónico ya está registrado", true);
            return;
        }

        // Crear usuario según tipo
        try {
            if ("Cliente".equals(tipoUsuario)) {
                int id = Integer.parseInt(idTextField.getText().trim());

                // Validar ID único para cliente
                if (idExiste(id)) {
                    mostrarMensaje("El ID de usuario ya está registrado", true);
                    return;
                }

                Cliente nuevoCliente = new Cliente.Builder()
                        .id(id)
                        .nombre(nombre)
                        .email(email)
                        .password(password)
                        .tipo("Cliente")
                        .build();
                dataBase.getClientes().add(nuevoCliente);

            } else if ("Repartidor".equals(tipoUsuario)) {
                Repartidor nuevoRepartidor = new Repartidor.Builder()
                        .nombre(nombre)
                        .email(email)
                        .password(password)
                        .tipo("Repartidor")
                        .build();
                dataBase.getRepartidores().add(nuevoRepartidor);
            }

            mostrarMensaje("Usuario registrado exitosamente", false);

            // Mostrar alert de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Exitoso");
            alert.setHeaderText(null);
            alert.setContentText("El " + tipoUsuario.toLowerCase() + " ha sido registrado correctamente.");
            alert.showAndWait();

            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarMensaje("El ID debe ser un número válido", true);
        } catch (Exception e) {
            mostrarMensaje("Error al registrar usuario: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    @FXML
    private void limpiarCampos() {
        tipoUsuarioComboBox.setValue(null);
        idTextField.clear();
        nombreTextField.clear();
        emailTextField.clear();
        passwordField.clear();
        confirmarPasswordField.clear();
        mensajeLabel.setVisible(false);
        idContainer.setVisible(false);
        idContainer.setManaged(false);
    }

    // ========== MÉTODO NUEVO PARA VOLVER AL LOGIN ==========
    @FXML
    private void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/InicioSesion.fxml"));

            if (loader.getLocation() == null) {
                mostrarMensaje("Error: No se encontró InicioSesion.fxml", true);
                System.err.println("Archivo no encontrado en: /co/edu/uniquindio/enviospepepicapapas/InicioSesion.fxml");
                return;
            }

            Parent root = loader.load();
            Stage stage = (Stage) volverLoginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Rapilandia Express - Inicio de Sesión");

        } catch (IOException e) {
            mostrarMensaje("Error al volver al login: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }
    // =======================================================

    private boolean validarCampos() {
        if (tipoUsuarioComboBox.getValue() == null) {
            mostrarMensaje("Por favor seleccione un tipo de usuario", true);
            return false;
        }

        String tipoUsuario = tipoUsuarioComboBox.getValue();

        if ("Cliente".equals(tipoUsuario)) {
            if (idTextField.getText().trim().isEmpty()) {
                mostrarMensaje("Por favor ingrese un ID de usuario", true);
                return false;
            }
            try {
                Integer.parseInt(idTextField.getText().trim());
            } catch (NumberFormatException e) {
                mostrarMensaje("El ID debe ser un número válido", true);
                return false;
            }
        }

        if (nombreTextField.getText().trim().isEmpty()) {
            mostrarMensaje("Por favor ingrese el nombre completo", true);
            return false;
        }

        if (emailTextField.getText().trim().isEmpty()) {
            mostrarMensaje("Por favor ingrese un correo electrónico", true);
            return false;
        }

        if (!emailTextField.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            mostrarMensaje("Por favor ingrese un correo electrónico válido", true);
            return false;
        }

        if (passwordField.getText().isEmpty()) {
            mostrarMensaje("Por favor ingrese una contraseña", true);
            return false;
        }

        if (passwordField.getText().length() < 6) {
            mostrarMensaje("La contraseña debe tener al menos 6 caracteres", true);
            return false;
        }

        if (confirmarPasswordField.getText().isEmpty()) {
            mostrarMensaje("Por favor confirme la contraseña", true);
            return false;
        }

        return true;
    }

    private boolean emailExiste(String email) {
        for (Cliente cliente : dataBase.getClientes()) {
            if (cliente.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        for (Repartidor repartidor : dataBase.getRepartidores()) {
            if (repartidor.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    private boolean idExiste(int id) {
        for (Cliente cliente : dataBase.getClientes()) {
            if (cliente.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        mensajeLabel.setText(mensaje);
        mensajeLabel.setVisible(true);

        if (esError) {
            mensajeLabel.setStyle("-fx-text-fill: #FF441F; -fx-font-weight: bold;");
        } else {
            mensajeLabel.setStyle("-fx-text-fill: #00D632; -fx-font-weight: bold;");
        }
    }
}
