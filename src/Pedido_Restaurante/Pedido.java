package Pedido_Restaurante;

public class Pedido {
    private String nombrePlato;
    private String tipo;
    private EstadoPedido estado;

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE; // todo pedido nuevo inicia pendiente
    }

    public Pedido(String nombrePlato) {
        this(nombrePlato, "General");
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public boolean estaPendiente() {
        return estado == EstadoPedido.PENDIENTE;
    }

    public boolean estaCompletado() {
        return estado == EstadoPedido.COMPLETADO;
    }
}
