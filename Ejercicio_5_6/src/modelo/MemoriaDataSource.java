package modelo;

import java.util.ArrayList;
import java.util.List;

public class MemoriaDataSource implements IDataSource {
    private List<Reporte> bd = new ArrayList<>();

    @Override 
    public void guardar(Reporte reporte) { 
        bd.add(reporte); 
    }
    
    @Override 
    public List<Reporte> obtenerTodos() { 
        return bd; 
    }
    
    @Override
    public Reporte buscarPorId(String id) {
        for (Reporte r : bd) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }
}