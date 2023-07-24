package proyectofinal.domain;

import java.util.ArrayList;

import proyectofinal.Aplicacion;

public class Arbol {
    private NodoArbol raiz;
    private int indexFinal;
    private int indexSemis;
    private int indexCuartos;
    private int indexOctavos;

    public Arbol(Object raiz) {
        this.raiz = new NodoArbol(null, null, raiz);
        this.indexFinal = 0;
        this.indexSemis = 0;
        this.indexCuartos = 0;
        this.indexOctavos = 0;

    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public void crearArbol(int fase, NodoArbol nodoAux) {
        if (fase == 5) {
            System.out.println("Hoja creada");
        } else {
            int index = -1;
            ArrayList<Partido> partidoFase = null;
            switch (fase) {
                case 1:
                    index = this.indexFinal;
                    partidoFase = Aplicacion.torneo.finales;
                    break;

                case 2:
                    index = this.indexSemis;
                    partidoFase = Aplicacion.torneo.semis;
                    break;

                case 3:
                    index = this.indexCuartos;
                    partidoFase = Aplicacion.torneo.cuartos;
                    break;

                case 4:
                    index = this.indexOctavos;
                    partidoFase = Aplicacion.torneo.octavos;
                    break;
            }
            Partido partido = null;
            if (index < partidoFase.size()) {
                partido = partidoFase.get(index);
            }

            if (nodoAux.getNodoIzq() == null) {
                nodoAux.setNodoIzq(new NodoArbol(null, null, partido.getEquipo1()));
                crearArbol(fase, nodoAux);
            } else if (nodoAux.getNodoDer() == null) {
                nodoAux.setNodoDer(new NodoArbol(null, null, partido.getEquipo2()));
                switch (fase) {
                    case 1:
                        this.indexFinal += 1;
                        break;

                    case 2:
                        this.indexSemis += 1;
                        break;

                    case 3:
                        this.indexCuartos += 1;
                        break;

                    case 4:
                        this.indexOctavos += 1;
                        break;
                }
                crearArbol(fase, nodoAux);
            } else {
                crearArbol(fase + 1, nodoAux.getNodoIzq());
                crearArbol(fase + 1, nodoAux.getNodoDer());
            }
        }
    }

    public void mostrarArbol(String dir, NodoArbol nodoAux) {
        if (nodoAux == null) {
            System.out.println("---");
        } else {
            System.out.println(nodoAux.getElemento() + dir);
            mostrarArbol(" - IZQ ", nodoAux.getNodoIzq());
            mostrarArbol(" - DER ", nodoAux.getNodoDer());
        }
    }

    public Equipo buscarEquipo(int contFase, int fase, Equipo equipo, NodoArbol nodo){
        if(fase == contFase && ((Equipo)nodo.getElemento()).getCodigo().equals(equipo.getCodigo())){
            System.out.println("Fase = " + contFase + ", Equipo = " + ((Equipo)nodo.getElemento()));
            return ((Equipo)nodo.getElemento());
        }else{
            buscarEquipo(contFase + 1, fase, equipo, nodo.getNodoDer());
            buscarEquipo(contFase + 1, fase, equipo, nodo.getNodoIzq());
            return null;
        }
    }

    public Partido buscPartido(Equipo equipo, int fase){
        Equipo equipoAux = this.buscarEquipo(0, indexCuartos, equipo, this.raiz);
        return Aplicacion.torneo.obtenerPartido(equipoAux, fase);
    }
}
