/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu.grafo;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author DANIEL
 */
public class TAristas {
    
    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    private Collection<TArista> aristas;

    public Collection<TArista> getAristas() {
        return aristas;
    }    
    
    public TAristas(){
        aristas = new LinkedList<>();
    }
    public TAristas(LinkedList<TArista> aristas){
        this.aristas = aristas;        
    }   
    
    /**
     * Busca dentro de la lista de aristas una arista que conecte a 
     * etOrigen con etDestino
     * @param etOrigen
     * @param etDestino
     * @return 
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        TArista resultado = null;
        for(TArista arista : aristas){
            if (arista.getEtiquetaOrigen().compareTo(etOrigen) == 0 &&
                    arista.etiquetaDestino.compareTo(etDestino)== 0){
                resultado = arista;
                return resultado;
            }
        }
        return resultado;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de
     * VerticesU con cualquier otro de VerticesV y cuyo costo sea el minimo
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return 
     */
    public TArista buscarMin(Collection<TVertice> VerticesU, Collection<TVertice> VerticesV) {
        //TODO: ---------COMPLETAR ALGORITMO--------
        // para todo u en Vertices U
        // para todo v en Vertices V
        // tA =buscar (u, v)
        // si tA <> null y tA.costo < costoMin entonces
        // tAMin = tA y costoMin = tA.costo
        // fin para todo v
        // fin para todo u
        // devolver tAMin
        //------------ SOLUCIÓN -------------
        TArista tempArista = null;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;        
        for(TVertice u : VerticesU){
            for(TVertice v : VerticesV){
                tempArista = this.buscar(u.getEtiqueta(),v.getEtiqueta());
                if(tempArista != null && tempArista.getCosto() < costoMin){
                    tAMin = tempArista;
                    costoMin = tempArista.getCosto();
                }
            }
        }
        return tAMin;       
    }
    
    public TArista buscarMinima(){
        
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;        
        for(TArista a : this.getAristas()){
            if(a.getCosto()< costoMin){
                tAMin = a;
                costoMin = a.getCosto();
            }
            
        }
        return tAMin;        
    }
    
    public String imprimirEtiquetas() {
        if (aristas.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for(TArista arista : this.aristas){
            salida.append(arista.getEtiquetaOrigen() + " - " + 
               arista.getEtiquetaDestino() + " - " + arista.getCosto()+ " \n ");
        }        
        return salida.toString();
    }

    /**
     * Inserta la arista al final de la coleccion de aristas
     * @param tArista 
     */
    public void insertarAlFinal(TArista tArista) {
        aristas.add(tArista);
    }
    
}
