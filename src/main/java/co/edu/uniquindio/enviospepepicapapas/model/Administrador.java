package co.edu.uniquindio.enviospepepicapapas.model;

import java.util.ArrayList;

public class Administrador extends Usuario {
    private String tipo;
    private String password;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Repartidor> repartidores;

    private Administrador(Builder builder) {
        setNombre(builder.nombre);
        setId(builder.id);
        setEmail(builder.email);
        this.tipo = builder.tipo;
        this.password = builder.password;
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

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder email(String email) {
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

        public Administrador build() {
            return new Administrador(this);
        }
    }

}
