package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    public static final double ENVIO_BASE = 15.0;
    public static final double ENVIO_GRATIS_DESDE = 200.0;

    private final List<ItemCarrito> items = new ArrayList<>();
    private double porcentajeDescuento = 0;
    private String codigoDescuento = null;

    public void agregar(Producto p, int cantidad) {
        for (ItemCarrito it : items) {
            if (it.getProducto().getId() == p.getId()) {
                it.sumarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(p, cantidad));
    }

    public boolean eliminar(int idProducto) {
        return items.removeIf(it -> it.getProducto().getId() == idProducto);
    }

    public int cantidadEnCarrito(int idProducto) {
        for (ItemCarrito it : items) {
            if (it.getProducto().getId() == idProducto) return it.getCantidad();
        }
        return 0;
    }

    public List<ItemCarrito> getItems() { return items; }
    public boolean estaVacio() { return items.isEmpty(); }

    public void aplicarDescuento(String codigo, double porcentaje) {
        this.codigoDescuento = codigo;
        this.porcentajeDescuento = porcentaje;
    }

    public String getCodigoDescuento() { return codigoDescuento; }

    public double getSubtotal() {
        double s = 0;
        for (ItemCarrito it : items) s += it.getSubtotal();
        return s;
    }

    public double getDescuento() { return getSubtotal() * porcentajeDescuento / 100.0; }

    public double getEnvio() {
        if (items.isEmpty()) return 0;
        return (getSubtotal() - getDescuento()) >= ENVIO_GRATIS_DESDE ? 0 : ENVIO_BASE;
    }

    public double getTotal() { return getSubtotal() - getDescuento() + getEnvio(); }

    public void vaciar() {
        items.clear();
        porcentajeDescuento = 0;
        codigoDescuento = null;
    }
}