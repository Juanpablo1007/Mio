/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.discretas;

import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showInputDialog;
import com.mycompany.discretas.Universal;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Discretas {
  static public String naturales = "";
   static public String  enteros = "";
     static public String racionales = "";
      static public String irracionales = "";

    public static void main(String[] args) {

        Universal[] u_1 = llenarConjuntos();
        Universal[] u_2 = saberTipo(u_1);
        MeterArreglos ( u_2);
        mostrarInfo (u_2);

    }
    

    /**
     * este metodo me dicta la cantidad de eleemntos que tendra erl conjunto
     * universal tambien me dice el tamaño que tendra cada uno de estos
     * subconjuntos y me los llena
     *
     * @return retorna el conjunto universal lleno
     */
    public static Universal[] llenarConjuntos() {
        int tamañoU = parseInt(showInputDialog("Escriba la cantidad de elementos que tendra el conjunto universal"));
        Universal universal[] = new Universal[tamañoU];
        for (int i = 0; i < universal.length; i++) {
            int TamañoConjunto = (parseInt(showInputDialog("Escriba la cantidad de elementos que tendra el  conjunto numero " + (i + 1))));
            double conjunto[] = new double[TamañoConjunto];

            for (int j = 0; j < conjunto.length; j++) {
                conjunto[j] = Double.parseDouble(showInputDialog("escriba un elemento del conjunto " + +(i + 1)));

            }
            Universal conjuntos = new Universal(conjunto, " ", i + 1, conjunto.length);
            universal[i] = conjuntos;
        }
        return universal;
    }

    /**
     * Esta funcion me crea un mensaje y me muestra toda la informacion necesaria 
     * @param u 
     */
    public static void mostrarInfo (Universal[] u){
         int cantidad = cantidadElementos (u); 
        String mensaje =  "- El conjunto universal cuenta con " + cantidad + " de elementos" + "\n \n" ;
        String mensaje2 = "";
       
        for (int i = 0; i < u.length; i++) {
         
                   mensaje2 += "- El elemento o subconjunto " + u[i].getNumeroConjunto() + " cuenta con " +  u[i].getTamaño() + " elementos" + "\n"; 
        }
      JOptionPane.showMessageDialog (null, mensaje+mensaje2 + "\n"+ mostrarContenidos(u));
              
        
    }
    /**
     * Esta funcion me dice a que conjuntos pertenece un subconjunto 
     * @param u
     * @return 
     */
    public static String mostrarContenidos (Universal[] u){
      String mensaje = "";
        for (int i = 0; i < u.length; i++) {
         if (u[i].getTipo().equalsIgnoreCase("Natural"))  {
             mensaje += "El conjunto numero "+ u[i].getNumeroConjunto() +" Que es un conjunto "+  u[i].getTipo()  + " es un subconjunto de los conjuntos " +"( "+ enteros +" " +racionales +" )"+ "\n";
                } else if (u[i].getTipo().equalsIgnoreCase("Entero")) {
                    mensaje += "El conjunto numero "+ u[i].getNumeroConjunto() +" Que es un conjunto "+  u[i].getTipo()  + " es un subconjunto de los conjuntos " + "( " +racionales + " )"+"\n";
                } else if (u[i].getTipo().equalsIgnoreCase("Racional")) {
                    mensaje += "El conjunto numero "+ u[i].getNumeroConjunto() +" Que es un conjunto "+  u[i].getTipo()  + " tiene como subconjuntos a " +"( "+ enteros +" " +naturales + " )"+ "\n";
                } else if (u[i].getTipo().equalsIgnoreCase("Irracional")) {
                    mensaje += "El conjunto numero "+ u[i].getNumeroConjunto() +" Que es un conjunto "+  u[i].getTipo()  + " no tiene subconjuntos y solo esta contenido por el conjunto universal"+ "\n";
                }
                   
        }
        return mensaje;
    }
    /**
     * esta funcion me dice que cantidad de elementos tiene el conjunto universal
     * @param u
     * @return 
     */
    public static int cantidadElementos (Universal[] u){
       int n =0;
        for (int i = 0; i < u.length; i++) {
            n+= u[i].getTamaño();
        }
        return n;
    }
    
    /**
     * Esta clase me dice que tipo es el elemento o subconjunto
     * @param u
     * @return 
     */
    public static Universal[] saberTipo(Universal[] u) {
        for (int i = 0; i < u.length; i++) {
            for (int j = 0; j < u[i].getTamaño(); j++) {
                if (isNatural(u[i].getConjuntos())   ) {
                    u[i].setTipo("Natural");
                    
                } else if (isEntero(u[i].getConjuntos())) {
                    u[i].setTipo("Entero");
                   
                } else if (isRacional(u[i].getConjuntos())) {
                    u[i].setTipo("Racional");
                } else if (isIrracional(u[i].getConjuntos())) {
                    u[i].setTipo("Irracional");
                }
            }
        }
        return u;
    }
    /**
     * esta funcion me mete los elementos o subconjuntos en arreglos dependiendo del conjunto al que pertenezca
     * @param u 
     */
    public static void MeterArreglos (Universal[] u){
        for (int i = 0; i < u.length; i++) {
            if(u[i].getTipo().equalsIgnoreCase("Natural")){
                naturales+= (u[i].getNumeroConjunto())+ " ";
            } else if (u[i].getTipo().equalsIgnoreCase("Entero")){
                enteros+= (u[i].getNumeroConjunto()) + " ";
            }else if (u[i].getTipo().equalsIgnoreCase("Racional")){
                racionales+= (u[i].getNumeroConjunto()) + " ";
            } else if (u[i].getTipo().equalsIgnoreCase("Irracional")){
                irracionales+= (u[i].getNumeroConjunto())+ " ";
            }
        }
 
    }

    /**
     * esta funcion me coge un numero decimal y me lo vuelve entero acercandolo
     * a l numero entero mas cerano a su extremo izquierdo
     *
     * @param n
     * @return
     */
    public static boolean truncar(double n) {
        if ((n - Math.floor(n)) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * esta funcion me verifica si el numero es natural sabiendo si es mayor
     * igual a 0 y que no sea decimal
     *
     * @param conjunto
     * @return
     */
    public static boolean isNatural(double[] conjunto) {
        boolean flag = false;
        int contador =0;
        for (int i = 0; i < conjunto.length; i++) {
        
            if (conjunto[i] >= 0 && truncar(conjunto[i])) {
                
                if (contador == conjunto.length-1 ){
                    flag=true;
                }
                    contador++;
            }

        }
        return flag;
    }

    /**
     * esta funcion me verifica si el numero es entero sabiendo que no sea
     * decimal
     *
     * @param conjunto
     * @return
     */
    public static boolean isEntero(double[] conjunto) {
        boolean flag = false;
        for (int i = 0; i < conjunto.length; i++) {
            if (truncar(conjunto[i]) && conjunto[i] < 0 ) {
                flag = true;
                break;
            }

        }
        return flag;
    }

    /**
     * esta funcion me permite conocer si un numero es racional si el numero de
     * decimales es menor a 10
     *
     * @param conjunto
     * @return
     */
    public static boolean isRacional(double[] conjunto) {
        boolean flag = false;
        String numero = "";
        for (int i = 0; i < conjunto.length; i++) {
            numero = "" + conjunto[i];
            String decimales[] = numero.split("\\.");
            if (decimales[1].length() < 10) {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * esta funcion me permite conocer si un numero es iracional si el numero de
     * decimales es mayor a 10
     *
     * @param conjunto
     * @return
     */
    public static boolean isIrracional(double[] conjunto) {
        boolean flag = false;
        String numero = "";
        for (int i = 0; i < conjunto.length; i++) {
            numero = "" + conjunto[i];
            String decimales[] = numero.split("\\.");
            if (decimales[1].length() > 10) {
                flag = true;
            }

        }
        return flag;
    }
}
