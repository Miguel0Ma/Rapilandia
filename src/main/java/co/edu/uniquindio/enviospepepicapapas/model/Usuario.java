package co.edu.uniquindio.enviospepepicapapas.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Cloneable {
    private int id;
    private String nombre;
    private String email;
    private ArrayList<Direccion> direccionesFrecuentes;

    public Usuario() {
        this.direccionesFrecuentes = new ArrayList<>();
    }

    @Override
    public Usuario clone() {
        try {
            Usuario cloned = (Usuario) super.clone();
            cloned.direccionesFrecuentes = new ArrayList<>(this.direccionesFrecuentes); // Deep copy
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Agrega una dirección frecuente al usuario.
     * @param direccion La dirección a agregar.
     */
    public void agregarDireccion(Direccion direccion) {
        if (direccion != null && !direccionesFrecuentes.contains(direccion)) {
            direccionesFrecuentes.add(direccion);
        }
    }

    /**
     * Elimina una dirección frecuente por ID.
     * @param idDireccion El ID de la dirección a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminarDireccion(String idDireccion) {
        return direccionesFrecuentes.removeIf(d -> d.getIdDireccion().equals(idDireccion));
    }

    /**
     * Busca una dirección frecuente por ID.
     * @param idDireccion El ID de la dirección.
     * @return La dirección encontrada o null.
     */
    public Direccion buscarDireccion(String idDireccion) {
        return direccionesFrecuentes.stream()
                .filter(d -> d.getIdDireccion().equals(idDireccion))
                .findFirst()
                .orElse(null);
    }

    /**
     * Actualiza una dirección frecuente existente.
     * @param idDireccion El ID de la dirección a actualizar.
     * @param nuevaDireccion La nueva dirección.
     * @return true si se actualizó, false si no se encontró.
     */
    public boolean actualizarDireccion(String idDireccion, Direccion nuevaDireccion) {
        for (int i = 0; i < direccionesFrecuentes.size(); i++) {
            if (direccionesFrecuentes.get(i).getIdDireccion().equals(idDireccion)) {
                direccionesFrecuentes.set(i, nuevaDireccion);
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene una copia de la lista de direcciones frecuentes (para evitar modificaciones externas).
     * @return Lista de direcciones frecuentes.
     */
    public List<Direccion> getDireccionesFrecuentes() {
        return new ArrayList<>(direccionesFrecuentes);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
