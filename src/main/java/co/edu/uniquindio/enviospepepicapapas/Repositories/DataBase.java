package co.edu.uniquindio.enviospepepicapapas.Repositories;

import co.edu.uniquindio.enviospepepicapapas.model.Administrador;
import co.edu.uniquindio.enviospepepicapapas.model.Cliente;
import co.edu.uniquindio.enviospepepicapapas.model.Repartidor;

import java.util.ArrayList;

public class DataBase {
    private static DataBase dataBase;
    private ArrayList<Repartidor> repartidores;
    private ArrayList<Administrador> administradores;
    private ArrayList<Cliente> clientes;

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
    public void CargarDatos(){
        repartidores = new ArrayList<>();
        repartidores.add(new Repartidor.Builder().nombre("Yulbreiner").email("Yul@gmail.com").password("123").tipo("Repartidor").build());
        administradores = new ArrayList<>();
        administradores.add(new Administrador.Builder().nombre("Yulbreiner").email("Yul@gmail.com").password("143").tipo("Administrador").build());
        clientes = new ArrayList<>();
        clientes.add(new Cliente.Builder().email("Yul@gmail.com").password("125").tipo("Cliente").build());


    }



}
