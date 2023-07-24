package proyectofinal.domain;

import java.util.ArrayList;

public class Usuario {

    private int idUsuario;

    private String nombre;
    private String documento;
    private String correo;
    private String contrasenia;
    private double saldo;
    private ArrayList<Apuesta> historialApuestas;

    public Usuario(int idUsuario, String nombre, String documento, String correo, String contrasenia, double saldo,
            ArrayList<Apuesta> historialApuestas) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.saldo = saldo;
        this.historialApuestas = historialApuestas;
    }

    public Usuario(String nombre, String documento, String correo, String contrasenia, double saldo,
            ArrayList<Apuesta> historialApuestas) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.saldo = saldo;
        this.historialApuestas = historialApuestas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Apuesta> getHistorialApuestas() {
        return historialApuestas;
    }

    public void setHistorialApuestas(ArrayList<Apuesta> historialApuestas) {
        this.historialApuestas = historialApuestas;
    }

    public void colocarApuesta(Apuesta apuesta) {
        this.historialApuestas.add(apuesta);
        this.saldo = this.saldo - apuesta.getValorApuesta();
    }

    public int buscarApuesta(Apuesta apuesta) {
        int pos = -1;
        for (int i = 0; i < this.historialApuestas.size(); i++) {
            if (this.historialApuestas.get(i).getCodigo().equals(apuesta.getCodigo())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public void modificarSaldo(Apuesta apuesta){
        if(apuesta.isGanado()){
            this.saldo += apuesta.calcularGanancia();
        }else{
            this.saldo -= apuesta.getValorApuesta();
        } 
    }
}
