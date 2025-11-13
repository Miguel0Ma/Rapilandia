package co.edu.uniquindio.enviospepepicapapas.model;

public class DireccionFactory {

    public static Direccion crearDireccion(String tipo, String calle, String ciudad, String zona,String idDireccion) {
        switch (tipo) {
            case "Trabajo":
                return new Direccion(idDireccion,"Residencial",calle,ciudad,zona);
            case "Residencial":
                return new Direccion(idDireccion,"Ciudad",zona,calle,ciudad);
            default:
                return new Direccion(idDireccion,tipo,zona,calle,ciudad);
        }

    }

    public static Direccion crearDireccionPorDefecto() {
        return crearDireccion("Casa", "Calle Principal", "Bogotá", "Centro","2334");
    }
}