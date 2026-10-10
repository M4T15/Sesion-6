package Vista;

import java.util.List;

import Item_inventario.Item;

public class InventarioView {
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void mostrarDetallesItem(Item item) {
		if(item !=null) {
			System.out.println(item);
		}else {
			System.out.println("Error: El item no existe.");
		}
	}
	
	public void mostrarInventario(List<Item> items) {
		if(items.isEmpty()) {
			System.out.println("El inventario esta vacio.");
		}else {
			System.out.println("\n ---INVENTARIO---");
			for( Item itemActual : items) {
				System.out.println(itemActual);
			}
			System.out.println("\n -------------");
		}
	}
}
