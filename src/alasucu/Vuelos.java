
package alasucu;

import java.util.Date;

/**
 * @author Rodrigo Castro
 */
public class Vuelos {
    
    private final Comparable costo;
    private final Date salidaFechaHora;
    private final Date llegadaFechaHora;
    private final Comparable origen;
    private final Comparable destino;

    public Vuelos(Comparable costo, Date salidaFechaHora, Date llegadaFechaHora, Comparable origen, Comparable destino) {
        this.costo = costo;
        this.salidaFechaHora = salidaFechaHora;
        this.llegadaFechaHora = llegadaFechaHora;
        this.origen = origen;
        this.destino = destino;
    }
    
    
    
    
    
}
