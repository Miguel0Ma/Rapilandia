package co.edu.uniquindio.enviospepepicapapas.model;


public class Repartidor extends Usuario{
    private String tipo;
    private String password;

    private Repartidor(Builder builder){
        setNombre(builder.nombre);
        setEmail(builder.email);
        this.password=builder.password;
        this.tipo=builder.tipo;

    }

    public String getTipo() {
        return tipo;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder{
       private String nombre;
       private String email;
       private String password;
       private String tipo;

       public Builder nombre(String nombre){
           this.nombre=nombre;
           return this;
       }
       public Builder email(String email){
           this.email=email;
           return this;
       }
       public Builder password(String password){
           this.password=password;
           return this;
       }
       public Builder tipo(String tipo){
           this.tipo=tipo;
           return this;
       }
       public Repartidor build(){
           return new Repartidor(this);
       }
    }
}
