/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.discretas;

/**
 *
 * @author USUARIO
 */
public class Universal {
    double conjuntos[];
    String tipo;
    int numeroConjunto;
    int tamaño;

    public Universal(double [] conjuntos, String tipo, int numeroConjunto,int tamaño) {
        this.conjuntos = conjuntos;
        this.tipo = tipo;
        this.numeroConjunto = numeroConjunto;
        this.tamaño = tamaño;
    }

    public String getTipo() {
        return tipo;
    }
    

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

  

    public int getNumeroConjunto() {
        return numeroConjunto;
    }

    public void setNumeroConjunto(int numeroConjunto) {
        this.numeroConjunto = numeroConjunto;
    }

    public double[] getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(double[] conjuntos) {
        this.conjuntos = conjuntos;
    }
   

   

   

    

    public void setTipo(String conjunto) {
        this.tipo = conjunto;
    }

  
    
}
