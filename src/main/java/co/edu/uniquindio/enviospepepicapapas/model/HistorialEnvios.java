package co.edu.uniquindio.enviospepepicapapas.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistorialEnvios implements Iterable<Envio> {
    private List<Envio> listaEnvios = new ArrayList<>();

    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
    }
    public void eliminarEnvio(Envio envio) {listaEnvios.remove(envio);}

    public Envio buscarEnvioId(int idEnvio) {
        for (Envio envio : listaEnvios) {
            if (envio.getIdEnvio() == idEnvio) {
                return envio;
            }

        }
        return null;
    }
    public void actualizarEnvio(int idEnvio,String origen, double peso, Dimension dimension,Usuario repartidor) {
        buscarEnvioId(idEnvio);
        Envio envio = buscarEnvioId(idEnvio);
        envio.setOrigen(origen);
        envio.setPeso(peso);
        envio.setDimension(dimension);
        envio.setRepartidor(repartidor);

    }
    @Override
    public Iterator<Envio> iterator() {
        return listaEnvios.iterator();
    }
}
