/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alasucu.grafo;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author DANIEL
 */
public class TGrafoNoDirigido extends TGrafoDirigido {    
    
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas){
        //Inserto arista y vertices, usando el constructor de TGrafoDirigido
        super(vertices, aristas);        
        //Inserto las aristas opuestas.
        for (TArista arista : aristas){           
            TArista sentidoOpuesto = new TArista(arista.getEtiquetaDestino(), 
                                 arista.getEtiquetaOrigen(), arista.getCosto());           
            this.aristas.insertarAlFinal(sentidoOpuesto);
            super.insertarArista(sentidoOpuesto);            
        }        
    }
    
    // este procedimiento de PRIM usa la lista de aristas expl�cita para
    // resolver. Por claridad y seguridad, se arman listas de v�rtices para
    // trabajar,
    // "verticesU" y "verticesV", de forma de hacerlo lo m�s parecido posible al
    // seudoc�digo abstracto.
    // al final devuelve un nuevo grafo no dirigido que es el �rbol abarcador de
    // costo m�nimo obtenido.
    public TGrafoNoDirigido Prim() {
        // devuelve un nuevo grafo, que es el Arbol Abarcador de Costo M�nimo
        int costoPrim = 0;
        LinkedList<TVertice> verticesV = new LinkedList<>();        
        LinkedList<TVertice> verticesU = new LinkedList<>();       
        TAristas AristasAAM = new TAristas();
        TArista tempArista;
       
        if (this.getVertices().isEmpty()) {
            return null;
        }
        verticesV.addAll(this.getVertices().values());
        TVertice inicio = verticesV.removeFirst();
        verticesU.add(inicio);       
        while (verticesV.isEmpty() != true) {
            // elegir una arista de costo minimo que vaya de U a V, agregarla a
            // la lista de aristas del AAM, quitar el v�rtice v de V y agregarlo
            // a U
            tempArista = this.getAristas().buscarMin(verticesU, verticesV);
            if (tempArista != null){                
                costoPrim += tempArista.getCosto();
                AristasAAM.insertarAlFinal(tempArista);
                
                Iterator<TVertice>  it = verticesV.iterator();
                while(it.hasNext()){
                    TVertice vertice = it.next();
                    if(vertice.getEtiqueta().compareTo(tempArista.getEtiquetaDestino())== 0){
                        verticesU.add(vertice);
                        it.remove();                    
                    }
                }                
            }else{
                System.out.println("ERROR: El GND no es conexo y no se puede"
                                               + " realizar algoritmo de Prim");
                return null;
            }
        }
	//A partir de la lista de vertices verticesU y las aristas en AristasAAM armar el grafo nuevoGrafo y retornarlo        
        System.out.println("costo AAM PRIM: " + costoPrim);
        TGrafoNoDirigido nuevoGrafo = new TGrafoNoDirigido(verticesU,AristasAAM.getAristas());
        return nuevoGrafo;
    }
    
    public TGrafoNoDirigido kruskal(){
        if(this.vertices.isEmpty()){
            return null;
        }
        int costoKruskal = 0;
        //Cargo cada componente como un ComponenteConexi individual
        ComponentesConexos componentes = new ComponentesConexos();
        for(TVertice vertice : vertices.values()){
            ComponenteConexo cc = new ComponenteConexo(vertice.getEtiqueta());
            componentes.getComponentes().add(cc);
        }
        
        //Creo una copia de las aristas del grafo y otro TAristas para guardar los resultados
        TAristas a = aristas;        
        TAristas aristasKruskal = new TAristas();
        
        //Mientras las aristas resultado, sean menor que el numero de vertices -1
        while(aristasKruskal.getAristas().size() < vertices.size()-1){
            TArista minima = a.buscarMinima();
            /*Compruebo si las etiquetas de los vertices origen y destino de la
              arista se encuentran en diferentes Componentes. Si es asi, origen 
              y destino referenciaran al mismo objeto.
            */
            ComponenteConexo origen = componentes.buscar(minima.getEtiquetaOrigen());
            ComponenteConexo destino = componentes.buscar(minima.getEtiquetaDestino());
            if(origen != destino){
                costoKruskal += minima.getCosto();
                aristasKruskal.getAristas().add(minima);
                a.getAristas().remove(minima);
                origen.mezclarComponentes(destino);
                componentes.getComponentes().remove(destino);         
                
            }else{
                a.getAristas().remove(minima);                
            }
            
        }
        System.out.println("costo AAM KRUSKAL: " + costoKruskal);
        TGrafoNoDirigido nuevoGrafo = new TGrafoNoDirigido(this.vertices.values(),aristasKruskal.getAristas());
        return nuevoGrafo;
    }
    
    public String bea(){
        String resultado = "";
        if (this.getVertices().isEmpty()){
            return null;
        }else{
            //Seteo visitado a false en cada v
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            for(TVertice vertice : this.getVertices().values()){
                if (!vertice.getVisitado()){
                    resultado += resultado + vertice.bea();
                }
            }
        }
        return resultado;
    }
    public String bea(Comparable etiquetaOrigen){
        for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
        TVertice unVertice = buscarVertice(etiquetaOrigen);
        if(unVertice != null){
            return unVertice.bea();
        }else{
            return "No existe un vertice con esa etiqueta";
        }
        
    }
    
    public Collection<Comparable> puntosArticulacion(){
        //Uso la collection para corroborar que todos los vértices sean puntosArticulacion y que esto suceda una única vez.
        Collection<Comparable> puntosArticulacion = new LinkedList<>();
        if(vertices == null){
            System.out.println("El grafo dirigido se encuentra vacío");
            return puntosArticulacion;
        }else{
            //Seteo visitado a false en cada vertice
            for(TVertice vertice : vertices.values()){
                vertice.setVisitado(false);            
            }
            //Realizo la búsqueda en profundidad
            for(TVertice vertice : vertices.values()){
                if(vertice.getVisitado() == false){
                    vertice.bpf(puntosArticulacion);
                }            
            }
            return puntosArticulacion;            
        }
        
        
    }
    
}
