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
public class ComponentesConexos {
    
    private HashSet<ComponenteConexo> componentes;

    public HashSet<ComponenteConexo> getComponentes() {
        return componentes;
    }

    public ComponentesConexos() {
        this.componentes = new HashSet<>();
    }   
    
    public ComponenteConexo buscar (Comparable etiqueta){
        for(ComponenteConexo componente : componentes){
            if(componente.getEtiquetasVertices().contains(etiqueta)){
                return componente;
            }
        }
        return null;        
    }
    
    
}
