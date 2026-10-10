package modelo;

public class ReporteInfraestructura extends Reporte {
    public ReporteInfraestructura(String id, Estudiante est, String desc) { 
        super(id, est, desc); 
    }
    @Override 
    public String obtenerTipo() { return "Infraestructura"; }
}