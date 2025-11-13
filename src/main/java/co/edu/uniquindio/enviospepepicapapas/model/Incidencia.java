package co.edu.uniquindio.enviospepepicapapas.model;

import java.time.LocalDateTime;

/**
 * Clase que representa una incidencia ocurrida durante un envío.
 * RF-012: Registrar incidencias y cambios de estado
 * RF-013: Panel de métricas - Top incidencias por zona
 */
public class Incidencia {

    // Atributos
    private int idIncidencia;
    private Envio envio;                    // Envío afectado
    private TipoIncidencia tipo;            // Tipo de problema
    private String descripcion;             // Detalle del problema
    private LocalDateTime fechaRegistro;    // Cuándo ocurrió
    private String zona;                    // Dónde ocurrió (para métricas)
    private Usuario reportadoPor;           // Quién lo reportó (Repartidor/Admin)
    private EstadoIncidencia estado;        // PENDIENTE, EN_PROCESO, RESUELTA
    private String solucion;                // Cómo se resolvió (opcional)

    // Constructor
    public Incidencia(int idIncidencia, Envio envio, TipoIncidencia tipo,
                      String descripcion, String zona, Usuario reportadoPor) {
        this.idIncidencia = idIncidencia;
        this.envio = envio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.zona = zona;
        this.reportadoPor = reportadoPor;
        this.fechaRegistro = LocalDateTime.now();
        this.estado = EstadoIncidencia.PENDIENTE;

        // Cambiar estado del envío a INCIDENCIA
        if (envio != null) {
            envio.setEstado(EstadoEnvio.INCIDENCIA);
        }
    }

    // Métodos de negocio

    /**
     * Marca la incidencia como resuelta
     */
    public void resolver(String solucion) {
        this.estado = EstadoIncidencia.RESUELTA;
        this.solucion = solucion;

        // Si el envío estaba en INCIDENCIA, cambiarlo a EN_RUTA
        if (envio != null && envio.getEstado() == EstadoEnvio.INCIDENCIA) {
            envio.setEstado(EstadoEnvio.ENRUTA);
        }
    }

    /**
     * Marca la incidencia como en proceso
     */
    public void marcarEnProceso() {
        this.estado = EstadoIncidencia.EN_PROCESO;
    }

    /**
     * Obtiene un resumen de la incidencia
     */
    public String getResumen() {
        return String.format("[%s] %s - Envío #%d - %s",
                tipo,
                descripcion,
                envio != null ? envio.getIdEnvio() : 0,
                zona
        );
    }

    /**
     * Verifica si la incidencia es crítica (requiere atención inmediata)
     */
    public boolean esCritica() {
        return tipo == TipoIncidencia.EXTRAVIO ||
                tipo == TipoIncidencia.ACCIDENTE;
    }

    // Getters y Setters
    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidencia tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Usuario getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(Usuario reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return String.format("Incidencia #%d - %s - Envío #%d - %s [%s]",
                idIncidencia,
                tipo,
                envio != null ? envio.getIdEnvio() : 0,
                zona,
                estado
        );
    }
}