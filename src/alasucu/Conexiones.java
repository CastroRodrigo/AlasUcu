
package alasucu;

import java.util.ArrayList;

/**
 * @author Rodrigo Castro
 */
public class Conexiones {
    
    private final Comparable origen;
    private final Comparable destino;
    private ArrayList<Vuelo> vuelos = new ArrayList<>();

    public Conexiones(Comparable origen, Comparable destino) {
        this.origen = origen;
        this.destino = destino;
    }
    
    public Comparable getOrigen() {
        return origen;
    }

    public Comparable getDestino() {
        return destino;
    }
    
    public ArrayList<Vuelo> getVuelos(){
        return vuelos;
    }
    /**
     * Metodo que agrega cuelos a una coleccion en cada conexion
     * @param vuelo Un objeto de tipo Vuelo
     */
    public void agregarVuelos(Vuelo vuelo){
        vuelos.add(vuelo);
    }
}
