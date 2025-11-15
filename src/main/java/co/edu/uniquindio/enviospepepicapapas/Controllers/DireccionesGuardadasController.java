package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Direccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class DireccionesGuardadasController {

    @FXML
    private ListView<String> listDirecciones;

    DataBase dataBase = DataBase.getDataBase();

    @FXML
    public void initialize() {
        cargarDirecciones();
    }

    private void cargarDirecciones() {
        ArrayList<Direccion> direcciones = dataBase.getDirecciones();
        listDirecciones.getItems().clear();
        for (Direccion dir : direcciones) {
            String info = "ID: " + dir.getIdDireccion() + " - Tipo: " + dir.getTipo() + " - Calle: " + dir.getCalle() + " - Ciudad: " + dir.getCiudad() + " - Zona: " + dir.getZona();
            listDirecciones.getItems().add(info);
        }
    }

    public void eliminarDireccion() {
        int indice = listDirecciones.getSelectionModel().getSelectedIndex();
        if (indice != -1) {
            listDirecciones.getItems().remove(indice);
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        // Lógica para regresar a la vista anterior
        // Por ejemplo: cambiar de escena o cerrar la ventana
    }

}