package proyectofinal.domain;

import java.util.Random;

import proyectofinal.Aplicacion;

public class Apuesta {
    private String codigo;
    private Equipo equipo;
    private double valorApuesta;
    private float cuota;
    private boolean ganado;
    private double gananciaPot;
    private EstadoApuesta estado;

    public Apuesta(String codigo, Equipo equipo, double valorApuesta, float cuota, double gananciaPot,
            EstadoApuesta estado) {
        this.codigo = codigo;
        this.equipo = equipo;
        this.valorApuesta = valorApuesta;
        this.cuota = cuota;
        this.ganado = false;
        this.gananciaPot = gananciaPot;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public double getValorApuesta() {
        return valorApuesta;
    }

    public void setValorApuesta(double valorApuesta) {
        this.valorApuesta = valorApuesta;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public boolean isGanado() {
        return ganado;
    }

    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    public double getGananciaPot() {
        return gananciaPot;
    }

    public void setGananciaPot(double gananciaPot) {
        this.gananciaPot = gananciaPot;
    }

    public EstadoApuesta getEstado() {
        return estado;
    }

    public void setEstado(EstadoApuesta estado) {
        this.estado = estado;
    }

    public void hacerApuesta(Usuario jugador) {
        boolean agregar = true;
        for (Apuesta apuesta : jugador.getHistorialApuestas()) {
            if (apuesta.getCodigo().equals(this.codigo)) {
                agregar = false;
            }
        }

        if (agregar) {
            jugador.getHistorialApuestas().add(this);
        }
    }

    public static String generarCodigo() {
        String codigo = "";
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            switch (random.nextInt(1)) {
                case 0:
                    codigo += (char) (48 + random.nextInt(9));// Se agrega un número (0-9) al código de la apuesta
                    break;

                case 1:
                    codigo += (char) (65 + random.nextInt(25));// Se agrega una letra mayúscula (A-Z) al código de la
                                                               // apuesta
                    break;
            }
        }

        return codigo;
    }

    public static float calcularCuota(Equipo equipo) {//Completar formula para calcular cuota
       
        Equipo rival = Aplicacion.torneo.obtenerRival(equipo);

        float probTotal = ((float)equipo.getPuntaje()) + ((float)rival.getPuntaje());
        float probEquipo = ((float)equipo.getPuntaje())/probTotal;

        float cuota = (1 / (probEquipo/(1-probEquipo))) + 1;
        return cuota;
        
       // float probabilidad = ((float) equipo.getPuntaje());
        //float cuota = probabilidad / (1 - probabilidad);
    }

    public double calcularGanancia() {
        return this.valorApuesta * this.cuota;
    }
}
