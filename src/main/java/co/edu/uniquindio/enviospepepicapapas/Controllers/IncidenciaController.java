package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.model.*;
import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.stream.Collectors;

public class IncidenciaController {

    private Rapilandia empresa;
    private Usuario usuarioActual; // Para saber quién reporta la incidencia

    @FXML
    private ComboBox<Envio> cbEnvio;

    @FXML
    private ComboBox<TipoIncidencia> cbTipo;

    @FXML
    private TextField txtZona;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TableView<Incidencia> tablaIncidencias;

    @FXML
    private TableColumn<Incidencia, Integer> colId;

    @FXML
    private TableColumn<Incidencia, Integer> colEnvio;

    @FXML
    private TableColumn<Incidencia, TipoIncidencia> colTipo;

    @FXML
    private TableColumn<Incidencia, String> colDescripcion;

    @FXML
    private TableColumn<Incidencia, String> colZona;

    @FXML
    private TableColumn<Incidencia, EstadoIncidencia> colEstado;

    @FXML
    private TableColumn<Incidencia, String> colFecha;

    @FXML
    private ComboBox<EstadoIncidencia> cbFiltroEstado;

    @FXML
    private TextArea txtSolucion;

    @FXML
    public void initialize() {
        empresa = new Rapilandia("Rapilandia Express");

        // Configurar tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("idIncidencia"));
        colEnvio.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getEnvio().getIdEnvio()
                ).asObject()
        );
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zona"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getFechaRegistro().format(
                                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                        )
                )
        );

        // Cargar datos
        cargarEnviosDisponibles();
        cbTipo.setItems(FXCollections.observableArrayList(TipoIncidencia.values()));
        cbFiltroEstado.setItems(FXCollections.observableArrayList(EstadoIncidencia.values()));
        actualizarTabla();
    }

    private void cargarEnviosDisponibles() {
        DataBase db = DataBase.getDataBase();
        cbEnvio.setItems(FXCollections.observableArrayList(db.getEnvios()));
    }

    private void actualizarTabla() {
        tablaIncidencias.setItems(FXCollections.observableArrayList(empresa.getIncidencias()));
    }

    @FXML
    private void onRegistrarIncidencia() {
        Envio envioSeleccionado = cbEnvio.getValue();
        TipoIncidencia tipoSeleccionado = cbTipo.getValue();
        String zona = txtZona.getText();
        String descripcion = txtDescripcion.getText();

        if (envioSeleccionado == null || tipoSeleccionado == null ||
            zona == null || zona.isEmpty() || descripcion == null || descripcion.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return;
        }

        // Crear nueva incidencia
        Incidencia nuevaIncidencia = empresa.registrarIncidencia(envioSeleccionado,
                tipoSeleccionado, descripcion, zona, usuarioActual);

        mostrarAlerta("Éxito", "Incidencia registrada correctamente", Alert.AlertType.INFORMATION);
        limpiarCampos();
        actualizarTabla();
    }

    @FXML
    private void onLimpiar() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        cbEnvio.setValue(null);
        cbTipo.setValue(null);
        txtZona.clear();
        txtDescripcion.clear();
        txtSolucion.clear();
    }

    @FXML
    private void onAplicarFiltro() {
        EstadoIncidencia estadoFiltro = cbFiltroEstado.getValue();

        if (estadoFiltro == null) {
            actualizarTabla();
            return;
        }

        tablaIncidencias.setItems(
                FXCollections.observableArrayList(
                        empresa.getIncidencias().stream()
                                .filter(i -> i.getEstado() == estadoFiltro)
                                .collect(java.util.stream.Collectors.toList())
                )
        );
    }

    @FXML
    private void onLimpiarFiltros() {
        cbFiltroEstado.setValue(null);
        actualizarTabla();
    }

    @FXML
    private void onResolverIncidencia() {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();
        String solucion = txtSolucion.getText();

        if (seleccionada == null) {
            mostrarAlerta("Error", "Seleccione una incidencia", Alert.AlertType.WARNING);
            return;
        }

        if (solucion == null || solucion.isEmpty()) {
            mostrarAlerta("Error", "Ingrese la solución", Alert.AlertType.WARNING);
            return;
        }

        seleccionada.resolver(solucion);
        mostrarAlerta("Éxito", "Incidencia resuelta correctamente", Alert.AlertType.INFORMATION);
        txtSolucion.clear();
        actualizarTabla();
    }

    @FXML
    private void onMarcarEnProceso() {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta("Error", "Seleccione una incidencia", Alert.AlertType.WARNING);
            return;
        }

        if (seleccionada.getEstado() == EstadoIncidencia.RESUELTA) {
            mostrarAlerta("Error", "Esta incidencia ya está resuelta", Alert.AlertType.WARNING);
            return;
        }

        seleccionada.marcarEnProceso();
        mostrarAlerta("Éxito", "Incidencia marcada como En Proceso", Alert.AlertType.INFORMATION);
        actualizarTabla();
    }

    @FXML
    private void onVerDetalles() {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta("Error", "Seleccione una incidencia", Alert.AlertType.WARNING);
            return;
        }

        String detalles = String.format(
                "ID: %d\nTipo: %s\nDescripción: %s\nZona: %s\nEstado: %s\nFecha: %s\nSolución: %s",
                seleccionada.getIdIncidencia(),
                seleccionada.getTipo(),
                seleccionada.getDescripcion(),
                seleccionada.getZona(),
                seleccionada.getEstado(),
                seleccionada.getFechaRegistro().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                seleccionada.getSolucion() != null ? seleccionada.getSolucion() : "No resuelta"
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalles de Incidencia");
        alert.setHeaderText("Información completa");
        alert.setContentText(detalles);
        alert.showAndWait();
    }

    @FXML
    private void onCancelarIncidencia() {
        Incidencia seleccionada = tablaIncidencias.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta("Error", "Seleccione una incidencia", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Cancelación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de cancelar esta incidencia?");

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                seleccionada.setEstado(EstadoIncidencia.CANCELADA);
                mostrarAlerta("Éxito", "Incidencia cancelada", Alert.AlertType.INFORMATION);
                actualizarTabla();
            }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para establecer el usuario actual (llamado desde el dashboard)
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}