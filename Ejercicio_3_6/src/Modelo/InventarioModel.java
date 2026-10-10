package Modelo;

import java.util.List;

import Item_inventario.Item;

import java.util.ArrayList;

public class InventarioModel {
	private List<Item> items;
	
	public InventarioModel() {
		this.items = new ArrayList<>();
	}
	
	public void agregarItem(Item item) {
		items.add(item);
	}
	
	public void eliminarItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> obtenerItems() {
		return items;
	}
	
	public Item buscarItem(String nombre) {
		for(Item itemActual : items) {
			if (itemActual.getNombre().equalsIgnoreCase(nombre)) {
				return itemActual; 
			}
		}
		return null;
	}


}
