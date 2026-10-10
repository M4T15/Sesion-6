package modelo;

public abstract class Reporte {
    protected String id;
    protected Estudiante estudiante;
    protected EstadoReporte estado;
    protected String descripcion;

    public Reporte(String id, Estudiante estudiante, String descripcion) {
        this.id = id;
        this.estudiante = estudiante;
        this.descripcion = descripcion;
        this.estado = EstadoReporte.PENDIENTE;
    }

    public String getId() { return id; }
    public EstadoReporte getEstado() { return estado; }
    public void setEstado(EstadoReporte estado) { this.estado = estado; }
    
    public abstract String obtenerTipo();

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Estado: %s)", 
            obtenerTipo(), id, estudiante.getNombre(), estado);
    }
}