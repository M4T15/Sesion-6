package modelo;

import java.util.ArrayList;
import java.util.List;
public class SistemaModelo {
    public static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        public Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    public static class PilaAcciones<T> {
        private Nodo<T> cima;
        public PilaAcciones() {
            this.cima = null;
        }
        public void apilar(T elemento) {
            Nodo<T> nuevoNodo = new Nodo<>(elemento);
            nuevoNodo.siguiente = cima;
            cima = nuevoNodo;
        }
        public T desapilar() {
            if (estaVacia()) return null;
            T dato = cima.dato;
            cima = cima.siguiente;
            return dato;
        }
        public boolean estaVacia() {
            return cima == null;
        }
    }
    public static class Reporte {
        private String id;
        private String titulo;
        private String estado;
        public Reporte(String id, String titulo) {
            this.id = id;
            this.titulo = titulo;
            this.estado = "Pendiente";
        }
        public String getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
        @Override
        public String toString() {
            return "[" + id + "] " + titulo + " - Estado: " + estado;
        }
    }
    public interface IRepositorio<T> {
        void guardar(T entidad);
        void eliminar(T entidad);
        List<T> obtenerTodos();
    }

    public static class RepositorioReportesMemoria implements IRepositorio<Reporte> {
        private List<Reporte> bdSimulada = new ArrayList<>();

        @Override
        public void guardar(Reporte entidad) { 
            bdSimulada.add(entidad); 
        }

        @Override
        public void eliminar(Reporte entidad) { 
            bdSimulada.remove(entidad); 
        }

        @Override
        public List<Reporte> obtenerTodos() { 
            return bdSimulada; 
        }
    }
}