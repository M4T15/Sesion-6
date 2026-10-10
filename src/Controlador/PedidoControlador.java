package Controlador;

import java.util.List;

import Pedido_Restaurante.EstadoPedido;
import Pedido_Restaurante.Pedido;
import Modelo.PedidoModelo;
import Vista.PedidoVista;

public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarPedido() {
        String nombrePlato = vista.solicitarNombrePlato().trim();
        String tipoPlato = vista.solicitarTipoPlato().trim();
        if (!nombrePlato.isEmpty()) {
            Pedido pedido = tipoPlato.isEmpty() ? new Pedido(nombrePlato) : new Pedido(nombrePlato, tipoPlato);
            modelo.agregarPedido(pedido);
            vista.mostrarMensaje("Pedido agregado: " + nombrePlato);
        } else {
            vista.mostrarMensaje("Error: el nombre del plato no puede estar vacio");
        }
    }

    public void mostrarPedidos() {
        List<Pedido> pedidos = modelo.getPedidos();
        vista.mostrarPedidos(pedidos);
    }

    public void eliminarPedido() {
        String nombre = vista.solicitarDato("Ingrese el nombre del plato a eliminar: ");
        if (modelo.eleminarPedido(nombre)) {
            vista.mostrarMensaje("Pedido eliminado con exito (guardado en el historial).");
        } else {
            vista.mostrarMensaje("No se encontro el pedido.");
        }
    }

    public void actualizarPedido() {
        String nombre = vista.solicitarDato("Ingrese el nombre del plato a actualizar: ");
        Pedido pedido = modelo.buscarPedido(nombre);
        if (pedido != null) {
            String nuevoNombre = vista.solicitarDato("Ingrese el nuevo nombre: ");
            pedido.setNombrePlato(nuevoNombre);
            vista.mostrarMensaje("Pedido actualizado.");
        } else {
            vista.mostrarMensaje("No se encontro el pedido.");
        }
    }

    public void buscarPedido() {
        String termino = vista.solicitarDato("Ingrese nombre o tipo a buscar: ");
        Pedido pedido = modelo.buscarPedido(termino);
        if (pedido != null) {
            vista.mostrarMensaje("Encontrado: " + pedido.getNombrePlato() + " [" + pedido.getTipo() + "] - " + pedido.getEstado());
        } else {
            vista.mostrarMensaje("No se encontraron coincidencias.");
        }
    }

    public void contarPedidos() {
        vista.mostrarMensaje("Total de pedidos: " + modelo.contarTotal());
        String tipo = vista.solicitarDato("Ingrese un tipo para contar (o enter para omitir): ");
        if (!tipo.isEmpty()) {
            vista.mostrarMensaje("Total de tipo '" + tipo + "': " + modelo.contarPortipo(tipo));
        }
    }

    // ---- Nuevas funcionalidades ----

    public void marcarPedidoCompleto() {
        String nombre = vista.solicitarDato("Ingrese el nombre del plato a marcar como completo: ");
        if (modelo.completarPedido(nombre)) {
            vista.mostrarMensaje("Pedido marcado como completo.");
        } else {
            vista.mostrarMensaje("No se encontro un pedido pendiente con ese nombre.");
        }
        mostrarContadorPendientes();
    }

    public void mostrarPedidosPorEstado() {
        String opcion = vista.solicitarDato("Ver pedidos: 1 = Pendientes, 2 = Completos: ");
        switch (opcion) {
            case "1":
                vista.mostrarPedidosPorEstado(modelo.getPedidosPorEstado(EstadoPedido.PENDIENTE), "Pendientes");
                break;
            case "2":
                vista.mostrarPedidosPorEstado(modelo.getPedidosPorEstado(EstadoPedido.COMPLETADO), "Completos");
                break;
            default:
                vista.mostrarMensaje("Opcion no valida.");
        }
    }

    public void mostrarContadorPendientes() {
        vista.mostrarContadorPendientes(modelo.contarPendientes());
    }

    public void mostrarHistorial() {
        vista.mostrarHistorial(modelo.getHistorial());
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":  agregarPedido(); break;
                case "2":  mostrarPedidos(); break;
                case "3":  eliminarPedido(); break;
                case "4":  actualizarPedido(); break;
                case "5":  buscarPedido(); break;
                case "6":  contarPedidos(); break;
                case "7":  marcarPedidoCompleto(); break;
                case "8":  mostrarPedidosPorEstado(); break;
                case "9":  mostrarContadorPendientes(); break;
                case "10": mostrarHistorial(); break;
                case "0":  vista.mostrarMensaje("Saliendo..."); break;
                default:   vista.mostrarMensaje("Opcion no valida, ingrese de nuevo");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }
}
