package Item_inventario;

public class Item {
	
	private String nombre;
	private int cantidad;
	private String tipo;
	private String descripcion;

	
	public Item(String nombre, int cantidad, String tipo, String descripcion) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tipo = tipo;
		this.descripcion = descripcion;
		
	}
	
	public void usarItem() {
		if(cantidad > 0) {
			System.out.println("Has usado " + nombre);
			cantidad --;
		}else {
			System.out.println("No te quedan " + nombre + " disponibles.");
		}
	}

	public int getCantidad() {
		return cantidad;
	}


	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre + " [" + tipo + "] - Cantidad: " + cantidad + " (" + descripcion + ") "; 
				
	}

}
