package alasucu.grafo;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Rodrigo Castro
 */
public class TVertice implements IVertice{

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private Object datos;
    private int numeracionBpf;
    private int numeracionTopologica;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }   
    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }
    @Override
    public boolean getVisitado() {
        return this.visitado;
    }
    @Override
    public Object getDatos() {
        return datos; 
    }
    public int getNumeracionBpf() {
        return numeracionBpf;
    }
    public int getNumeracionTopologica() {
        return numeracionTopologica;
    }    
    
    @Override
    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public void setNumeracionBpf(int numeracionBpf) {
        this.numeracionBpf = numeracionBpf;
    }
    
    public void setNumeracionTopologica(int numeracionTopologica) {
        this.numeracionTopologica = numeracionTopologica;
    }

    public void sumarNumeracionTopologica() {
        this.numeracionTopologica += 1;
    }   

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        numeracionBpf = -1;
        numeracionTopologica = 0;
    }   

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }


    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }    

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }
    
    public Collection<Comparable> bpf (Collection<Comparable> visitados){
        //Seteo el vértice como visitado
        this.setVisitado(true);
        visitados.add(this.etiqueta);
        for(TAdyacencia adyacente : this.adyacentes){
            if(adyacente.getDestino().getVisitado() == false){
                adyacente.getDestino().bpf(visitados);
            }
        }        
        return visitados;
    }    
    
    public TCaminos todosLosCaminos (Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLCaminos){
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()){
            TVertice destino = adyacencia.getDestino();
            if(!destino.getVisitado()){
                if(destino.getEtiqueta().compareTo(etVertDest) == 0){
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLCaminos.getCaminos().add(copia);
                }else{                    
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    adyacencia.getDestino().todosLosCaminos(etVertDest, copia, todosLCaminos);
                                        
                }
            }
        }
        this.setVisitado(false);
        return todosLCaminos;
    }
    
    public boolean contieneCiclos(int contador){
        //Seteo el vértice como visitado
        this.setVisitado(true);
        this.setNumeracionBpf(contador);
        contador ++;        
        for(TAdyacencia adyacente : this.adyacentes){
            if(adyacente.getDestino().getVisitado() == false){
                return adyacente.getDestino().contieneCiclos(contador);
            }else{
                if (this.numeracionBpf > adyacente.getDestino().getNumeracionBpf()){
                    return true;
                }
            }
        }
        this.setVisitado(false);
        this.setNumeracionBpf(-1);
        return false;        
    }
    
    public TCaminos caminoCiclos(int contador, TVertice verticeOrigen, TCamino caminoPrevio, TCaminos todosLCaminos){
        //Seteo el vértice como visitado
        this.setVisitado(true);
        this.setNumeracionBpf(contador);
        contador ++;        
        for(TAdyacencia adyacente : this.adyacentes){
            if(adyacente.getDestino().getVisitado() == false){
                TCamino copia = caminoPrevio.copiar();
                copia.agregarAdyacencia(adyacente);
                adyacente.getDestino().caminoCiclos(contador, verticeOrigen, copia, todosLCaminos);
            }else{
                if (this.numeracionBpf > adyacente.getDestino().getNumeracionBpf() && adyacente.getDestino().getEtiqueta().compareTo(verticeOrigen.getEtiqueta())==0){
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacente);
                    todosLCaminos.getCaminos().add(copia);
                }
            }
        }
        this.setVisitado(false);
        this.setNumeracionBpf(-1);
        return todosLCaminos;        
    }
    public String bea ( ){
        String resultado= "";
        Queue cola = new LinkedList();
        this.visitado=true;
        resultado = resultado + this.etiqueta.toString();
        cola.add(this);
        while(!cola.isEmpty()){
            TVertice aProcesar = (TVertice)cola.remove();
            for(TAdyacencia a: aProcesar.adyacentes){
                TVertice v = a.getDestino();
                if (!v.visitado){
                    v.visitado= true;
                    cola.add(v);
                    resultado = resultado + v.etiqueta.toString();
                }
            }
        }
        return resultado; 
    }
}
