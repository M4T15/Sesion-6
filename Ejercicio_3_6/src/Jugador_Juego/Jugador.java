package Jugador_Juego;

import Modelo.InventarioModel;
import Item_inventario.Item;

public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private InventarioModel inventario;
    private Item armaEquipada;

    public Jugador(String nombre, int salud, int nivel, InventarioModel inventario) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.inventario = inventario;
    }


    public boolean equiparArma(String nombreArma) {
        Item item = inventario.buscarItem(nombreArma);
        if (item != null) {
            armaEquipada = item;
            return true;
        }
        return false;
    }

  
    public int atacar() {
        if (armaEquipada != null) {
            return 20 + nivel; 
        }
        return 5 + nivel; 
    }

    public void recibirDano(int dano) {
        salud -= dano;
        if (salud < 0) salud = 0;
    }

    public boolean estaVivo() { return salud > 0; }
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
}
