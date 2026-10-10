package Ejercicio_3;


import Jugador_Juego.Jugador;
import Enemigo_Juego.Enemigo;
import Vista_Juego.CombateView;
import Controlador_Juego.CombateController;

import Controlador.InventarioController;
import Item_inventario.Item;
import Modelo.InventarioModel;
import Vista.InventarioView;


public class Main {
    public static void main(String[] args) {
        InventarioModel inventarioJugador = new InventarioModel();
        Item espada = new Item("Espada Larga", 1, "Arma", "Aumenta el dano en combate");
        inventarioJugador.agregarItem(espada);

        Jugador jugador = new Jugador("Heroe", 100, 5, inventarioJugador);
        Enemigo enemigo = new Enemigo("Orco Furioso", 80, 3, "Monstruo");

        System.out.println("--- PREPARACION ---");
        if (jugador.equiparArma("Espada Larga")) {
            System.out.println(jugador.getNombre() + " ha equipado la Espada Larga.");
        }

        CombateView vista = new CombateView();
        CombateController controlador = new CombateController(jugador, enemigo, vista);

        controlador.iniciarCombate();
    }
}