package co.edu.uniquindio.enviospepepicapapas.Repositories;

import co.edu.uniquindio.enviospepepicapapas.model.*;

import java.util.ArrayList;

public class DataBase {
    private static DataBase dataBase;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Administrador> administradores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Direccion> direcciones;

    private DataBase() {
        CargarDatos();
    }

    public static DataBase getDataBase(){
        if(dataBase == null){
            dataBase = new DataBase();
        }
        return dataBase;
    }
    public ArrayList<Repartidor> getRepartidores() {
        return repartidores;
    }
    public ArrayList<Administrador>getAdministradores() {
        return administradores;
    }
    public ArrayList<Cliente>getClientes(){
        return clientes;
    }
    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }
    public void CargarDatos(){
        repartidores = new ArrayList<>();
        repartidores.add(new Repartidor.Builder().nombre("Yulbreiner").email("yul@gmail.com").password("123").tipo("Repartidor").build());
        administradores = new ArrayList<>();
        administradores.add(new Administrador.Builder().nombre("Yulbreiner").email("yul@gmail.com").password("123").tipo("Administrador").build());
        clientes = new ArrayList<>();
        clientes.add(new Cliente.Builder().nombre("Juan Perez").email("yul@gmail.com").password("123").tipo("Cliente").build());
        direcciones = new ArrayList<>();
        Direccion nuevaDireccion1 = DireccionFactory.crearDireccion("Residencial","Calle 11","Armenia","Centro","123");
        direcciones.add(nuevaDireccion1);
    }
    public void agregarDireccion(Direccion direccion){
        direcciones.add(direccion);

    }




}
