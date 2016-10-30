/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu;

import alasucu.grafo.TGrafoDirigido;
import alasucu.grafo.UtilGrafos;

/**
 * @author Rodrigo Castro
 */
public class AlasUcu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        AlasUcuAdapter adapter = new AlasUcuAdapter();
        
        //adapter.crearAeropuertos("src/Files/aeropuertos.csv", true);
        //System.out.println(adapter.listarAeropuertos());

        //adapter.crearVuelos("src/Files/vuelosTest.txt", true);
        //System.out.println(adapter.listarVuelos());

        //adapter.crearConexiones();
        //System.out.println(adapter.listarConexiones());
        
            TGrafoDirigido gd = adapter.cargarEstructuras();
            //System.out.println(gd.bpf());
            gd.todosLosCaminos("YAM", "YQT").imprimir();
    }
    
}
