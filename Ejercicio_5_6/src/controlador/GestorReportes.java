package controlador;

import modelo.*;
import java.util.List;

public class GestorReportes {
    private IDataSource dataSource;
    private BandejaAcciones<AccionDeshacer<Reporte>> historial;
    private boolean usuarioEsDelegado;
    public GestorReportes(IDataSource dataSource, boolean usuarioEsDelegado) {
        this.dataSource = dataSource;
        this.usuarioEsDelegado = usuarioEsDelegado;
        this.historial = new BandejaAcciones<>();
    }
    public void registrarReporte(Reporte reporte) throws Exception {
        if (reporte.getId() == null || reporte.getId().isEmpty()) {
            throw new Exception("DocumentoIncompleto: El reporte debe tener un ID válido.");
        }
        dataSource.guardar(reporte);
    }
    public void cambiarEstadoReporte(String id, EstadoReporte nuevoEstado) throws Exception {
        if (!usuarioEsDelegado) {
            throw new Exception("PermisoDenegado: Solo el delegado puede cambiar el estado de los reportes.");
        }
        Reporte reporte = dataSource.buscarPorId(id);
        if (reporte != null) {
            historial.apilar(new AccionDeshacer<>(reporte, reporte.getEstado()));
            reporte.setEstado(nuevoEstado);
        }
    }
    public void deshacerUltimaAccion() throws Exception {
        if (!usuarioEsDelegado) {
            throw new Exception("PermisoDenegado: Solo el delegado puede deshacer acciones.");
        }
        if (!historial.estaVacia()) {
            AccionDeshacer<Reporte> ultimaAccion = historial.desapilar();
            Reporte reporteModificado = ultimaAccion.getDocumento();
            reporteModificado.setEstado(ultimaAccion.getEstadoAnterior());
            System.out.println("El reporte " + reporteModificado.getId() + " volvió a " + ultimaAccion.getEstadoAnterior());
        } else {
            System.out.println("No hay acciones recientes en la pila para deshacer.");
        }
    }
    public List<Reporte> obtenerPendientes() {
        return dataSource.obtenerTodos();
    }
}