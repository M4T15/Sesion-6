package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tienda {
    private final List<Producto> productos = new ArrayList<>();
    private final List<Compra> historial = new ArrayList<>();
    private final Map<String, Double> descuentos = new HashMap<>();
    private final Carrito carrito = new Carrito();
    private int siguienteId = 1;
    private int siguienteCompra = 1;

    public Tienda() {
        descuentos.put("DESC10", 10.0);
        descuentos.put("DESC20", 20.0);
        agregarProducto("Laptop", "Tecnología", 2500, 5);
        agregarProducto("Mouse", "Accesorio", 45, 20);
        agregarProducto("Teclado", "Accesorio", 120, 10);
        agregarProducto("Audifonos", "Accesorio", 89, 8);
    }

    public Producto agregarProducto(String nombre, String tipo, double precio, int stock) {
        Producto p = new Producto(siguienteId++, nombre, tipo, precio, stock);
        productos.add(p);
        return p;
    }

    public List<Producto> getProductos() { return productos; }

    public Producto buscarProducto(int id) {
        for (Producto p : productos) if (p.getId() == id) return p;
        return null;
    }
    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    public boolean actualizarProducto(int id, String nuevoNombre, String nuevoTipo) {
        Producto p = buscarProducto(id);
        if (p != null) {
            p.setNombre(nuevoNombre);
            p.setTipo(nuevoTipo);
            return true;
        }
        return false;
    }

    public List<Producto> buscarPorNombreOTipo(String criterio) {
        String busqueda = criterio.toLowerCase();
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(busqueda) || 
                             p.getTipo().toLowerCase().contains(busqueda))
                .collect(Collectors.toList());
    }

    public int contarTotal() {
        return productos.size();
    }

    public Map<String, Integer> contarPorTipos() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Producto p : productos) {
            conteo.put(p.getTipo(), conteo.getOrDefault(p.getTipo(), 0) + 1);
        }
        return conteo;
    }


    public Carrito getCarrito() { return carrito; }
    public List<Compra> getHistorial() { return historial; }

    public Double porcentajeDeCodigo(String codigo) {
        return descuentos.get(codigo.trim().toUpperCase());
    }

    public Compra realizarCompra() {
        for (ItemCarrito it : carrito.getItems()) {
            it.getProducto().reducirStock(it.getCantidad());
        }
        Compra c = new Compra(siguienteCompra++, carrito);
        historial.add(c);
        carrito.vaciar();
        return c;
    }
}