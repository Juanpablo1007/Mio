package proyectofinal.domain;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.Random;

import proyectofinal.bd.EquipoDAO;
import proyectofinal.bd.UsuarioDAO;

public class Torneo {
    public static final String nombre = "Champions League";
    public static Arbol arbol;
    public int indexFase = 1;
    public static ArrayList<Usuario> usuarios;
    public Hashtable<Integer, ListaEnlazada> equipos;
    public ArrayList<Partido> octavos;
    public ArrayList<Partido> cuartos;
    public ArrayList<Partido> semis;
    public ArrayList<Partido> finales;

    public double saldoIni;

    public Equipo ganador;

    public static ArrayList<Partido> fase;

    private ArrayList<Integer> llaves;

    private UsuarioDAO usuarioDAO;
    private EquipoDAO equipoDAO;

    private static final int tamTablaEquipos = 23;

    public Torneo() {
        this.usuarioDAO = new UsuarioDAO();
        this.equipoDAO = new EquipoDAO();
        this.equipos = new Hashtable<>(tamTablaEquipos);
        this.octavos = new ArrayList<>();
        this.cuartos = new ArrayList<>();
        this.semis = new ArrayList<>();
        this.finales = new ArrayList<>();
        this.llaves = new ArrayList<>();
    }

    public void cargarUsuarios() {
        Torneo.usuarios = usuarioDAO.select();
    }

    public void cargarEquipos() {
        ListaEnlazada lista;
        ArrayList<Equipo> equiposAux = equipoDAO.select();

        for (Equipo equipo : equiposAux) {
            long llave = Long.parseLong(equipo.getCodigo());
            int pos = funcionHash(llave);// Heurística = plegamiento
            if (equipos.get(pos) != null) {// Solucionar colisiones
                equipos.get(pos).agregarNodo(equipo);
            } else {
                lista = new ListaEnlazada(equipo);
                equipos.put(pos, lista);
                llaves.add(pos);
            }
        }
        System.out.println(equipos);

    }

    public static int funcionHash(long llave) {// 981732 xSuma=732 i=2 i+1%3=0 -> false 548 347 627 3
                                               // plegamiento=981+732=1713/10=171%23=10
        int x = 3;
        String llaveCadena = String.valueOf(llave);
        int plegamiento = 0;
        String xSuma = "";
        for (int i = 0; i < llaveCadena.length(); i++) {
            xSuma += llaveCadena.charAt(i);
            if ((i + 1) % x == 0) {
                plegamiento += Integer.parseInt(xSuma);
                xSuma = "";
            }
        }
        if (String.valueOf(plegamiento).length() > String.valueOf(tamTablaEquipos).length()) {
            plegamiento /= 10;
        }
        if (plegamiento >= tamTablaEquipos) {
            plegamiento %= tamTablaEquipos;
        }
        return plegamiento;
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario) == null) {
            Torneo.usuarios.add(usuario);
            usuarioDAO.insert(usuario);
            return true;
        }
        return false;
    }

    public Usuario buscarUsuario(Usuario usuario) {
        Usuario usuarioAux = null;
        for (int i = 0; i < Torneo.usuarios.size(); i++) {
            if (Torneo.usuarios.get(i).getDocumento().equals(usuario.getDocumento())) {
                usuarioAux = usuarios.get(i);
                break;
            }
        }
        return usuarioAux;
    }

    public Equipo buscarEquipo(Equipo equipo) {
        long llave = Long.parseLong(equipo.getCodigo());
        int pos = funcionHash(llave);
        NodoLista nodoAux = equipos.get(pos).getCabeza();
        for (int i = 1; i <= equipos.get(pos).getTamLista(); i++) {
            if (((Equipo) nodoAux.retornarValor()).getCodigo().equals(equipo.getCodigo())) {
                return (Equipo) nodoAux.retornarValor();
            }
            nodoAux = nodoAux.obtenerNodoPorEnlace();
        }
        return null;
    }

    public void generarOctavos() {
        System.out.println("Generando octavos...");
        Random random = new Random();
        int buscar;
        String codigos = "";
        Partido partido = new Partido(null, null, new ArrayList<>());
        NodoLista nodoAux;
        int cont = 0;

        while (cont < 8) {
            if (partido.getEquipo1() == null || partido.getEquipo2() == null) {
                buscar = llaves.get(random.nextInt(llaves.size()));
                nodoAux = equipos.get(buscar).getCabeza();
                for (int j = 1; j <= equipos.get(buscar).getTamLista(); j++) {
                    if (!codigos.contains(((Equipo) nodoAux.retornarValor()).getCodigo())) {
                        if (partido.getEquipo1() == null) {
                            partido.setEquipo1((Equipo) nodoAux.retornarValor());
                            codigos += partido.getEquipo1().getCodigo() + "  ";
                        } else if (partido.getEquipo2() == null) {
                            partido.setEquipo2((Equipo) nodoAux.retornarValor());
                            codigos += partido.getEquipo2().getCodigo() + "  ";
                        }
                    }
                    nodoAux = nodoAux.obtenerNodoPorEnlace();
                }
            } else {
                obtenerFase().add(partido);
                partido = new Partido(null, null, new ArrayList<>());
                cont++;
            }
        }
        System.out.println("Octavos generados...");
    }

    public Equipo obtenerRival(Equipo equipo) {
        for (Partido partido : obtenerFase()) {
            if (partido.getEquipo1().getCodigo().equals(equipo.getCodigo())) {
                return partido.getEquipo2();
            } else if (partido.getEquipo2().getCodigo().equals(equipo.getCodigo())) {
                return partido.getEquipo1();
            }
        }
        return null;
    }

    public ArrayList<Partido> obtenerFase() {
        switch (this.indexFase) {
            case 1:
                return this.octavos;

            case 2:
                return this.cuartos;

            case 3:
                return this.semis;

            case 4:
                return this.finales;
        }
        return null;
    }

    public Partido obtenerPartido(Equipo equipo, int fase) {
        ArrayList<Partido> lista = null;
        switch (fase) {
            case 1:
                lista = this.finales;
                break;

            case 2:
                lista = this.semis;
                break;

            case 3:
                lista = this.cuartos;
                break;

            case 4:
                lista = this.octavos;
                break;
        }

        for (Partido partido : lista) {
            if (partido.getEquipo1().getCodigo().equals(equipo.getCodigo())
                    || partido.getEquipo2().getCodigo().equals(equipo.getCodigo())) {
                return partido;
            }
        }

        return null;
    }
}
