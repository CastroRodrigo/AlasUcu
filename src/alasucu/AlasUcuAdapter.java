
package alasucu;

import alasucu.grafo.ManejadorArchivosGenerico;
import alasucu.grafo.TCamino;
import alasucu.grafo.TCaminos;
import alasucu.grafo.TGrafoDirigido;
import alasucu.grafo.TVertice;
import alasucu.grafo.UtilGrafos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * @author Rodrigo Castro
 */
public class AlasUcuAdapter{
    
    private HashMap<Comparable, Aeropuerto> aeropuertos = new HashMap<>(); 
    private ArrayList<Vuelo> vuelos = new ArrayList<>();
    private ArrayList<Conexiones> conexiones = new ArrayList<>();

    public ArrayList<Vuelo> getVuelos(){
        return vuelos;     
    }

    public HashMap<Comparable, Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }
    
    public ArrayList<Conexiones> getConexiones(){
        return conexiones;
    }
    
    public void cargarAeropuertosVuelos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String listarAeropuertos() {
        ArrayList<Comparable> resultado = new ArrayList<>();
        for(Comparable key:aeropuertos.keySet()){
            resultado.add(key);
        }
        
        return resultado.toString();  
    }
    
    public String InformacionAeropuerto(String aeropuerto){
        StringBuilder resultado = new StringBuilder();
        if(aeropuertos.containsKey(aeropuerto)){
            Aeropuerto aux = aeropuertos.get(aeropuerto);
            resultado.append("NOMBRE: ").append(aux.getName());
            resultado.append("PAIS: ").append(aux.getCountry());
            resultado.append("CIUDAD: ").append(aux.getCity());
            resultado.append("IATA: ").append(aux.getIata());
            resultado.append("ICAO: ").append(aux.getIcao());
            resultado.append("LONGITUD: ").append(aux.getLongitude());
            resultado.append("LATITUD: ").append(aux.getLongitude());
            resultado.append("ALTITUDE: ").append(aux.getAltitude());
            resultado.append("ZONA HORARIA: ").append(aux.getTimeZone());
            resultado.append("DST: ").append(aux.getDst());
            resultado.append("ZONA HORARIA BASE DE DATOS: ").append(aux.getDatabaseTimeZone());        
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

    public String listarVuelos() {
        ArrayList<Comparable> resultado = new ArrayList<>();
        for(Vuelo vuelo:vuelos){
            resultado.add(vuelo.getOrigen().toString()+ "-" + vuelo.getDestino().toString());
        }
        
        return resultado.toString();
    }
    
    public TGrafoDirigido cargarEstructuras(){
        crearAeropuertos("src/Files/aeropuertos.csv", true);
        crearVuelos("src/Files/vuelosTest.txt", true);
        crearConexiones();
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo( getAeropuertos(),getConexiones(), TGrafoDirigido.class);
        return gd;
    }
    
    public String informacionDeRecorridos(String Origen, String Destino,TGrafoDirigido grafo){
        TCaminos caminos = grafo.todosLosCaminos(Origen, Destino);
        StringBuilder resultado = new StringBuilder();
        for(TCamino camino:caminos.getCaminos()){
           Comparable etiquetaOrigen = camino.getOrigen().getEtiqueta();
           for(Comparable vertice:camino.getOtrosVertices()){
               Comparable etiquetaDestino = vertice;
               for(Conexiones conexion:conexiones){
                   if(conexion.getOrigen().equals(etiquetaOrigen) && conexion.getDestino().equals(etiquetaDestino)){
                       ArrayList<Vuelo> vuelos = conexion.getVuelos();
                       resultado.append("--------------------------------------------").append("\n");
                       resultado.append("VUELOS DESDE ").append(conexion.getOrigen()).append(" HASTA ").append(conexion.getDestino()).append("\n");
                       for(Vuelo vuelo:vuelos){
                           resultado.append("  COSTO: ").append(vuelo.getCosto());
                           resultado.append("  LLEGADA: ").append(vuelo.getLlegadaFechaHora());
                           resultado.append("  SALIDA: ").append(vuelo.getSalidaFechaHora());
                           resultado.append("\n");
                       }
               }
           }     
        }
    }
      return resultado.toString();
    }
    
}
