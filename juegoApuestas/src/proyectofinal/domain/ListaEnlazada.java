package proyectofinal.domain;

public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamLista = 0;

    public ListaEnlazada(Object valor){
        crearCabeza(valor);
    }

    public void agregarNodo(Object valor){
        NodoLista nodoAux = cabeza;
        NodoLista nuevo = new NodoLista(valor);
        if(cabeza == null){
            cabeza = nuevo;
        }else{
            for (int i = 1; i < tamLista; i++) {
                nodoAux = nodoAux.obtenerNodoPorEnlace();
            }
            nodoAux.enlazarSiguiente(nuevo);
        }
        tamLista ++;
    }

    public void crearCabeza(Object valor) {
        cabeza = new NodoLista(valor);
        tamLista ++;
    }

    public int getTamLista() {
        return tamLista;
    }

    public NodoLista getCabeza() {
        return cabeza;
    }

    @Override
    public String toString() {
        NodoLista nodoAux = cabeza;
        String mensaje = "";
        for (int i = 1; i <= tamLista; i++) {
            mensaje +=   nodoAux.retornarValor() + " " ;
            nodoAux = nodoAux.obtenerNodoPorEnlace();
        }
        return mensaje;
    }
}
