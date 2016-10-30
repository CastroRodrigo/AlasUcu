package alasucu;

import alasucu.grafo.TCamino;
import alasucu.grafo.TCaminos;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Rodrigo Castro
 */
public interface IAlasUcuAdapter {
    
    /**
     * Metodo que toma un archivo con datos de vuelos y crea un HashMap de Aeropuertos
     * @param aeropuertos El nombre del archivo de aeropuertos
     */
    public void crearAeropuertos(String aeropuertos, boolean ignoreHeader);
    
    /**
     * Metodo que toma un archivo con datos de vuelos y crea un ArrayList de Vuelos 
     * @param vuelos El nombre del archivo de vuelos 
     */
    public void crearVuelos(String vuelos, boolean ignoreHeader);
    
    
    
    /**
     * Metodo que carga los datos en el grafo 
     */
    public void cargarAeropuertosVuelos();
    
    /**
     * Metodo que devuelve un String con los aeropuertos 
     * @return Un array de Strings con la lista de aeropuertos
     */
    public String listarAeropuertos();
    /**
     * Metodo que devuelve un String con los vuelos ORIGEN-DESTINO
     * @return devuelve un string de vuelos
     */
    public String listarVuelos();
    
    /**
     * Metodo que retona un una coleccion de caminos
     * @return Un TCaminos con todos los recorridos 
     */
    public TCaminos getListaDeRecorridos();
    
    /**
     * Metodo que devuelve una coleccion de todos los recorridos desde un origen
     * @param origen Un aeropuerto (etiqueta)
     * @return Un TCaminos con todos los recorridos desde un origen
     */
    public TCaminos getRecorridosDeUnOrigen(Comparable origen);
    
    /**
     * Metodo que devuelve el recorrido con menor costo desde un origen a un destino
     * @param origen Etiqueta de un aeropuerto origen
     * @param destino Etiqueta de un aeropuerto destino
     * @return Un TCamino con el recorrido de menor costo
     */
    public TCamino getRecorridoOptimo(Comparable origen, Comparable destino);
    
    /**
     * Metodo que devuelve el recorrido con menor costo desde un origen a un destino y en una fecha de partida
     * @param origen Etiqueta de un aeropuerto origen
     * @param destino Etiqueta de un aeropuerto destino
     * @param fecha La fecha de partida del vuelo
     * @return Un TCamino con el recorrido optimo
     */
    public TCamino getRecorridoOptimo(Comparable origen, Comparable destino, Comparable fecha);
    
}
