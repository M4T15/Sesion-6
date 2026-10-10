package modelo;

public class AccionDeshacer<T extends Reporte> {
    private T documento;
    private EstadoReporte estadoAnterior;

    public AccionDeshacer(T documento, EstadoReporte estadoAnterior) {
        this.documento = documento;
        this.estadoAnterior = estadoAnterior;
    }
    
    public T getDocumento() { 
        return documento; 
    }
    
    public EstadoReporte getEstadoAnterior() { 
        return estadoAnterior; 
    }
}