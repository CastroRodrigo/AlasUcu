
package alasucu;

import alasucu.grafo.ManejadorArchivosGenerico;
import alasucu.grafo.TCamino;
import alasucu.grafo.TCaminos;
import alasucu.grafo.TVertice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * @author Rodrigo Castro
 */
public class AlasUcuAdapter implements IAlasUcuAdapter {
    
    private HashMap<Comparable, Aeropuerto> aeropuertos = new HashMap<>(); 
    private ArrayList<Vuelo> vuelos = new ArrayList<>();
    private ArrayList<Conexiones> conexiones = new ArrayList<>();

    public ArrayList<Vuelo> getVuelos(){
        return vuelos;     
    }

    public HashMap<Comparable, Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }
    
    @Override
    public void cargarAeropuertosVuelos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listarAeropuertos() {
        ArrayList<Comparable> resultado = new ArrayList<>();
        for(Comparable key:aeropuertos.keySet()){
            resultado.add(key);
        }
        
        return resultado.toString();  
    }
    
    /**
     * Metodo que crea las conexiones y las agrega a una coleccion
     */
    public void crearConexiones(){
        Comparable origenConexion = "";
        Comparable destinoConexion = "";
        for(Vuelo aux:vuelos){
            if(origenConexion.equals(aux.getOrigen())==false){
                origenConexion = aux.getOrigen();
                destinoConexion = aux.getDestino();
                conexiones.add(new Conexiones(origenConexion, aux.getDestino()));
            }
            else if(origenConexion.equals(aux.getOrigen())&& destinoConexion.equals(aux.getDestino())==false){
                origenConexion = aux.getOrigen();
                destinoConexion = aux.getDestino();
                conexiones.add(new Conexiones(origenConexion, aux.getDestino()));
            }    
        }
        for(Vuelo vueloAux:vuelos){
            for(Conexiones conexionAux:conexiones){
                if(vueloAux.getOrigen().equals(conexionAux.getOrigen()) && vueloAux.getDestino().equals(conexionAux.getDestino())){
                    conexionAux.agregarVuelos(vueloAux);
                }
            }
        }
    }
    /**
     * Metodo que devuelve un String de origen y destino de las cnexiones
     * @return String de origen destino
     */
    public String listarConexiones(){
        ArrayList<String> resultado = new ArrayList<>();
        for(Conexiones aux:conexiones){
            resultado.add(aux.getOrigen()+"-"+aux.getDestino());
        }
        return resultado.toString();
    }

    @Override
    public TCaminos getListaDeRecorridos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TCaminos getRecorridosDeUnOrigen(Comparable origen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TCamino getRecorridoOptimo(Comparable origen, Comparable destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TCamino getRecorridoOptimo(Comparable origen, Comparable destino, Comparable fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearAeropuertos(String archivoAeropuertos, boolean ignoreHeader) {
        
        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(archivoAeropuertos, ignoreHeader);
        
               
        
        for (String linea :lineasArchivo ) {
            if ((linea != null) && (linea.trim() != "")) {
                String[] dato = linea.split("\\;");
                Aeropuerto aeropuerto = new Aeropuerto(dato[1],dato[2],dato[3],(Comparable)dato[4],dato[5],dato[6],dato[7],dato[8],Double.parseDouble(dato[9]),dato[10],dato[11]);
                aeropuertos.put(dato[4], aeropuerto);
            }
        }
    } 

    @Override
    public void crearVuelos(String achivoVuelos, boolean ignoHead) {
        
        String[] lineasArchivo = ManejadorArchivosGenerico.leerArchivo(achivoVuelos, ignoHead);
        
        
        for(String linea:lineasArchivo){
            if((linea!= null) && (linea.trim() != "")){
                String[] dato = linea.split("\\,");
                Vuelo vuelo = new Vuelo((Comparable)dato[0],(Comparable)dato[1],Integer.parseInt(dato[2]), new Date(dato[3]),new Date(dato[4]));
                vuelos.add(vuelo);
            }    
        }
    }

    @Override
    public String listarVuelos() {
        ArrayList<Comparable> resultado = new ArrayList<>();
        for(Vuelo vuelo:vuelos){
            resultado.add(vuelo.getOrigen().toString()+ "-" + vuelo.getDestino().toString());
        }
        
        return resultado.toString();
    }
    
    
    
}
