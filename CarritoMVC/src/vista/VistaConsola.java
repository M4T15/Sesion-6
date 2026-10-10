package vista;

import modelo.Carrito;
import modelo.Compra;
import modelo.ItemCarrito;
import modelo.Producto;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VistaConsola {
    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("1. Agregar producto (al catalogo)");
        System.out.println("2. Listar productos");
        System.out.println("3. Eliminar pedido");
        System.out.println("4. Actualizar pedido");
        System.out.println("5. Buscar pedido");
        System.out.println("6. Contar pedidos");
        System.out.println("7. Agregar producto al carrito");
        System.out.println("8. Ver carrito");
        System.out.println("9. Eliminar producto del carrito");
        System.out.println("10. Aplicar descuento");
        System.out.println("11. Calcular envio");
        System.out.println("12. Ver historial de compras");
        System.out.println("13. Realizar compra");
        System.out.println("0. Salir");
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\nProductos");
        for (Producto p : productos) {
            System.out.println(p.getId() + ". [" + p.getTipo() + "] " + p.getNombre()
                    + " - S/ " + String.format("%.2f", p.getPrecio())
                    + " (stock: " + p.getStock() + ")");
        }
    }

    public void mostrarConteo(int total, Map<String, Integer> porTipos) {
        System.out.println("\nCantidad total de pedidos: " + total);
        System.out.println("Cantidad por tipos:");
        for (Map.Entry<String, Integer> entry : porTipos.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void mostrarCarrito(Carrito c) {
        if (c.estaVacio()) {
            System.out.println("El carrito esta vacio");
            return;
        }
        
        System.out.println("carrito");
        for (ItemCarrito it : c.getItems()) {
            System.out.println("ID: " + it.getProducto().getId() +  
                               " | [" + it.getProducto().getTipo() + "] Producto: " + it.getProducto().getNombre() +  
                               " | Cant: " + it.getCantidad() +  
                               " | Subtotal: S/ " + it.getSubtotal());
        }
        
        System.out.println("Subtotal: S/ " + c.getSubtotal());
        System.out.println("Descuento: S/ " + c.getDescuento());
        System.out.println("Envio: S/ " + c.getEnvio());
        System.out.println("TOTAL: S/ " + c.getTotal());
    }
    
    public void mostrarEnvio(Carrito c) {
        if (c.estaVacio()) {
            System.out.println("agrega productos para calcular el envio");
            return;
        }
        
        if (c.getEnvio() == 0) {
            System.out.println("envio gratis (compra desde s/ " + Carrito.ENVIO_GRATIS_DESDE + ")");
        } else {
            System.out.println("envio: s/ " + c.getEnvio() + " (gratis desde s/ " + Carrito.ENVIO_GRATIS_DESDE + ")");
        }
    }

    public void mostrarHistorial(List<Compra> historial) {
        if (historial.isEmpty()) {
            System.out.println("todavia no hay compras");
            return;
        }
        System.out.println("historial de compras");
        for (Compra c : historial) {
            System.out.println("compra " + c.getNumero() + " - " + c.getFecha());
            for (String linea : c.getDetalle()) {
                System.out.println("    " + linea);
            }
            System.out.println("    subtotal: s/ " + c.getSubtotal() +  
                               " | desc: s/ " + c.getDescuento() +  
                               " | envio: s/ " + c.getEnvio() +  
                               " | total: s/ " + c.getTotal());
        }
    }

    public void mostrarMensaje(String m) { System.out.println(m); }

    public String leerTexto(String pregunta) {
        System.out.print(pregunta);
        return sc.hasNextLine() ? sc.nextLine().trim() : "0";
    }

    public int leerEntero(String pregunta) {
        while (true) {
            String t = leerTexto(pregunta);
            try {
                return Integer.parseInt(t);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero entero valido");
            }
        }
    }

    public double leerDecimal(String pregunta) {
        while (true) {
            String t = leerTexto(pregunta).replace(',', '.');
            try {
                return Double.parseDouble(t);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido");
            }
        }
    }
}