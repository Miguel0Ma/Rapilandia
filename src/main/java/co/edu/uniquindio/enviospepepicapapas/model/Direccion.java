package co.edu.uniquindio.enviospepepicapapas.model;

public class Direccion {
    private String idDireccion;
    private String tipo;
    private String calle;
    private String ciudad;
    private String zona;

    public Direccion(String idDireccion, String tiopo, String calle, String ciudad, String zona) {
        this.idDireccion = idDireccion;
        this.tipo = tiopo;
        this.calle = calle;
        this.ciudad = ciudad;
        this.zona = zona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
