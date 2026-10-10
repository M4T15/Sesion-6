package modelo;

public class Producto {
    private final int id;
    private String nombre;
    private String tipo; // solicitado por el lab
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String tipo, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo; // solicitado por el lab
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	} 

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { 
    	this.tipo = tipo; 
    	} 

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { 
    	this.precio = precio; 
    	}

    public int getStock() { return stock; }
    public void setStock(int stock) { 
    	this.stock = stock; 
    	}

    public void reducirStock(int cantidad) {
    	this.stock -= cantidad; 
    	}
}