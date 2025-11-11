package co.edu.uniquindio.enviospepepicapapas.model;

public class PagoEfectivo implements MetodoPago{

    @Override
    public boolean procesarPago(double monto) {
        return false;
    }
}
