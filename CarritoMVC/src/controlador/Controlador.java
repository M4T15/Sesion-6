package controlador;

import modelo.Compra;
import modelo.Producto;
import modelo.Tienda;
import vista.VistaConsola;

public class Controlador {
    private final Tienda tienda;
    private final VistaConsola vista;

    public Controlador(Tienda tienda, VistaConsola vista) {
        this.tienda = tienda;
        this.vista = vista;
    }

    public void iniciar() {
        int op;
        do {
            vista.mostrarMenu();
            op = vista.leerEntero("seleciones una opcion");
            switch (op) {
                case 1: agregarProductoCatalogo(); 
                    break;
                case 2: vista.mostrarProductos(tienda.getProductos());
                    break;
                case 3: eliminarPedido(); 
                    break;
                case 4: actualizarPedido(); 
                    break;
                case 5: buscarPedido(); 
                    break;
                case 6: contarPedidos(); 
                    break;
                case 7: agregarAlCarrito(); break;
                case 8: vista.mostrarCarrito(tienda.getCarrito()); 
                    break;
                case 9: eliminarDelCarrito(); 
                    break;
                case 10: aplicarDescuento(); 
                    break;
                case 11: vista.mostrarEnvio(tienda.getCarrito()); 
                    break;
                case 12: vista.mostrarHistorial(tienda.getHistorial());
                    break;
                case 13: realizarCompra(); 
                    break;
                case 0: vista.mostrarMensaje("acabamos"); 
                    break;
                default: vista.mostrarMensaje("ingrese solo una opcion valida");
            }
        } while (op != 0);
    }

    private void agregarProductoCatalogo() {
        String nombre = vista.leerTexto("nombre :");
        if (nombre.isEmpty()) {
            vista.mostrarMensaje("el nombre no puede estar vacio");
            return;
        }
        String tipo = vista.leerTexto("tipo :"); 
        if (tipo.isEmpty()) {
            vista.mostrarMensaje("el tipo no puede estar vacio");
            return;
        }
        double precio = vista.leerDecimal("El precio es de ");
        int stock = vista.leerEntero("Hay la cantidad de  ");
        if (precio <= 0 || stock < 0) {
            vista.mostrarMensaje("El precio no puede ser negativo y la cantidad tampoco ");
            return;
        }
        Producto p = tienda.agregarProducto(nombre, tipo, precio, stock); 
        vista.mostrarMensaje("el producto fue agregado ed manera correcta con el ID de " + p.getId() );
    }

    private void eliminarPedido() {
        if (tienda.getProductos().isEmpty()) {
            vista.mostrarMensaje("no hay productos para eliminar");
            return;
        }
        vista.mostrarProductos(tienda.getProductos());
        int id = vista.leerEntero("ID del producto a eliminar: ");
        if (tienda.eliminarProducto(id)) {
            vista.mostrarMensaje("Producto eliminado correctamente");
        } else {
            vista.mostrarMensaje("No se encontro un producto con ese ID");
        }
    }

    private void actualizarPedido() {
        if (tienda.getProductos().isEmpty()) {
            vista.mostrarMensaje("no hay productos para actualizar");
            return;
        }
        vista.mostrarProductos(tienda.getProductos());
        int id = vista.leerEntero("ID del producto a actualizar: ");
        Producto p = tienda.buscarProducto(id);
        if (p == null) {
            vista.mostrarMensaje("el producto no existe");
            return;
        }
        String nuevoNombre = vista.leerTexto("Nuevo nombre: ");
        String nuevoTipo = vista.leerTexto("Nuevo tipo: ");
        if (nuevoNombre.isEmpty() || nuevoTipo.isEmpty()) {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacios");
            return;
        }
        if (tienda.actualizarProducto(id, nuevoNombre, nuevoTipo)) {
            vista.mostrarMensaje("Producto actualizado correctamente");
        } else {
            vista.mostrarMensaje("Error al actualizar el producto");
        }
    }

    private void buscarPedido() {
        String criterio = vista.leerTexto("Ingrese el nombre o tipo a buscar: ");
        java.util.List<Producto> resultados = tienda.buscarPorNombreOTipo(criterio);
        vista.mostrarProductos(resultados);
    }

    private void contarPedidos() {
        int total = tienda.contarTotal();
        java.util.Map<String, Integer> porTipos = tienda.contarPorTipos();
        vista.mostrarConteo(total, porTipos);
    }

    private void agregarAlCarrito() {
        vista.mostrarProductos(tienda.getProductos());
        int id = vista.leerEntero("ID del producto :");
        Producto p = tienda.buscarProducto(id);
        if (p == null) {
            vista.mostrarMensaje("el producto que busca no existe");
            return;
        }
        int cant = vista.leerEntero("Cantidad: ");
        if (cant <= 0) {
            vista.mostrarMensaje("La cantidad debe ser mayor a 0");
            return;
        }
        int yaEnCarrito = tienda.getCarrito().cantidadEnCarrito(id);
        if (yaEnCarrito + cant > p.getStock()) {
            vista.mostrarMensaje("No hay suficentes productos ,solo quedan: " + p.getStock()
                    + " (en el carro tienes " + yaEnCarrito + ")");
            return;
        }
        tienda.getCarrito().agregar(p, cant);
        vista.mostrarMensaje("Agregado: " + cant + " x " + p.getNombre());
    }

    private void eliminarDelCarrito() {
        if (tienda.getCarrito().estaVacio()) {
            vista.mostrarMensaje("carro vacio ");
            return;
        }
        vista.mostrarCarrito(tienda.getCarrito());
        int id = vista.leerEntero("ID del prodcuto q sera eliminado ");
        if (tienda.getCarrito().eliminar(id)) {
            vista.mostrarMensaje("Producto eliminado de carro ");
        } else {
            vista.mostrarMensaje("Ese producto no esta en el carro ");
        }
    }

    private void aplicarDescuento() {
        if (tienda.getCarrito().estaVacio()) {
            vista.mostrarMensaje("el carro esta vacio agrega algo priemro");
            return;
        }
        String codigo = vista.leerTexto("Codigo del descuento :");
        Double pct = tienda.porcentajeDeCodigo(codigo);
        if (pct == null) {
            vista.mostrarMensaje("codigo no existe");
            return;
        }
        tienda.getCarrito().aplicarDescuento(codigo.trim().toUpperCase(), pct);
        vista.mostrarMensaje("Descuento del " + pct.intValue() );
    }

    private void realizarCompra() {
        if (tienda.getCarrito().estaVacio()) {
            vista.mostrarMensaje("carro vacio");
            return;
        }
        vista.mostrarCarrito(tienda.getCarrito());
        String conf = vista.leerTexto("Conprar ? (s/n): ");
        if (!conf.equalsIgnoreCase("s")) {
            vista.mostrarMensaje("Compra cancelada");
            return;
        }
        Compra c = tienda.realizarCompra();
        vista.mostrarMensaje(String.format("Compra realizada. Total pagado:", c.getNumero(), c.getTotal()));
    }
}