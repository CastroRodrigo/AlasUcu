
package alasucu;

import java.util.Date;

/**
 * @author Rodrigo Castro
 */
public class Vuelo {
    
    private final Comparable costo;
    private final Date salidaFechaHora;
    private final Date llegadaFechaHora;
    private final Comparable origen;
    private final Comparable destino;

    public Vuelo(Comparable origen, Comparable destino,Comparable costo, Date salidaFechaHora, Date llegadaFechaHora) {
        this.costo = costo;
        this.salidaFechaHora = salidaFechaHora;
        this.llegadaFechaHora = llegadaFechaHora;
        this.origen = origen;
        this.destino = destino;
    }

    public Comparable getCosto() {
        return costo;
    }

    public Date getSalidaFechaHora() {
        return salidaFechaHora;
    }

    public Date getLlegadaFechaHora() {
        return llegadaFechaHora;
    }

    public Comparable getOrigen() {
        return origen;
    }

    public Comparable getDestino() {
        return destino;
    }
    
    
    
    
    
}
