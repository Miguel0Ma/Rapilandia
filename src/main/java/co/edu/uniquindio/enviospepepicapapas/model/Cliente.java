package co.edu.uniquindio.enviospepepicapapas.model;

public class Cliente extends Usuario {
    private String tipo;
    private String password;

    private Cliente(Builder builder) {
        setNombre(builder.nombre);
        setId(builder.id);
        setEmail(builder.email);
        this.tipo = builder.tipo;
        this.password = builder.password;
    }

    /**
     * Agrega una dirección frecuente usando el Factory.
     * @param tipo Tipo de dirección (ej. "Casa").
     * @param calle Calle.
     * @param ciudad Ciudad.
     * @param zona Zona.
     */
    public void agregarDireccionFrecuente(String tipo, String calle, String ciudad, String zona, String id) {
        Direccion nuevaDireccion = DireccionFactory.crearDireccion(tipo, calle, ciudad, zona,id);
        agregarDireccion(nuevaDireccion);
    }

    /**
     * Selecciona una dirección frecuente para un envío.
     * @param idDireccion ID de la dirección.
     * @return La dirección seleccionada o null si no existe.
     */
    public Direccion seleccionarDireccionFrecuente(String idDireccion) {
        return buscarDireccion(idDireccion);
    }

    public String getTipo() {
        return tipo;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private int id;
        private String nombre;
        private String email;
        private String password;
        private String tipo;

        public Cliente.Builder id(int id) {
            this.id = id;
            return this;
        }

        public Cliente.Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Cliente.Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }

        public Cliente build() {
            return new Cliente(this);
        }
    }

}
