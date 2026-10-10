package restaurante;

import Controlador.PedidoControlador;
import Modelo.PedidoModelo;
import Vista.PedidoVista;

public class Main {
	public static void main(String[] args) {
		PedidoModelo modelo = new PedidoModelo();
		PedidoVista vista = new PedidoVista();
		PedidoControlador controlador = new PedidoControlador(modelo,vista);
		controlador.iniciar();
	}
}
