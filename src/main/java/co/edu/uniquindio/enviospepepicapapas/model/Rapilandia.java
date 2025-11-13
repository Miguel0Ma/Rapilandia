package co.edu.uniquindio.enviospepepicapapas.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rapilandia {

    private String nombreEmpresa;
    private List<Envio> envios;
    private List<Usuario> usuarios;
    private List<Incidencia>incidencias;
    private TarifaManager tarifaManager;
    private HistorialEnvios historial;

    public Rapilandia(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.envios = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.incidencias = new ArrayList<>();
        this.tarifaManager = TarifaManager.getInstancia();
        this.historial = new HistorialEnvios();
    }

    // -------------------------
    // MÉTODOS PRINCIPALES
    // -------------------------

    //  Registrar un usuario
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    //  Registrar un nuevo envío (usa el Builder y Strategy)
    public Envio registrarEnvio(EnvioBuilder builder, TarifaStrategy estrategia) {
        tarifaManager.setEstrategia(estrategia);
        Envio envio = builder.build();
        double costo = tarifaManager.calcularTarifa(envio);
        envio.setCostoTotal(costo);

        envios.add(envio);
        historial.agregarEnvio(envio);

        return envio;
    }
    public void cancelarEnvio(Envio envio) {
        historial.eliminarEnvio(envio);
    }

    public void modificarEnvio(int idEnvio, String origen, double peso, Dimension dimension, Usuario repartidor) {
        historial.actualizarEnvio(idEnvio, origen, peso, dimension, repartidor);
    }

    //  Procesar pago
    public boolean procesarPago(Envio envio, MetodoPago metodoPago) {
        double monto = envio.getCostoTotal();
        boolean resultado = metodoPago.procesarPago(monto);
        if (resultado) {
            System.out.println("💳 Pago aprobado para envío #" + envio.getIdEnvio());
        } else {
            System.out.println("❌ Pago rechazado para envío #" + envio.getIdEnvio());
        }
        return resultado;
    }

    //  Enviar notificación (Bridge)
    public void notificarEnvio(Envio envio, NotificacionSender sender, String destino) {
        Notificacion notificacion = new NotificacionEnvio(sender);
        notificacion.notificar("El envío #" + envio.getIdEnvio() + " está " + envio.getEstado(), destino);
    }

    //  Consultar historial
    public HistorialEnvios getHistorial() {
        return historial;
    }

    public List<Envio> getEnvios() {
        return envios;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public Incidencia registrarIncidencia(Envio envio, TipoIncidencia tipo,
                                          String descripcion, String zona,
                                          Usuario reportadoPor) {
        int nuevoId = incidencias.size() + 1;
        Incidencia incidencia = new Incidencia(
                nuevoId, envio, tipo, descripcion, zona, reportadoPor
        );
        incidencias.add(incidencia);

        System.out.println("⚠️ Incidencia registrada: " + incidencia.getResumen());

        return incidencia;
    }

    public Map<String, Long> getIncidenciasPorZona() {
        return incidencias.stream()
                .collect(Collectors.groupingBy(
                        Incidencia::getZona,
                        Collectors.counting()
                ));
    }
    public List<Incidencia> getIncidenciasPendientes() {
        return incidencias.stream()
                .filter(i -> i.getEstado() == EstadoIncidencia.PENDIENTE)
                .collect(Collectors.toList());
    }
    public List<Incidencia> getIncidenciasCriticas() {
        return incidencias.stream()
                .filter(Incidencia::esCritica)
                .filter(i -> i.getEstado() != EstadoIncidencia.RESUELTA)
                .collect(Collectors.toList());
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }
}