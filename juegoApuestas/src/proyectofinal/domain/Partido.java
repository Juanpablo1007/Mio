package proyectofinal.domain;

import java.util.ArrayList;
import java.util.Random;

public class Partido {
    private int codigo;
    private Equipo equipo1;
    private Equipo equipo2;
    private int golesEq1;
    private int golesEq2;
    private ArrayList<Evento> eventos;

    public Partido(Equipo equipo1, Equipo equipo2, ArrayList<Evento> eventos) {
        this.codigo = generarCodigo();
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEq1 = 0;
        this.golesEq2 = 0;
        this.eventos = eventos;
    }

    public int generarCodigo(){
        String codigo = "";
        for (int i = 0; i < 6; i++) {
            codigo += (int)(Math.random() * 10d);
        }
        return Integer.parseInt(codigo);
    }

    public int getCodigo(){
        return this.codigo;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String toString() {
        return equipo1.getNombre() + " VS " + equipo2.getNombre();
    }

    public int getGolesEq1() {
        return golesEq1;
    }

    public int getGolesEq2() {
        return golesEq2;
    }

    public Equipo determinarGanador() {
        Random random = new Random();
        int puntaje1 = 0;
        int puntaje2 = 0;

        while (puntaje1 == puntaje2) {
            puntaje1 = (int) ((random.nextInt(3) + 1) * this.equipo1.getPuntaje());
            puntaje2 = (int) ((random.nextInt(3) + 1) * this.equipo2.getPuntaje());
        }

        int goles = 0;

        if (puntaje1 > puntaje2) {
            goles = random.nextInt(5) + 1;
            this.equipo1.aumentarGoles(goles);
            this.golesEq1 = goles;

            goles = random.nextInt(goles);
            this.equipo2.aumentarGoles(goles);
            this.golesEq2 = goles;

            return this.equipo1;
        } else {
            goles = random.nextInt(5) + 1;
            this.equipo2.aumentarGoles(goles);
            this.golesEq2 = goles;

            goles = random.nextInt(goles);
            this.equipo1.aumentarGoles(goles);
            this.golesEq1 = goles;

            return this.equipo2;
        }
    }

    public void generarEventos() {
        Random random = new Random();
        for (int i = 0; i < this.golesEq1; i++) {
            int minuto = random.nextInt(90) + 1;
            Evento evento = new Evento(this.equipo1, minuto, TipoEvento.GOL);
            this.eventos.add(evento);
        }

        for (int i = 0; i < this.golesEq2; i++) {
            int minuto = random.nextInt(90) + 1;
            Evento evento = new Evento(this.equipo2, minuto, TipoEvento.GOL);
            this.eventos.add(evento);
        }

        for (int i = 0; i < 15; i++) {
            int aux = random.nextInt(4);
            int minuto = random.nextInt(90) + 1;
            Evento evento = null;
            Equipo equipo = random.nextInt(2) == 0 ? this.equipo1 : this.equipo2;
            evento = new Evento(equipo, minuto, null);

            switch (aux) {
                case 0:
                    evento.setTipoEvento(TipoEvento.FALTA);
                    break;
                case 1:
                    evento.setTipoEvento(TipoEvento.TARJETA);
                    break;
                case 2:
                    evento.setTipoEvento(TipoEvento.ATAJADA);
                    break;
                case 3:
                    evento.setTipoEvento(TipoEvento.TIRO_ESQUINA);
                    break;
            }
            this.eventos.add(evento);
        }
    }
}
