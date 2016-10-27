/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu.grafo;

import java.util.HashSet;

/**
 *
 * @author DANIEL
 */
public class ComponenteConexo {
    
    private HashSet<Comparable> etiquetasVertices;

    public HashSet<Comparable> getEtiquetasVertices() {
        return etiquetasVertices;
    }

    public ComponenteConexo(Comparable etiqueta) {
        this.etiquetasVertices = new HashSet<>();
        etiquetasVertices.add(etiqueta);
    }
    
    public void mezclarComponentes (ComponenteConexo componente){
        etiquetasVertices.addAll(componente.getEtiquetasVertices());
    }
    
    
}
