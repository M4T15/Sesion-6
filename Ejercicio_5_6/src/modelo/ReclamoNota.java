package modelo;

public class ReclamoNota extends Reporte {
    private Curso curso;
    public ReclamoNota(String id, Estudiante est, String desc, Curso curso) { 
        super(id, est, desc); 
        this.curso = curso;
    }
    @Override 
    public String obtenerTipo() { return "Reclamo Nota"; }
}