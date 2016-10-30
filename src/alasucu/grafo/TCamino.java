
package alasucu.grafo;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Rodrigo Castro
 */
public class TCamino {
    
    private TVertice origen;
    private Collection <Comparable> otrosVertices;
    private Double costoTotal;
    
    public TVertice getOrigen() {
        return origen;
    }
    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }    

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    public TCamino(TVertice v) {
        this.origen = v;
        this.otrosVertices = new LinkedList<>();
        this.costoTotal = 0.0;
        
    }
    
    public TCamino copiar (){
        TVertice nuevoOrigen = new TVertice (this.getOrigen().getEtiqueta());
        nuevoOrigen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        TCamino copia = new TCamino (nuevoOrigen);
        copia.getOtrosVertices().addAll(this.getOtrosVertices());        
        copia.setCostoTotal(costoTotal);
        return copia;        
    }    
    public boolean agregarAdyacencia (TAdyacencia adyacenciaActual){
        if (adyacenciaActual.getDestino() != null){
            costoTotal = costoTotal + ((Number)adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }
    public boolean eliminarAdyacencia (TAdyacencia adyacenciaActual){
        if(otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())){
            costoTotal = costoTotal - ((Number)adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta()));
        }
        return false;
    }
    
    public void imprimirEtiquetas(){
        System.out.print(origen.getEtiqueta() + " - ");
        int contador = 1;
        for(Comparable c : otrosVertices){
            System.out.print(c);
            if (contador < otrosVertices.size()){
                System.out.print(" - ");
            }
            contador ++;
        }
        System.out.print(" (Costo total: " + costoTotal + ")");
        System.out.println();
    }
    public String imprimirEtiqueta(){
        String resultado = "";
        resultado += origen.getEtiqueta() + " - ";        
        int contador = 1;
        for(Comparable c : otrosVertices){
            resultado += c;   
            if (contador < otrosVertices.size()){
                resultado += " - "; 
            }
            contador ++;
        }
        resultado += " (Costo total: " + costoTotal + ")";
        return resultado;
    }
    
}
