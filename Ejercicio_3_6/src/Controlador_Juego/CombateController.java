package Controlador_Juego;

import Jugador_Juego.Jugador;
import Enemigo_Juego.Enemigo;
import Vista_Juego.CombateView;
import java.util.Random;

public class CombateController {
    private Jugador jugador;
    private Enemigo enemigo;
    private CombateView vista;
    private Random random;

    public CombateController(Jugador jugador, Enemigo enemigo, CombateView vista) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.vista = vista;
        this.random = new Random();
    }

    
    public void iniciarCombate() {
        vista.mostrarMensaje("Un " + enemigo.getNombre() + " salvaje ha aparecido!");
        boolean combateActivo = true;

        while (combateActivo && jugador.estaVivo() && enemigo.estaVivo()) {
            vista.mostrarEstado(jugador, enemigo);
            vista.mostrarMenu();
            String opcion = vista.pedirOpcion();

            if (opcion.equals("1")) {
                
                int danoJugador = jugador.atacar();
                enemigo.recibirDano(danoJugador);
                vista.mostrarMensaje("Has atacado causando " + danoJugador + " de dano.");

                
                if (enemigo.estaVivo()) {
                    int accionEnemigo = random.nextInt(2);
                    if (accionEnemigo == 0) {
                        int danoEnemigo = enemigo.atacar();
                        jugador.recibirDano(danoEnemigo);
                        vista.mostrarMensaje("El enemigo te ataco y causo " + danoEnemigo + " de dano.");
                    } else {
                        vista.mostrarMensaje("El enemigo esta confundido y no hizo nada en este turno.");
                    }
                }
            } else if (opcion.equals("2")) {
                vista.mostrarMensaje("Escapaste cobardemente del combate.");
                combateActivo = false;
            } else {
                vista.mostrarMensaje("Opcion no valida.");
            }
        }

        if (!jugador.estaVivo()) {
            vista.mostrarMensaje("Has sido derrotado en combate...");
        } else if (!enemigo.estaVivo()) {
            vista.mostrarMensaje("Victoria! Has derrotado al " + enemigo.getNombre() + ".");
        }
    }
}