package modelo;

public class JustificacionFalta extends Reporte {
    public JustificacionFalta(String id, Estudiante est, String desc) { 
        super(id, est, desc); 
    }
    @Override 
    public String obtenerTipo() { return "Justificación"; }
}