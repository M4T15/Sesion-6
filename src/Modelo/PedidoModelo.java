package Modelo;

import Pedido_Restaurante.EstadoPedido;
import Pedido_Restaurante.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    // Pedidos vigentes (pendientes y completados)
    private List<Pedido> pedidos;
    // Pedidos completados o eliminados
    private List<Pedido> historial;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido buscarPedido(String termino) {
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().equalsIgnoreCase(termino) || p.getTipo().equalsIgnoreCase(termino)) {
                return p;
            }
        }
        return null;
    }

    /** Busca por nombre un pedido que todavia este pendiente. */
    public Pedido buscarPedidoPendiente(String nombre) {
        for (Pedido p : pedidos) {
            if (p.estaPendiente() && p.getNombrePlato().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    /** Elimina el pedido: sale de la lista vigente y pasa al historial con estado ELIMINADO. */
    public boolean eleminarPedido(String nombre) {
        Pedido p = buscarPedido(nombre);
        if (p != null) {
            pedidos.remove(p);
            p.setEstado(EstadoPedido.ELIMINADO);
            historial.add(p);
            return true;
        }
        return false;
    }

    /** Marca un pedido pendiente como completado y lo registra en el historial. */
    public boolean completarPedido(String nombre) {
        Pedido p = buscarPedidoPendiente(nombre);
        if (p != null) {
            p.setEstado(EstadoPedido.COMPLETADO);
            historial.add(p);
            return true;
        }
        return false;
    }

    /** Devuelve los pedidos vigentes que tengan el estado indicado. */
    public List<Pedido> getPedidosPorEstado(EstadoPedido estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado() == estado) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public int contarPendientes() {
        return getPedidosPorEstado(EstadoPedido.PENDIENTE).size();
    }

    /** Historial de pedidos completados o eliminados (en orden cronologico). */
    public List<Pedido> getHistorial() {
        return historial;
    }

    public int contarTotal() {
        return pedidos.size();
    }

    public int contarPortipo(String tipo) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                contador++;
            }
        }
        return contador;
    }
}
