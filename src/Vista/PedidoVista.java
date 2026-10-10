package Vista;

import Pedido_Restaurante.Pedido;

import java.util.List;
import java.util.Scanner;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public String solicitarNombrePlato() {
        System.out.print("Ingrese el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipoPlato() {
        System.out.print("Ingrese el tipo de plato (ej. bebida, postre): ");
        return scanner.nextLine();
    }

    public String solicitarDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay registrado ningun plato");
        } else {
            System.out.println("---- Platos Registrados ----");
            for (Pedido pedido : pedidos) {
                System.out.println("- " + pedido.getNombrePlato() + " [" + pedido.getTipo() + "] - " + pedido.getEstado());
            }
        }
    }

    public void mostrarPedidosPorEstado(List<Pedido> pedidos, String tituloEstado) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos " + tituloEstado.toLowerCase());
        } else {
            System.out.println("---- Pedidos " + tituloEstado + " ----");
            for (Pedido pedido : pedidos) {
                System.out.println("- " + pedido.getNombrePlato() + " [" + pedido.getTipo() + "]");
            }
        }
    }

    public void mostrarContadorPendientes(int pendientes) {
        System.out.println("Pedidos pendientes: " + pendientes);
    }

    public void mostrarHistorial(List<Pedido> historial) {
        if (historial.isEmpty()) {
            System.out.println("El historial esta vacio");
        } else {
            System.out.println("---- Historial de Pedidos ----");
            int i = 1;
            for (Pedido pedido : historial) {
                System.out.println(i++ + ". " + pedido.getNombrePlato() + " [" + pedido.getTipo() + "] - " + pedido.getEstado());
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n ---- MENU ---- ");
        System.out.println("1 = Ingresar nuevo plato");
        System.out.println("2 = Mostrar todos los platos");
        System.out.println("3 = Eliminar un pedido");
        System.out.println("4 = Actualizar un pedido");
        System.out.println("5 = Buscar un pedido");
        System.out.println("6 = Contar pedidos");
        System.out.println("7 = Marcar pedido como completo");
        System.out.println("8 = Mostrar pedidos por estado");
        System.out.println("9 = Contador de pedidos pendientes");
        System.out.println("10 = Historial de pedidos");
        System.out.println("0 = Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Seleccione una opcion: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
