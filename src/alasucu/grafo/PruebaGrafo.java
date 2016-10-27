package alasucu.grafo;


import java.util.Collection;
import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
//        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src/UT5/TA1_TA2_TA3/aeropuertos_ciclo.txt","src/UT5/TA1_TA2_TA3/conexiones_ciclo.txt",
//                false, TGrafoDirigido.class);
//
//        Object[] etiquetasarray = gd.getEtiquetasOrdenado();
//
//        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
//        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
//        Double[][] mfloyd = gd.floyd();
//        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        
//        Double[][] warshall = gd.warshallDouble();
//        UtilGrafos.imprimirMatrizMejorado(warshall, gd.getVertices(), "Cierre Transitivo");
//        for (int i = 0; i < etiquetasarray.length; i++) {
//            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
//        }
//        System.out.println();
//        System.out.println("Centro del grafo: " + gd.centroDelGrafo());

//        Double[] dijkstra =  gd.dijkstra("Asuncion");
//        for (int i = 0; i < dijkstra.length; i++) {
//            System.out.println(dijkstra[i]);
//        }
//        
//        System.out.println();        
//        Comparable origen = "Asuncion";
//        Comparable destino = "Montevideo";
//        System.out.println("Camino más corto entre: " + origen + " y " + destino);
//        gd.obtenerCaminoFloyd(origen, destino);
        
//        System.out.println();
//        Collection <Comparable> visitados = gd.bpf();
//        for(Comparable ciudad : visitados){
//            System.out.println(ciudad);
//        } 
//        
//        System.out.println();
//        Collection <Comparable> visitados2 = gd.bpf("Montevideo");        
//        for(Comparable ciudad : visitados2){
//            System.out.println(ciudad);
//        } 
        
//        System.out.println();
//        TCaminos caminos = gd.todosLosCaminos(origen, destino);
//        caminos.imprimir();
//        
//        System.out.println();
//        System.out.println("El grafo contiene ciclos :" + gd.contieneCiclos());
//        
//        System.out.println();
//        TCaminos caminoCiclos = gd.caminoCiclos();
//        caminoCiclos.imprimir();
        
//        System.out.println();
//        LinkedList <TVertice> ordenTopologico = gd.ordenTopologico();
//        int contador = 0;
//        for(TVertice v : ordenTopologico){
//            if (contador < ordenTopologico.size()){
//                System.out.print(v.getEtiqueta() + " -> ");                
//            }else{
//                System.out.println(v.getEtiqueta());
//            }            
//        }

//*******************************************************************************************************************************
        
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo("src/UT5/TA1_TA2_TA3/verticesBEA.txt","src/UT5/TA1_TA2_TA3/aristasBEA.txt",
                false, TGrafoNoDirigido.class);
//        Double[][] matriz1 = UtilGrafos.obtenerMatrizCostos(gnd.getVertices());
//        UtilGrafos.imprimirMatrizMejorado(matriz1, gnd.getVertices(), "Matriz");       
//        
//        Double[][] mfloyd1 = gnd.floyd();
//        UtilGrafos.imprimirMatrizMejorado(mfloyd1, gnd.getVertices(), "Matriz luego de FLOYD");
//        
//        TGrafoNoDirigido prim = gnd.Prim();
//        Double[][] matrizPrim = UtilGrafos.obtenerMatrizCostos(prim.getVertices());
//        UtilGrafos.imprimirMatrizMejorado(matrizPrim, prim.getVertices(), "Matriz luego de PRIM");
//        for(TArista arista : prim.getAristas().getAristas()){
//            System.out.println(arista.getEtiquetaOrigen() +  " - " + arista.getEtiquetaDestino() +  " - " + arista.getCosto());
//            
//        }
        
//        System.out.println();
//        TGrafoNoDirigido kruskal = gnd.kruskal();
//        Double[][] matrizKruskal = UtilGrafos.obtenerMatrizCostos(kruskal.getVertices());
//        UtilGrafos.imprimirMatrizMejorado(matrizKruskal, kruskal.getVertices(), "Matriz luego de KRUSKAL");
//        for(TArista arista : kruskal.getAristas().getAristas()){
//            System.out.println(arista.getEtiquetaOrigen() +  " - " + arista.getEtiquetaDestino() +  " - " + arista.getCosto());
//            
//        }
        
        System.out.println();
        System.out.println(gnd.bea("d"));
        
//        System.out.println();
//        Collection <Comparable> visitados = gnd.bpf();
//        for(Comparable etiqueta : visitados){
//            System.out.print(etiqueta);
//        }
//        System.out.println();        
    }
}
