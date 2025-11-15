package co.edu.uniquindio.enviospepepicapapas.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardViewController {

    @FXML
    private BorderPane contentPane;

    @FXML
    private Button btnRegistrarEnvio;

    @FXML
    private Button btnHistorial;

    @FXML
    private Button btnPagos;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnNotificaciones;

    @FXML
    private Button btnDirecciones;

    @FXML
    private Button btnDireccionesGuardadas;

    @FXML
    private Button btnUsuariosExistentes;

    @FXML
    private Button btnIncidencias;

    @FXML
    private Button cerrarSesion;

    @FXML
    public void initialize() {
        System.out.println("DashboardViewController inicializado correctamente");
    }

    public void setTipoUsuario(String tipoUsuario) {
        switch (tipoUsuario) {
            case "Repartidor":
                // Repartidor puede ver: Historial de Envíos y Notificaciones
                btnRegistrarEnvio.setVisible(false);
                btnPagos.setVisible(false);
                btnUsuarios.setVisible(false);
                btnUsuariosExistentes.setVisible(false);
                break;
            case "Cliente":
                // Cliente puede ver: Registrar Envío, Historial de Envíos y Gestión de Pagos
                btnUsuarios.setVisible(false);
                btnNotificaciones.setVisible(false);
                btnUsuariosExistentes.setVisible(false);
                btnUsuarios.setVisible(false);
                break;
            case "Administrador":
                break;
            default:
                // Por defecto, oculta funcionalidades administrativas
                btnUsuarios.setVisible(false);
                btnNotificaciones.setVisible(false);
                break;
        }
    }

    @FXML
    public void onRegistrarEnvio(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/EnvioView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de envíos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onHistorialEnvios(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/HistorialEnviosView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de historial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onGestionPagos(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/SelectorEnviosView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir el selector de envíos: " + e.getMessage());
            e.printStackTrace();
        }
    }




    @FXML
    public void onGestionUsuarios(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/UsuarioView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onAgregarDireccion(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/AgregarDireccionView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de direcciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onNotificaciones(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/NotificacionView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de notificaciones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onDireccionesGuardadas(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/DireccionesGuardadasView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de direcciones guardadas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onUsuariosExistentes(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/UsuariosExistentesView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de usuarios existentes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onIncidencias(ActionEvent event) {
        try {
            cargarVistaEnContenido("/co/edu/uniquindio/enviospepepicapapas/IncidenciaView.fxml");
        } catch (IOException e) {
            mostrarError("Error al cargar vista", "No se pudo abrir la vista de incidencias: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void onCerrarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/enviospepepicapapas/InicioSesion.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Rapilandia Express");
        } catch (IOException e) {
            mostrarError("Error al cerrar sesión", "No se pudo regresar a la pantalla de inicio de sesión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarVistaEnContenido(String rutaFXML) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = loader.load();

        contentPane.setCenter(root);
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}