package co.edu.uniquindio.enviospepepicapapas.model;

public enum EstadoIncidencia {
    PENDIENTE,      // Recién reportada
    EN_PROCESO,     // Se está trabajando en resolverla
    RESUELTA,       // Ya fue resuelta
    CANCELADA       // Se canceló (no era válida)
}