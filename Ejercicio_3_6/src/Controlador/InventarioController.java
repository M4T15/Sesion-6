package Controlador;

import Item_inventario.Item;
import Modelo.InventarioModel;
import Vista.InventarioView;

public class InventarioController {
	private InventarioModel modelo;
	private InventarioView vista;
	
	public InventarioController(InventarioModel modelo, InventarioView vista) {
		this.modelo = modelo;
		this.vista = vista;
	}
	
	public void agregarItem (Item item){
		modelo.agregarItem(item);
		vista.mostrarMensaje("Item agregado con exito" + item.getNombre());
	}
	
	public void eliminarItem(String nombre) {
		Item itemEliminar = modelo.buscarItem(nombre);
		if(itemEliminar != null) {
			modelo.eliminarItem(itemEliminar);
			vista.mostrarMensaje("Item eliminado: " + nombre );
		}else {
			vista.mostrarMensaje("No se pudo eliminar. El item " + nombre + " no existe.");
		}
	}
	
	public void verInventario() {
		vista.mostrarInventario( modelo.obtenerItems());
	}
	
	public void buscarItem(String nombre) {
		Item itemEncontrado = modelo.buscarItem(nombre);
		vista.mostrarDetallesItem(itemEncontrado);
	}
	
}
