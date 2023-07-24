package proyectofinal.domain;

public class Evento {

    private Equipo equipo;
    private int minuto;
    private TipoEvento tipoEvento;

    public Evento(Equipo equipo, int minuto, TipoEvento tipoEvento) {
        this.equipo = equipo;
        this.minuto = minuto;
        this.tipoEvento = tipoEvento;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
