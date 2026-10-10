package modelo;

public class Estudiante {
    private String id;
    private String nombre;
    public Estudiante(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() { 
        return nombre; 
    }
}