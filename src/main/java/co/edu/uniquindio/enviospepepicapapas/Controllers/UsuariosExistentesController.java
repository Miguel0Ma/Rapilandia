package co.edu.uniquindio.enviospepepicapapas.Controllers;

import co.edu.uniquindio.enviospepepicapapas.Repositories.DataBase;
import co.edu.uniquindio.enviospepepicapapas.model.Administrador;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class UsuariosExistentesController {

    @FXML
    private ListView<String> listUsuarios;

    DataBase dataBase = DataBase.getDataBase();
    
    // Lista para mantener referencia a los usuarios en el mismo orden que el ListView
    private ArrayList<Object> todosLosUsuarios = new ArrayList<>();

    @FXML
    public void initialize() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        listUsuarios.getItems().clear();
        todosLosUsuarios.clear();

        // Agregar clientes
        for (Cliente cliente : dataBase.getClientes()) {
            String info = "Cliente - " + cliente.getNombre() + " - " + cliente.getEmail();
            listUsuarios.getItems().add(info);
            todosLosUsuarios.add(cliente);
        }

        // Agregar repartidores
        for (Repartidor repartidor : dataBase.getRepartidores()) {
            String info = "Repartidor - " + repartidor.getNombre() + " - " + repartidor.getEmail();
            listUsuarios.getItems().add(info);
            todosLosUsuarios.add(repartidor);
        }

        // Agregar administradores
        for (Administrador administrador : dataBase.getAdministradores()) {
            String info = "Administrador - " + administrador.getNombre() + " - " + administrador.getEmail();
            listUsuarios.getItems().add(info);
            todosLosUsuarios.add(administrador);
        }
    }

    @FXML
    private void onEliminar(ActionEvent event) {
        int selectedIndex = listUsuarios.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Object usuario = todosLosUsuarios.get(selectedIndex);
            
            if (usuario instanceof Cliente) {
                dataBase.getClientes().remove(usuario);
            } else if (usuario instanceof Repartidor) {
                dataBase.getRepartidores().remove(usuario);
            } else if (usuario instanceof Administrador) {
                dataBase.getAdministradores().remove(usuario);
            }
            
            cargarUsuarios();
        }
    }

}