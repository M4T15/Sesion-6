package modelo;
import java.util.Stack;
public class BandejaAcciones<T> {
    private Stack<T> pila = new Stack<>();

    public void apilar(T accion) { 
        pila.push(accion); 
    }
    public T desapilar() {
        if (!pila.isEmpty()) {
            return pila.pop();
        }
        return null;
    }
    public boolean estaVacia() { 
        return pila.isEmpty(); 
    }
}