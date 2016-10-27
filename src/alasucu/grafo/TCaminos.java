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
public class TCaminos {
    
    private Collection<TCamino> caminos;

    public Collection<TCamino> getCaminos() {
        return caminos;
    }    

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public void imprimir (){
        for(TCamino camino : caminos){
            camino.imprimirEtiquetas();
            //System.out.println(camino.imprimirEtiqueta());            
        }
    }
    
}
