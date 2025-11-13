package co.edu.uniquindio.enviospepepicapapas.model;

public enum TipoIncidencia {
    DEMORA("Demora en la entrega"),
    EXTRAVIO("Paquete extraviado"),
    DAÑO("Paquete dañado"),
    DIRECCION_INCORRECTA("Dirección incorrecta o incompleta"),
    CLIENTE_AUSENTE("Cliente no se encuentra en dirección"),
    RECHAZO("Cliente rechaza el paquete"),
    ACCIDENTE("Accidente de tránsito"),
    ROBO("Robo del paquete"),
    CLIMA("Problemas climáticos"),
    OTRO("Otra incidencia");

    private final String descripcion;

    TipoIncidencia(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}