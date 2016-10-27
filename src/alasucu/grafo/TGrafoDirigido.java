package alasucu.grafo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import sun.misc.Queue;

public class TGrafoDirigido implements IGrafoDirigido {

    protected Map<Comparable, TVertice> vertices; // vertices del grafo.-
    protected TAristas aristas;

    public TAristas getAristas() {
        return aristas;
    }    
    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }
    

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        this.aristas = new TAristas();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
            this.aristas.insertarAlFinal(arista);
        }
        
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino.
     * En caso de no existir la adyacencia, retorna falso. En caso de que las
 vectorVertices sean inv�lidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un v en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     */
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las
 vectorVertices pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un v dentro del
 grafo.-

 La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el v con la etiqueta indicada, false en caso
 contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un v dentro del grafo.-

 La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El v encontrado. En caso de no existir, retorna nulo.
     */
    public TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
 costo), dado su v origen y destino.- Para que la arista sea
 valida, se deben cumplir los siguientes casos: 1) Las vectorVertices pasadas
 por parametros son v�lidas.- 2) Los vertices (origen y destino) existen
 dentro del grafo.- 3) No es posible ingresar una arista ya existente
 (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
 costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen()!= null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }
 
    /**
     * Metodo encargado de insertar un v en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el v, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }
    
    @Override
    public boolean insertarVertice(TVertice vertice) {
     Comparable unaEtiqueta = vertice.getEtiqueta();
     if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }      
    
    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }
    
    public boolean contineAristasNegativas(){
        for(TArista arista : aristas.getAristas()){
            if (arista.getCosto()< 0){
                return true;
            }
        }
        return false;
    }
    
    public Double[] dijkstra(Comparable verticeInicial){
        if (contineAristasNegativas()){
            System.out.println("El grafo contiene aristas negativas");
            return null;
        }else if(!vertices.containsKey(verticeInicial)){
            System.out.println("El vértice de inicio no se encuentra en el grafo");
            return null;
        }else{
            Double[] distMin = new Double[vertices.size()];            
            Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(vertices);
            
            LinkedList<TVertice> vertVmenosS = new LinkedList<>();
            vertVmenosS.addAll(vertices.values());
            
            Object[] listaEtiquetas = vertices.keySet().toArray();
            int indiceInicial = 0;
            for (int i = 0; i < listaEtiquetas.length; i++) {
                if (verticeInicial.compareTo((Comparable)listaEtiquetas[i]) == 0){
                    indiceInicial = i;
                }                
            }
            for (int i = 0; i < matrizCostos.length; i++) {
                if (i != indiceInicial) {
                    distMin[i] = matrizCostos[indiceInicial][i];
                } else {
                    distMin[i] = -1.0;
                }                
            }
            vertVmenosS.remove(indiceInicial);
            
            while (vertVmenosS.isEmpty() != true) {
                double min = Double.MAX_VALUE;
                int indiceV = indiceInicial;
                for (int i = 0; i < matrizCostos.length; i++) {
                    Comparable etiquetaBuscada = (Comparable) vertices.keySet().toArray()[i];
                    if (i != indiceInicial && vertVmenosS.contains(vertices.get(etiquetaBuscada))) {
                        if (min >= distMin[i]) {
                            min = distMin[i];
                            indiceV = i;
                        }
                    }                    
                }
                Comparable etiquetaVertice = (Comparable) vertices.keySet().toArray()[indiceV];
                for (int i = 0; i < matrizCostos.length; i++) {
                    if (i != indiceInicial && i != indiceV) {
                        if (distMin[i] > distMin[indiceV] + matrizCostos[indiceV][i]) {
                            distMin[i] = distMin[indiceV] + matrizCostos[indiceV][i];
                        }
                    }
                }
                vertVmenosS.remove(vertices.get(etiquetaVertice));
            }
            return distMin;
        }
        
    }

    @Override
    public Double[][] floyd() {
        if (contineAristasNegativas()){
            System.out.println("El grafo contiene aristas negativas");
            return null;
        }else{
            Double[][] c = UtilGrafos.obtenerMatrizCostos(vertices);
            for(int i=0; i<c.length; i++){
                c[i][i] = 0.0;
            }
            for(int k=0; k<c.length; k++){
                for(int i=0; i<c.length; i++){
                    for(int j=0; j<c.length; j++){
                        if ((c[i][k] + c[k][j]) < c[i][j]) {
                            c[i][j] = c[i][k] + c[k][j];
                        }
                    }
                }
            }
            return c;            
        }
        
    }
    public TVertice[][] precedentesFloyd() {
        if (contineAristasNegativas()){
            System.out.println("El grafo contiene aristas negativas");
            return null;
        }else{
            Double[][] c = UtilGrafos.obtenerMatrizCostos(vertices);        
            TVertice[] vectorVertices = vertices.values().toArray(new TVertice[vertices.values().size()]);
            TVertice[][] precedentes = new TVertice[c.length][c.length];
            for(int i=0; i<c.length; i++){
                c[i][i] = 0.0;
                for(int j=0; j<c.length; j++){
                    precedentes[i][j]= null;
                }
            }        
            for(int k=0; k<c.length; k++){
                for(int i=0; i<c.length; i++){
                    for(int j=0; j<c.length; j++){
                        if ((c[i][k] + c[k][j]) < c[i][j]) {
                            c[i][j] = c[i][k] + c[k][j];
                            precedentes [i][j] = vectorVertices[k];
                        }
                    }
                }
            }
            return precedentes;            
        }        
    }
    
    public void obtenerCaminoFloyd (Comparable origen, Comparable destino){
        TVertice[][] matrizPrecedentes = precedentesFloyd();
        TVertice[] vectorVertices = vertices.values().toArray(new TVertice[vertices.values().size()]);
        int indiceOrigen = -1;
        int indiceDestino = -1;
        for (int i = 0; i < vectorVertices.length; i++) {
            if(origen.compareTo(vectorVertices[i].getEtiqueta())== 0){
                indiceOrigen = i;
            }
            if(destino.compareTo(vectorVertices[i].getEtiqueta())== 0){
                indiceDestino = i;
            }
            if(indiceOrigen != -1 && indiceDestino != -1){
                break;                
            }           
        }        
        if(indiceOrigen != -1 && indiceDestino != -1){
            System.out.print(origen + " - ");
            obtenerCaminoFloyd(indiceOrigen, indiceDestino, vectorVertices, matrizPrecedentes);
            System.out.println(destino);
        }else{
            System.out.println("Uno o ambos vértices no pertenecen al grafo");
        }  
        
    }
    
    private void obtenerCaminoFloyd(int indiceOrigen, int indiceDestino,TVertice[] vectorVertices, TVertice[][] matrizPrecedentes){
        TVertice k = matrizPrecedentes[indiceOrigen][indiceDestino];
        if(k == null){
            return;            
        }
        int indiceK = -1;
        for (int i = 0; i < vectorVertices.length; i++) {
            if(k.getEtiqueta().compareTo(vectorVertices[i].getEtiqueta())== 0){
                indiceK = i;
                break;
            }                   
        }
        obtenerCaminoFloyd(indiceOrigen, indiceK, vectorVertices, matrizPrecedentes);
        System.out.print(k.getEtiqueta() + " - ");
        obtenerCaminoFloyd(indiceK, indiceDestino, vectorVertices, matrizPrecedentes);
        
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        //Calculamos la matriz de Floyd
        Double[][] c = this.floyd();       
        int columnaVertice = 0;
        int contador = 0;
        for(Comparable key: vertices.keySet()){
            if(key.equals(etiquetaVertice)){
                columnaVertice = contador;
                break;
            }            
            contador += 1;            
        }        
        double excentricidad = 0;
        for(int i=0; i<c.length; i++){
            if(c[i][columnaVertice] > excentricidad){
                excentricidad = c[i][columnaVertice];
            }
        }
        return excentricidad;
    }
    
    @Override
    public Comparable centroDelGrafo() {        
        Comparable[] etiquetas = vertices.keySet().toArray(new Comparable[vertices.keySet().size()]);
        Double[][] c = this.floyd();
        double centro = Double.MAX_VALUE;
        int contadorColumna = 0;
        int columnaResultado = 0;
        for(int i=0; i<c.length; i++){
            double excentricidad = 0;
            for(int j=0; j<c.length; j++){
                if(c[j][i] > excentricidad){
                    excentricidad = c[j][i];
                }
            }
            if (excentricidad < centro){
                centro = excentricidad;
                columnaResultado = contadorColumna;
            }
            contadorColumna ++;            
        }        
       return etiquetas [columnaResultado];
    }

    @Override
    public boolean[][] warshall() {
        
        if (contineAristasNegativas()){
            System.out.println("El grafo contiene aristas negativas");
            return null;
        }else{
            Double[][] costos = UtilGrafos.obtenerMatrizCostos(vertices); 
            boolean[][] w = new boolean[vertices.size()][vertices.size()];
            for(int i=0; i<vertices.size(); i++){
                for(int j=0; j<vertices.size(); j++){
                    if (costos[i][j] == Double.MAX_VALUE || costos[i][j] <= 0){
                        w[i][j] = false;                       
                    }else{
                        w[i][j] = true;
                    }                    
                }
            }                
            for(int k=0; k<w.length; k++){
                for(int i=0; i<w.length; i++){
                    for(int j=0; j<w.length; j++){
                        if ( i == j){
                            w[i][j] = false;
                        }else if (w[i][j] == false){
                            w[i][j] = w[i][k] && w[k][j];
                        }                      
                    }
                }
            }
            return w;          
        }        
    }
    public Double[][] warshallDouble() {        
        if (contineAristasNegativas()){
            System.out.println("El grafo contiene aristas negativas");
            return null;
        }else{
            boolean[][] warshall = warshall();
            Double[][] warshallD = new Double[warshall.length][warshall.length];            
            for(int i=0; i<vertices.size(); i++){
                for(int j=0; j<vertices.size(); j++){
                    if (warshall[i][j] == true){
                        warshallD[i][j]= 1d;                       
                    }else{
                        warshallD[i][j]= 0d; 
                    }                    
                }
            }          
            return warshallD;          
        }        
    }
    
    public Collection<Comparable> bpf (){
        //Uso la collection para corroborar que todos los vértices sean visitados y que esto suceda una única vez.
        Collection<Comparable> visitados = new LinkedList<>();
        if(vertices == null){
            System.out.println("El grafo dirigido se encuentra vacío");
            return visitados;
        }else{
            //Seteo visitado a false en cada vertice
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            //Realizo la búsqueda en profundidad
            for(TVertice vertice : vertices.values()){
                if(vertice.getVisitado() == false){
                    vertice.bpf(visitados);
                }            
            }
            return visitados;            
        }        
    }
    
    public Collection<Comparable> bpf (Comparable etiqueta){
        //Uso la collection para corroborar que todos los vértices sean visitados y que esto suceda una única vez.
        Collection<Comparable> visitados = new LinkedList<>();
        if(vertices == null){
            System.out.println("El grafo dirigido se encuentra vacío");
            return visitados;
        }else if( buscarVertice(etiqueta) == null ){
            System.out.println("El vértice " + etiqueta + " no se encentra en el grafo");
            return visitados;
        }else{
            //Seteo visitado a false en cada vertice
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            TVertice inicio = buscarVertice(etiqueta);
            inicio.bpf(visitados);
            //Realizo la búsqueda en profundidad
            for(TVertice vertice : vertices.values()){
                if(vertice.getVisitado() == false){
                    vertice.bpf(visitados);
                }            
            }
            return visitados;            
        }        
    }  
    
    public TCaminos todosLosCaminos (Comparable etiquetaOrigen, Comparable etiquetaDestino){
        //Seteo visitado a false en cada v
        for(TVertice vertice : vertices.values()){
            vertice.setVisitado(false);            
        }
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v != null){
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }
    
    //Este métododo es una especialización de bpf y permite saber si hay ciclos
    public boolean contieneCiclos (){
        boolean resultado = false;
        if(vertices == null){
            System.out.println("El grafo dirigido se encuentra vacío");
            return resultado;
        }else{
            //Seteo visitado a false en cada v
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            //Realizo la búsqueda en profundidad, pero esta vez inicio en cada v sin tener en cuenta visitas anteriores
            for(TVertice vertice : vertices.values()){                
                int i = 0;
                resultado = vertice.contieneCiclos(i);
                if (resultado == true){
                    return resultado;
                }
            }            
        }
        return resultado;
    }    
    
    public TCaminos caminoCiclos (){
        TCaminos todosLosCaminos = new TCaminos();
        if(vertices == null){
            System.out.println("El grafo dirigido se encuentra vacío");
            return null;
        }else{
            //Seteo visitado a false en cada v
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            //Realizo la búsqueda en profundidad, pero esta vez inicio en cada v sin tener en cuenta visitas anteriores
            for(TVertice vertice : vertices.values()){                 
                TCamino caminoPrevio = new TCamino(vertice);
                int i = 0;
                vertice.caminoCiclos(i, vertice, caminoPrevio, todosLosCaminos);
            }            
        }
        return todosLosCaminos;
    }

    public LinkedList<TVertice> ordenTopologico (){
        if(vertices == null || vertices.isEmpty()== true){
            System.out.println("El grafo dirigido se encuentra vacío");
            return null;
        }else if( this.contieneCiclos() == true){
            System.out.println("El grafo dirigido contiene ciclos y no se puede "
                                        + "realizar una ordenación topológica");
            return null;
        }else{
            Collection <TVertice> vertGrafo = new LinkedList<>();
            vertGrafo.addAll(vertices.values());
            LinkedList<TVertice> resultado = new LinkedList<>();            
            return ordenTopologico(vertGrafo, resultado);
            
        }
    }
    private LinkedList<TVertice> ordenTopologico(Collection <TVertice> vertGrafo, LinkedList<TVertice> resultado){        
        while(vertGrafo.isEmpty()!= true){
            //Se calculan la cantidad de aristas que llegan a cada vector
            for(TVertice v : vertGrafo){
                for(TAdyacencia adyacencia : v.getAdyacentes()){
                    adyacencia.getDestino().sumarNumeracionTopologica();                
                }
            }
            Iterator<TVertice>  it = vertGrafo.iterator();
            while(it.hasNext()){
                TVertice vertice = it.next();
                if(vertice.getNumeracionTopologica() == 0){
                    resultado.add(vertice);
                    it.remove();                    
                }else{
                    vertice.setNumeracionTopologica(0);
                }
            }            
        }
        return resultado;
    }
    
    
}
