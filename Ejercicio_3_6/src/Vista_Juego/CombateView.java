package Vista_Juego;
import Jugador_Juego.Jugador;
import Enemigo_Juego.Enemigo;
import java.util.Scanner;

public class CombateView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }


    public void mostrarEstado(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n--- ESTADO DEL COMBATE ---");
        System.out.println(jugador.getNombre() + " - HP: " + jugador.getSalud());
        System.out.println(enemigo.getNombre() + " - HP: " + enemigo.getSalud());
        System.out.println("--------------------------");
    }

    public void mostrarMenu() {
        System.out.println("1. Atacar al enemigo");
        System.out.println("2. Huir del combate");
    }

    public String pedirOpcion() {
        System.out.print("Elige una accion: ");
        return scanner.nextLine();
    }
}