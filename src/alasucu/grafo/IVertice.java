package alasucu.grafo;

import java.util.LinkedList;

/**
 *
 * @author Rodrigo Castro
 */
public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<TAdyacencia> getAdyacentes();

    Object getDatos();

    Comparable getEtiqueta();

    boolean getVisitado();

    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

   
    TVertice primerAdyacente();

    void setVisitado(boolean valor);

    
    
}
