package Enemigo_Juego;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }

   
    public int atacar() {
        return 10 + nivel; 
    }

    public void recibirDano(int dano) {
        salud -= dano;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() { return salud > 0; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
}
