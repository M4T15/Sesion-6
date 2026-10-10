package Pedido_Restaurante;

/**
 * Estados posibles de un pedido.
 */
public enum EstadoPedido {
    PENDIENTE("Pendiente"),
    COMPLETADO("Completado"),
    ELIMINADO("Eliminado");

    private final String descripcion;

    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
