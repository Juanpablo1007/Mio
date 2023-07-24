package proyectofinal.domain;

import java.io.Serializable;
import java.util.Random;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String codigo;
    private JugadorEquipo[] jugadores;
    private int puntaje;
    private String escudo;
    private int goles;

    public Equipo(int id, String nombre, String codigo, JugadorEquipo[] jugadores, int puntaje, String escudo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.jugadores = jugadores;
        this.puntaje = puntaje;
        this.goles = 0;
        this.escudo = escudo;
    }

    public Equipo(String nombre, String codigo, JugadorEquipo[] jugadores, int puntaje, String escudo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.jugadores = jugadores;
        this.puntaje = puntaje;
        this.goles = 0;
        this.escudo = escudo;
    }

    public Equipo() {
        this(0, "", "", null, 0, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public JugadorEquipo[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(JugadorEquipo[] jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public void aumentarGoles(int goles) {
        this.goles += goles;
        this.asignarGoles(goles);
    }

    public void asignarGoles(int goles) {
        Random random = new Random();
        for (int i = 0; i < goles; i++) {
            int pos = random.nextInt(jugadores.length);
            if (jugadores[pos].getPosicion().equals(Posicion.DELANTERO)
                    || jugadores[pos].getPosicion().equals(Posicion.MEDIOCAMPISTA)) {
                jugadores[pos].aumentarGoles();
            } else {
                i--;
            }
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
