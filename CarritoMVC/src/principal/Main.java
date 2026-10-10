package principal;

import controlador.Controlador;
import modelo.Tienda;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        new Controlador(new Tienda(), new VistaConsola()).iniciar();
    }
}