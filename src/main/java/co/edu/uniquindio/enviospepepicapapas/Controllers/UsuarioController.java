package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Administrador;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Rapilandia;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;
import co.edu.uniquindio.enviospepepicapapas.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UsuarioController {

    @FXML private Button btnVolver;
    @FXML private TextField txtNombre;

    @FXML private TextField txtEmail;

    @FXML private PasswordField txtPassword;

    @FXML private ComboBox<String> cbTipo;

    private Rapilandia empresa;
    private DataBase dataBase = DataBase.getDataBase();

    // Usuario actual que se está creando o duplicando
    private Usuario usuarioSeleccionado;

    // Inicialización automática del FXML
    @FXML
    public void initialize() {
        empresa = new Rapilandia("Rapilandia Express");

        // Cargar tipos de usuario
        cbTipo.getItems().addAll("Cliente", "Repartidor", "Administrador");
    }

    // Guardar usuario nuevo
    @FXML
    private void onGuardar() {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String tipo = cbTipo.getValue();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || tipo == null) {
            mostrarAlerta("Campos incompletos", "Por favor completa todos los campos antes de guardar.", Alert.AlertType.WARNING);
            return;
        }

        // Crear usuario según el tipo y agregarlo a la lista correspondiente en DataBase
        Usuario nuevoUsuario = null;
        switch (tipo) {
            case "Cliente":
                nuevoUsuario = new Cliente.Builder()
                    .nombre(nombre)
                    .email(email)
                    .password(password)
                    .tipo(tipo)
                    .build();
                dataBase.getClientes().add((Cliente) nuevoUsuario);
                break;
            case "Repartidor":
                nuevoUsuario = new Repartidor.Builder()
                    .nombre(nombre)
                    .email(email)
                    .password(password)
                    .tipo(tipo)
                    .build();
                dataBase.getRepartidores().add((Repartidor) nuevoUsuario);
                break;
            case "Administrador":
                nuevoUsuario = new Administrador.Builder()
                    .nombre(nombre)
                    .email(email)
                    .password(password)
                    .tipo(tipo)
                    .build();
                dataBase.getAdministradores().add((Administrador) nuevoUsuario);
                break;
        }

        if (nuevoUsuario != null) {
            empresa.registrarUsuario(nuevoUsuario);
            mostrarAlerta("Éxito", "Usuario registrado correctamente en Rapilandia.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Tipo de usuario no válido.", Alert.AlertType.ERROR);
        }
    }

    // Duplicar usuario (usa Prototype)
    @FXML
    private void onDuplicarUsuario() {
        if (usuarioSeleccionado == null) {
            // Si no hay uno en memoria, crea uno base
            usuarioSeleccionado = new Usuario() {};
            usuarioSeleccionado.setNombre(txtNombre.getText());
            usuarioSeleccionado.setEmail(txtEmail.getText());
        }

        // Aplicar Prototype
        Usuario copia = usuarioSeleccionado.clone();
        copia.setNombre(usuarioSeleccionado.getNombre() + " (Copia)");

        empresa.registrarUsuario(copia);

        mostrarAlerta("Duplicación exitosa", "Usuario duplicado con éxito usando Prototype.", Alert.AlertType.INFORMATION);
    }

    //  Cancelar acción
    @FXML
    private void onCancelar() {
        limpiarCampos();
        mostrarAlerta("Acción cancelada", "Los campos fueron limpiados.", Alert.AlertType.INFORMATION);
    }



    // Métodos auxiliares

    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtPassword.clear();
        cbTipo.setValue(null);
        usuarioSeleccionado = null;
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}