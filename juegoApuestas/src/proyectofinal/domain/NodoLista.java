package proyectofinal.domain;

public class NodoLista {
    private NodoLista siguiente;
    private Object valorNodo;

    public NodoLista(Object valor) {
        this.valorNodo = valor;

    }

    public NodoLista() {

        this.valorNodo = "toy vacio";

    }

    public void enlazarSiguiente(NodoLista nodo) {

        siguiente = nodo;

    }

    public NodoLista obtenerNodoPorEnlace() {

        if (siguiente == null) {

            NodoLista nulo = new NodoLista();
            return nulo;

        } else {
            return siguiente;
        }

    }

    public Object retornarValor() {
        return valorNodo;
    }
}
