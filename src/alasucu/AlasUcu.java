/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

/**
 *
 * @author Rodrigo
 */
public class AlasUcu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        AlasUcuAdapter adapter = new AlasUcuAdapter();
        
        adapter.crearAeropuertos("src/Files/aeropuertos.csv", true);
        //System.out.println(adapter.listarAeropuertos());

        adapter.crearVuelos("src/Files/vuelos.csv", true);
        //System.out.println(adapter.listarVuelos());

        adapter.crearConexiones();
        System.out.println(adapter.listarConexiones());
        
    }
    
}
