package modelo;
public class Excepciones {
}
class PermisoDenegadoException extends Exception {
    public PermisoDenegadoException(String mensaje) { 
        super(mensaje); 
    }
}
class DocumentoIncompletoException extends Exception {
    public DocumentoIncompletoException(String mensaje) { 
        super(mensaje); 
    }
}