package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Direccion;
import co.edu.uniquindio.enviospepepicapapas.model.DireccionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AgregarDireccionController {

    @FXML
    private TextField txtIdDireccion;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtZona;

    @FXML
    public void initialize() {
        // Inicializar opciones del ComboBox
        cbTipo.getItems().addAll("Residencial"," Comercial");
    }

    DataBase dataBase = DataBase.getDataBase();

    @FXML
    private void onGuardar(ActionEvent event) {
        if (validarCampos()) {
            try {
                // Crear objeto Direccion usando el Factory Pattern
                Direccion nuevaDireccion = DireccionFactory.crearDireccion(
                    cbTipo.getValue(),
                    txtCalle.getText().trim(),
                    txtCiudad.getText().trim(),
                    txtZona.getText().trim(),
                    txtIdDireccion.getText().trim()
                );
                dataBase.agregarDireccion(nuevaDireccion);

                mostrarAlerta("Éxito", "La dirección ha sido registrada correctamente", Alert.AlertType.INFORMATION);
                limpiarCampos();

            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo guardar la dirección: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void onCancelar(ActionEvent event) {
        limpiarCampos();
        // Aquí se puede agregar lógica para cerrar la ventana o regresar a la vista anterior
    }


    private boolean validarCampos() {
        if (txtIdDireccion.getText().trim().isEmpty()) {
            mostrarAlerta("Validación", "El ID de la dirección es obligatorio", Alert.AlertType.WARNING);
            return false;
        }

        if (cbTipo.getValue() == null || cbTipo.getValue().isEmpty()) {
            mostrarAlerta("Validación", "Debe seleccionar un tipo de dirección", Alert.AlertType.WARNING);
            return false;
        }

        if (txtCalle.getText().trim().isEmpty()) {
            mostrarAlerta("Validación", "La calle es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        if (txtCiudad.getText().trim().isEmpty()) {
            mostrarAlerta("Validación", "La ciudad es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        if (txtZona.getText().trim().isEmpty()) {
            mostrarAlerta("Validación", "La zona es obligatoria", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        txtIdDireccion.clear();
        cbTipo.getSelectionModel().clearSelection();
        txtCalle.clear();
        txtCiudad.clear();
        txtZona.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}