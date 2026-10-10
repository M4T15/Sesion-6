package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private final int numero;
    private final LocalDateTime fecha;
    private final List<String> detalle = new ArrayList<>();
    private final double subtotal, descuento, envio, total;

    public Compra(int numero, Carrito carrito) {
        this.numero = numero;
        this.fecha = LocalDateTime.now();
        for (ItemCarrito it : carrito.getItems()) {
            detalle.add(it.getCantidad() + " x " + it.getProducto().getNombre()
                    + " = S/ " + String.format("%.2f", it.getSubtotal()));
        }
        this.subtotal = carrito.getSubtotal();
        this.descuento = carrito.getDescuento();
        this.envio = carrito.getEnvio();
        this.total = carrito.getTotal();
    }

    public int getNumero() { return numero; }
    public String getFecha() { return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); }
    public List<String> getDetalle() { return detalle; }
    public double getSubtotal() { return subtotal; }
    public double getDescuento() { return descuento; }
    public double getEnvio() { return envio; }
    public double getTotal() { return total; }
}