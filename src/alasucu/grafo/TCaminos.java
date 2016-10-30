package alasucu.grafo;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Rodrigo Castro
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
