package proyectofinal.domain;

public class NodoArbol{
    private Object elemento;
    private NodoArbol nodoIzq;
    private NodoArbol nodoDer;

    public NodoArbol(NodoArbol nodoIzq, NodoArbol nodoDer, Object elemento){
       this.elemento=elemento;
       this.nodoIzq= nodoIzq;
       this.nodoDer= nodoDer;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoArbol getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(NodoArbol nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    public NodoArbol getNodoDer() {
        return nodoDer;
    }

    public void setNodoDer(NodoArbol nodoDer) {
        this.nodoDer = nodoDer;
    }   


}