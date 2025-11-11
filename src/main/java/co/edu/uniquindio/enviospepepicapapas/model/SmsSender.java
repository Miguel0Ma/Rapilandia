package co.edu.uniquindio.enviospepepicapapas.model;

public class SmsSender implements NotificacionSender {
    @Override
    public void enviarNotificacion(String mensaje, String destino) {
        System.out.println("📧 Enviando SMS a " + destino + ": " + mensaje);
    }
}
