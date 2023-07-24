package proyectofinal.domain;

import java.io.Serializable;

public class JugadorEquipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int dorsal;
    private String nacionalidad;
    private int goles;
    private Posicion posicion;

    public JugadorEquipo(String nombre, int dorsal, String nacionalidad, int goles, Posicion posicion) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public void aumentarGoles(){
        this.goles += 1;
    }
}