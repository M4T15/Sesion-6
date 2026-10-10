package modelo;

import java.util.List;

public interface IDataSource {
    void guardar(Reporte reporte);
    List<Reporte> obtenerTodos();
    Reporte buscarPorId(String id);
}