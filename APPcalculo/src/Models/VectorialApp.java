package Models;

import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author USUARIO
 */
public class VectorialApp {

    public static int i = 0;
    public static int w = 0;
    public static int r = 0;
    public static int s = 0;
    public static int conx = 0;
    public static int cony = 0;
    public static int conz = 0;
    public static int conigual = 0;

    public static int cont = 0;
    public static int cont2 = 0;
    public static int cont3 = 0;
    public static String x = new String();
    public static String y = new String();
    public static String z = new String();
    public static String ecuacion1 = new String();
    public static String ecuacion = new String();
    public static double h;
    public static double k;
    public static double j;
    public static double a;
    public static double b;
    public static String XY = new String();
    public static String XZ = new String();
    public static String YZ = new String();
    public static String figura = new String();

    public static String getXY() {
        return XY;
    }

    public static String getXZ() {
        return XZ;
    }

    public static String getYZ() {
        return YZ;
    }

    public static String getFigura() {
        return figura;
    }

    public static void setXY(String XY) {
        VectorialApp.XY = XY;
    }

    public static void setXZ(String XZ) {
        VectorialApp.XZ = XZ;
    }

    public static void setYZ(String YZ) {
        VectorialApp.YZ = YZ;
    }

    public static void setFigura(String figura) {
        VectorialApp.figura = figura;
    }

    public static void main(String[] args) {
        // String aux = JOptionPane.showInputDialog("ingrese la ecuacion ") ;

        ecuacion = "-((x)^2)/3)+((y)^2)/4)-((z)^2))=1";
        ecuacion1 = organizar(ecuacion, i, cont3);
        if (verificar(ecuacion)) {
            JOptionPane.showMessageDialog(null, "La formula escrita no esta correcta, revise los parentesis",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            //Posicion(ecuacion1, i);
            try {
                separarVariables(ecuacion1, i, cont, cont2);
                calcularHKJ(i);
                determinarForma();
                
                System.out.print(  y);
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "La formula escrita no esta correcta, revise los parentesis",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static boolean verificarVariables(char variable, int contador,String ecuacion ){
  ecuacion=ecuacion.toLowerCase();
        for(int i=0;i<ecuacion.length();i++){
            if (ecuacion.charAt(i)==variable){
            contador +=1;
            
        }
        }
          if(contador==1){
              return false;
          }  else{
              return true;
          }
        }
    
    // GENERALLLLLLLLLLLLLLLL
public static boolean Repeticiones(String ecuacion1, int i, int cont){
    if(i==ecuacion1.length()){
        return false;
    }
    if(cont>3){
        return true;
    }
    else{
        if(ecuacion1.charAt(i)== '^'){
            return Repeticiones(ecuacion1,i+1,cont+1);
        }else{
             return Repeticiones(ecuacion1,i+1,cont);
        }
            
    }
        
}
    public static boolean verificar(String aux) {
        if (esNumero(aux, 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void determinarForma() {

        if (isCircu()) {
            XY = sacarTrazasEsferaxy(ecuacion1, i);
            YZ = sacarTrazasEsferaYZ(ecuacion1, i);
            XZ = sacarTrazasEsferaXZ(ecuacion1, i);
            figura = Esfera(ecuacion, i);

        } else if (isH1()) {

            XY = trazaXYH1();
            XZ = trazaXZH1();
            YZ = trazaYZH1();
            figura = H1();

        } else if (isH2() && (!isproducto(x) && !isproducto(y) && !isproducto(z))) {

            XY = trazaXYH2();
            XZ = trazaXZH2();
            YZ = trazaYZH2();
            figura = H2();

        } else if (isCono() && (!isproducto(x) && !isproducto(y) && !isproducto(z))) {
            XY = trazaXYcono();
            XZ = trazaXZcono();
            YZ = trazaYZcono();
            figura = Cono();

        } else if (isElipsoide()) {

            XY = trazasEliXY();
            XZ = trazasEliXZ();
            YZ = trazasEliYZ();
            figura = Elipsoide();

        } else if (isPara()) {

            XY = trazaXYPara();
            XZ = trazaXZPara();
            YZ = trazaYZPara();
            figura = paraboloideHeliptico();

        } else if (isSilla()) {
            XY = trazaXYSillaMontarPo();
            XZ = trazaXZSillaMontar();
            YZ = trazaYZSillaMontar();

            figura = Silla();

        } else {
            JOptionPane.showMessageDialog(null, "Ecuiacion incorrecta, porfavor revise los parentecis o signos. ",
                    "INFOMATIVO", JOptionPane.WARNING_MESSAGE);

        }

    }

    public static String organizar(String aux, int i, int cont3) {
        aux = aux.toLowerCase();
        aux = aux.replace(" ", "");
        if (isIgual(aux, i)) {
            String[] aux_ = aux.split("=");
            return aux_[1] + "=" + aux_[0];
        }
        if (i == aux.length()) {
            return aux;
        }
        return organizar(aux, i + 1, cont3);
    }

    public static boolean isIgual(String aux, int i) {
        Posicion(aux, i);
        boolean auxi = false;
        if (conigual < conx && conigual < cony) {
            auxi = true;
        } else if (conigual < conx && conigual < conz) {
            auxi = true;
        } else if (conigual < cony && conigual < conx) {
            auxi = true;
        } else if (conigual < cony && conigual < conz) {
            auxi = true;
        } else if (conigual < conz && conigual < conx) {
            auxi = true;
        } else if (conigual < conz && conigual < cony) {
            auxi = true;
        }

        return auxi;

    }

    public static void separarVariables(String aux, int i, int cont, int cont2) {

        try {
            if (cont2 == 3) {

            } else {
                if ((conx < cony) && (cony < conz)) {
                    if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            x = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            x = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        y = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            z = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }
                }
                if ((conx < conz) && (conz < cony)) {
                    if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            x = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            x = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        z = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            y = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }
                }
                if ((cony < conx) && (conx < conz)) {
                    if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            y = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = darAux(aux, r);
                            x = y;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        x = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            z = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }
                }
                if ((cony < conz) && (conz < conx)) {
                    if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            y = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        z = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            x = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            x = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }
                }
                if ((conz < conx) && (conx < cony)) {
                    if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            z = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            x = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        x = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            y = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }
                }
                if ((conz < cony) && (cony < conx)) {
                    if (isVariableZ(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            z = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        } else {
                            z = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }

                    } else if (isVariableY(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            y = aux;
                            separarVariables(aux, i, cont, cont2 + 1);

                        }
                        y = darVariable(aux, w);
                        ecuacion1 = darAux(aux, r);
                        aux = ecuacion1;
                        separarVariables(aux, i, cont, cont2 + 1);

                    } else if (isVariableX(aux, i, cont)) {

                        if (aux.charAt(0) == '=') {
                            ecuacion1 = "";
                            x = aux;
                            separarVariables(aux, i, cont, cont2 + 1);
                        } else {
                            x = darVariable(aux, w);
                            ecuacion1 = darAux(aux, r);
                            aux = ecuacion1;
                            separarVariables(aux, i, cont, cont2 + 1);
                        }
                    }

                }

            }
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "La formula escrita no correcta",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Posicion(String aux, int i) {

        if (i == aux.length()) {

        } else {
            if (aux.charAt(i) == 'x') {
                conx = i;
            }
            if (aux.charAt(i) == 'y') {
                cony = i;
            }
            if (aux.charAt(i) == 'z') {
                conz = i;
            }
            if (aux.charAt(i) == '=') {
                conigual = i;
            }
            Posicion(aux, i + 1);
        }

    }

    public static boolean isVariableX(String aux, int i, int cont) {

        if (cont != 0) {

            return true;

        }
        if (i == aux.length() - 1 && cont == 0) {

            return false;

        } else if (aux.charAt(i) == 'x') {
            w = i;

            return isVariableX(aux, i + 1, cont + 1);
        }

        return isVariableX(aux, i + 1, cont);

    }

    public static boolean isVariableY(String aux, int i, int cont) {

        if (cont != 0) {

            return true;

        }
        if (i == aux.length() && cont == 0) {

            return false;

        } else if (aux.charAt(i) == 'y') {
            w = i;

            return isVariableY(aux, i + 1, cont + 1);
        }

        return isVariableY(aux, i + 1, cont);

    }

    public static boolean isVariableZ(String aux, int i, int cont) {

        if (cont != 0) {

            return true;

        }
        if (i == aux.length() && cont == 0) {

            return false;

        } else if (aux.charAt(i) == 'z') {
            w = i;

            return isVariableZ(aux, i + 1, cont + 1);
        }

        return isVariableZ(aux, i + 1, cont);

    }

    public static String darVariable(String aux, int i) {
        try {
            if ((aux.charAt(i) == '+' || aux.charAt(i) == '-' || aux.charAt(i) == '=')
                    && (esXYZ(aux, i + 1) || aux.charAt(i + 1) == '(' || aux.charAt(i + 1) == '-'
                    || (aux.charAt(i) == '=' && esNumero(aux, i + 1)) || i == -5)) {
                r = i;

                if (((esNumero(aux, i - 1) || aux.charAt(i - 1) == ')') && aux.charAt(2) != '(')) {
                    if (aux.charAt(i + 1) == '(' || aux.charAt(i + 1) == '-' || esXYZ(aux, i + 1)) {

                        if (aux.charAt(1) == '(') {
                            return "" + aux.charAt(i - 2) + aux.charAt(i - 1);
                        }
                        return "" + aux.charAt(i - 1);
                    }

                    return "" + aux.charAt(i - 2) + aux.charAt(i - 1);
                } else if (((esNumero(aux, i - 1) || aux.charAt(i - 1) == ')') && aux.charAt(2) == '(')) {
                    return "" + aux.charAt(i - 3) + aux.charAt(i - 2) + aux.charAt(i - 1);
                }
                return "";

            } else {

                if ((aux.charAt(0) == '+' || aux.charAt(0) == '-' || aux.charAt(0) == '=') && aux.charAt(2) == '(') {

                    return aux.charAt(i - 3) + darVariable(aux, i + 1);
                } else if ((aux.charAt(1) == '(') && aux.charAt(0) == '(') {
                    return aux.charAt(i - 2) + darVariable(aux, i + 1);
                } else if ((aux.charAt(1) == '('
                        && (aux.charAt(0) == '+' || aux.charAt(0) == '-' || aux.charAt(0) == '='))) {
                    return aux.charAt(i - 2) + darVariable(aux, i + 1);
                } else if ((aux.charAt(0) == '+' || aux.charAt(0) == '-' || aux.charAt(0) == '=')) {
                    return aux.charAt(i - 2) + darVariable(aux, i + 1);
                } else if ((aux.charAt(0) == '(') && esXYZ(aux, 1)) {
                    return aux.charAt(i - 1) + darVariable(aux, i + 1);
                } else if ((aux.charAt(1) == '(') && esXYZ(aux, 1)) {
                    return aux.charAt(i - 2) + darVariable(aux, i + 1);
                } else if ((esNumero(aux, 0))) {
                    i = -5;
                    return darVariable(aux, i);
                }

                return aux.charAt(i + 1) + darVariable(aux, i);

            }

        } catch (java.lang.StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "La formula escrita no correcta",
                    "ERROR", JOptionPane.ERROR_MESSAGE);

        }

        return darVariable(aux, i);
    }

    public static String darAux(String aux, int w) {

        if (w == aux.length()) {
            return "";
        } else {
            return aux.charAt(w) + darAux(aux, w + 1);

        }

    }

    public static boolean esNumero(String ecuacion, int i) {

        char aux = ecuacion.charAt(i);
        if ("0123456789".contains(String.valueOf(aux).toLowerCase())) {
            return true;
        }
        return false;

    }

    public static boolean esLetra(String ecuacion, int i) {

        char aux = ecuacion.charAt(i);
        return "abcdefghijklmnñopqrstuvw".contains(String.valueOf(aux).toLowerCase());

    }

    public static boolean esXYZ(String ecuacion, int i) {

        char aux = ecuacion.charAt(i);
        return "xyz".contains(String.valueOf(aux).toLowerCase());

    }

    public static boolean esletra(String ecuacion, int i) {

        char aux = ecuacion.charAt(i);
        return "abcdefghijklmnopqrstuvwñ_<>ªº¿·#@&,:;!Ç{}".contains(String.valueOf(aux).toLowerCase());

    }

    public static boolean tieneLetra(String ecuacion, int i, int j) {
        if (i == ecuacion.length()) {
            return false;
        }
        if (j != 0) {
            return true;

        } else {
            if (esletra(ecuacion, i)) {
                return tieneLetra(ecuacion, i + 1, 1);
            }
            return tieneLetra(ecuacion, i + 1, 0);
        }
    }

    public static String calcularVariable(String e, int j) {
        String aux = "";
        for (int i = j - 1; i == 0; i--) {
            aux += e.charAt(i);
        }
        return aux;
    }

    public static String calcularEcuacion(String e, int j) {
        String aux = "";
        for (int i = j; i == e.length(); i++) {
            aux += e.charAt(i);
        }
        return aux;
    }

    public static boolean isCero(String variable, int i) {

        if (variable.length() == 2) {
            return false;
        }

        if ((variable.charAt(i) == '^'
                || ((variable.charAt(i) == ')' || (esXYZ(variable, i)) && variable.charAt(i + 1) == '*')))) {
            return false;
        } else {
            if (esNumero(variable, i)) {
                i++;
                return true;
            } else {
                return isCero(variable, i + 1);
            }
        }
    }

    public static String X(String x, int i) {

        if (i == x.length() - 1) {
            return "";
        }

        if ((x.charAt(i) == '^' || ((x.charAt(i) == ')' || esXYZ(x, i)) && x.charAt(i + 1) == '*'))) {
            return "";
        } else {

            if (esNumero(x, i)) {
                return x.charAt(i) + X(x, i + 1);
            } else {
                return X(x, i + 1);
            }

        }
    }

    public static boolean isNegativoX(String x, int i) {

        if (x.length() == 2) {
            return false;
        }
        if ((x.charAt(i) == '^' || ((x.charAt(i) == ')' || esXYZ(x, i)) && x.charAt(i + 1) == '*'))) {
            return false;
        } else {
            if (x.charAt(i) == '+' && esNumero(x, i + 1)) {
                i++;
                return true;
            } else {

                return isNegativoX(x, i + 1);
            }

        }
    }

    public static boolean existe(String variable) {
        if (variable.contentEquals("")) {
            return true;
        }
        return false;
    }

    public static void calcularHKJ(int i) {

        if (!isCero(x, i) || existe(x)) {
            h = 0.0;
        } else {

            if (isNegativoX(x, i)) {
                h = Double.parseDouble(X(x, i) + ".00") * -1;
            } else {
                h = Double.parseDouble(X(x, i) + ".00");
            }
        }
        if (!isCero(y, i) || existe(y)) {
            k = 0.0;
        } else {
            if (isNegativoX(y, i)) {
                k = Double.parseDouble(X(y, i) + ".00") * -1;
            } else {
                k = Double.parseDouble(X(y, i) + ".00");
            }
        }
        if (!isCero(z, i) || existe(z)) {
            j = 0.0;
        } else {
            if (isNegativoX(z, i)) {
                j = Double.parseDouble(X(z, i) + ".00") * -1;
            } else {
                j = Double.parseDouble(X(z, i) + ".00");
            }
        }

    }

    public static String Sacardividendo(String variable, int i) {
        if (!isdividir(variable)) {
            return "1.0";
        }

        String aux[] = variable.split("/");

        if (aux[1].length() == i) {
            return "";
        } else {
            if (esNumero(aux[1], i)) {
                return aux[1].charAt(i) + Sacardividendo(variable, i + 1);
            }
            return Sacardividendo(variable, i + 1);

        }
    }

    public static boolean isdividir(String variable) {
        if (variable.contains("/")) {
            return true;
        }
        return false;
    }

    public static String Sacarproducto(String variable, int i) {
        if (!isproducto(variable)) {
            return "1";
        }
        String aux[] = variable.split("\\*");

        if (aux[1].length() == i) {
            return "";
        } else {
            if (esNumero(aux[1], i)) {
                return aux[1].charAt(i) + Sacarproducto(variable, i + 1);
            }
            return Sacarproducto(variable, i + 1);

        }
    }

    public static boolean isproducto(String variable) {
        if (variable.contains("*")) {
            return true;
        }
        return false;
    }

    // ESFERAAAAAA
    public static boolean isCircu() {
        boolean centinela = false;

        if ((x.charAt(0) != '-') && (y.charAt(0) != '-') && (z.charAt(0) != '-')) {
            centinela = true;
            if ((x.contains("/")) || (y.contains("/")) || (z.contains("/") || x.contains("*")
                    || y.contains("*") || z.contains("*")) || ecuacion1.contentEquals("=0")) {
                centinela = false;
            }
        }

        return centinela;

    }

    // private static boolean inVaribleC(String ecuacion) {
    // if (ecuacion.charAt(0) == '=') {
    // if ((ecuacion.charAt(1) == '(') || esNumero(ecuacion, 1)) {
    // return true;
    // }
    //
    // }
    // return false;
    // }
    public static String sacarRadio(String ecuacion, int i) {
        if (i == ecuacion.length()) {
            return "";
        } else {
            if (esNumero(ecuacion, i)) {

                return ecuacion.charAt(i) + sacarRadio(ecuacion, i + 1);
            }
        }
        return sacarRadio(ecuacion, i + 1);
    }

    public static String sacarTrazasEsferaxy(String ecuacion, int i) {
        calcularHKJ(i);
        String mensaje = "";
        double radio = Math.sqrt(Double.parseDouble(sacarRadio(ecuacion, i)));
        double x1 = h + radio;
        double x2 = h - radio;
        double y1 = k + radio;
        double y2 = k - radio;
        String centro = "(" + h + ", " + k + ", " + j + ")";
        mensaje = "         XY           " + "\n" + " Puntos en X: " + "  PUNTO 1: " + x1 + "  PUNTO 2: " + x2 + "\n"
                + "Puntos en Y: " + "  PUNTO 1: " + y1 + "  PUNTO 2: " + y2;
        return mensaje;

    }

    public static String sacarTrazasEsferaYZ(String ecuacion, int i) {
        calcularHKJ(i);
        String mensaje = "";
        double radio = Math.sqrt(Double.parseDouble(sacarRadio(ecuacion, i)));
        double y1 = k + radio;
        double y2 = k - radio;
        double z1 = j + radio;
        double z2 = j - radio;

        mensaje = "         YZ           " + "\n" + " Puntos en Y: " + "  PUNTO 1: " + y1 + "  PUNTO 2: " + y2 + "\n"
                + "Puntos en Z: " + "  PUNTO 1: " + z1 + "  PUNTO 2: " + z2;
        return mensaje;

    }

    public static String sacarTrazasEsferaXZ(String ecuacion, int i) {
        calcularHKJ(i);
        String mensaje = "";
        double radio = Math.sqrt(Double.parseDouble(sacarRadio(ecuacion, i)));
        double x1 = h + radio;
        double x2 = h - radio;
        double z1 = j + radio;
        double z2 = j - radio;

        mensaje = "         XZ           " + "\n" + " Puntos en X: " + "  PUNTO 1: " + x1 + "  PUNTO 2: " + x2 + "\n"
                + "Puntos en Z: " + "  PUNTO 1: " + z1 + "  PUNTO 2: " + z2;
        return mensaje;

    }

    public static String Esfera(String ecuacion, int i) {
        String centro = "(" + h + ", " + k + ", " + j + ")";

        String mensaje = "         ESFERA           " + "\n" + "\n"
                + "Esta figura tiene tres trazas xy, yz, xz, todas sus trazas corresponden a circufenrencias" + "\n"
                + "\n" + "RADIO:" + Math.sqrt(Double.parseDouble(sacarRadio(ecuacion1, i))) + "\n" + "\n" + "CENTRO:" + centro + "\n" + "\n";
        return mensaje;
    }

    // ELIPSOIDEEEEEEEEE
    public static boolean isElipsoide() {
        boolean aux = false;
        if ((x.charAt(0) != '-') && (y.charAt(0) != '-') && (z.charAt(0) != '-') && !isproducto(x) && !isproducto(y)
                && !isproducto(z)) {
            if (ecuacion1.contains("=1")) {
                return true;
            }
        }
        return aux;
    }

    // public static boolean isUno() {
    // int aux = ecuacion.length() - 1;
    // if (ecuacion.charAt(aux) == '1') {
    // return true;
    // }
    // return false;
    // }
    public static boolean DeterminarABElip(String variable1, String variable2) {

        double n1 = Double.parseDouble(Sacardividendo(variable1, i));
        double n2 = Double.parseDouble(Sacardividendo(variable2, i));
        if (n1 >= n2) {
            a = Math.sqrt(n1);
            b = Math.sqrt(n2);
            return true;
        } else {
            b = Math.sqrt(n1);
            a = Math.sqrt(n2);
            return false;
        }
    }

    public static String trazasEliXY() {
        calcularHKJ(i);
        String mensaje = "";
        String mensajeaux = "";
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        if (DeterminarABElip(x, y)) {
            x1 = a + h;
            x2 = h - a;
            y1 = k;
            y2 = k;
            b1 = k + b;
            b2 = k - b;
            mensajeaux = "Punto 1 (x,y) en: " + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (x,y) en:  " + "(" + x2
                    + " , " + y2 + ")" + "\n" + "Punto 3 (x,y) en:  " + "(" + h + " , " + b1 + ")" + "\n"
                    + "Punto 4 (x,y) en:  " + "(" + h + " , " + b2 + ")";
        } else {
            x1 = h;
            x2 = h;
            y1 = k + a;
            y2 = k - a;
            b1 = h + b;
            b2 = h - b;
            mensajeaux = "Punto 1 (y,x) en: " + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (y,x) en:  " + "(" + y2
                    + " , " + x2 + ")" + "\n" + "Punto 3 (y,x) en:  " + "(" + k + " , " + b1 + ")" + "\n"
                    + "Punto 4 (y,x) en:  " + "(" + k + " , " + b2 + ")";
            ;
        }
        mensaje = "                  XY    " + "\n" + "\n" + mensajeaux;
        return mensaje;

    }

    public static String trazasEliYZ() {
        calcularHKJ(i);
        String mensaje = "";
        String mensajeaux = "";
        double y1;
        double y2;
        double z1;
        double z2;
        double b1;
        double b2;
        if (DeterminarABElip(y, z)) {
            y1 = a + k;
            y2 = k - a;
            z1 = j;
            z2 = j;
            b1 = j + b;
            b2 = j - b;
            mensajeaux = "Punto 1 (y,z) en: " + "(" + y1 + " , " + z1 + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + y2
                    + " , " + z2 + ")" + "\n" + "Punto 3 (y,z) en:  " + "(" + k + " , " + b1 + ")" + "\n"
                    + "Punto 4 (y,z) en:  " + "(" + k + " , " + b2 + ")";
        } else {
            y1 = k;
            y2 = k;
            z1 = j + a;
            z2 = j - a;
            b1 = k + b;
            b2 = k - b;
            mensajeaux = "Punto 1 (z,y) en: " + "(" + z1 + " , " + y1 + ")  " + "\n" + "Punto 2 (z,y) en:  " + "(" + z2
                    + " , " + y2 + ")" + "\n" + "Punto 3 (z,y) en:  " + "(" + j + " , " + b1 + ")" + "\n"
                    + "Punto 4 (z,y) en:  " + "(" + j + " , " + b2 + ")";
            ;
        }
        mensaje = "                  YZ    " + "\n" + "\n" + mensajeaux;
        return mensaje;

    }

    public static String trazasEliXZ() {
        calcularHKJ(i);
        String mensaje = "";
        String mensajeaux = "";
        double x1;
        double x2;
        double z1;
        double z2;
        double b1;
        double b2;
        if (DeterminarABElip(x, z)) {
            x1 = a + h;
            x2 = h - a;
            z1 = j;
            z2 = j;
            b1 = j + b;
            b2 = j - b;
            mensajeaux = "Punto 1 (x,z) en: " + "(" + x1 + " , " + z1 + ")  " + "\n" + "Punto 2 (x,z) en:  " + "(" + x2
                    + " , " + z2 + ")" + "\n" + "Punto 3 (x,z) en:  " + "(" + h + " , " + b1 + ")" + "\n"
                    + "Punto 4 (x,z) en:  " + "(" + h + " , " + b2 + ")";
        } else {
            x1 = h;
            x2 = h;
            z1 = j + a;
            z2 = j - a;
            b1 = h + b;
            b2 = h - b;
            mensajeaux = "Punto 1 (z,x) en: " + "(" + z1 + " , " + x1 + ")  " + "\n" + "Punto 2 (z,x) en:  " + "(" + z2
                    + " , " + x2 + ")" + "\n" + "Punto 3 (z,x) en:  " + "(" + j + " , " + b1 + ")" + "\n"
                    + "Punto 4 (z,x) en:  " + "(" + j + " , " + b2 + ")";
            ;
        }
        mensaje = "                  XZ    " + "\n" + "\n" + mensajeaux;
        return mensaje;

    }

    public static String Elipsoide() {
        String centro = "(" + h + ", " + k + ", " + j + ")";
        String mensaje = "         ELIPSOIDE           " + "\n" + "\n"
                + "Esta figura tiene tres trazas xy, yz, xz, todas sus trazas corresponden a elipses" + "\n" + "\n"
                + "CENTRO:" + centro;
        return mensaje;
    }

    // CONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    public static boolean isCono1() {
        if (((x.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((y.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((z.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCono2() {
        if (((x.charAt(0) == '=' && x.charAt(1) != '-')
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((y.charAt(0) == '=' && y.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((z.charAt(0) == '=' && z.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {

            return true;

        }
        return false;
    }

    public static void DeterminarABPara(String variable1, String variable2) {

        double n1 = Double.parseDouble(Sacardividendo(variable1, i));
        double n2 = Double.parseDouble(Sacarproducto(variable2, i));
        a = Math.sqrt(n1);
        b = n2;
    }

    public static boolean por(String variable) {
        if (variable.contains("*")) {
            return false;
        }
        return true;
    }

    public static boolean isCono() {
        if (isCono1()) {
            return true;
        } else if (isCono2()) {
            return true;
        }
        return false;
    }

    public static void DeterminarABCono(String variable1, String variable2) {

        double n1 = Double.parseDouble(Sacardividendo(variable1, i));
        double n2 = Double.parseDouble(Sacardividendo(variable2, i));
        a = n1;
        b = n2;
    }

    public static double c(String variable) {
        double c = Double.parseDouble(Sacardividendo(variable, i));
        return Math.sqrt(c);
    }

    public static double p(String variable) {
        double c = Double.parseDouble(Sacarproducto(variable, i));
        return c;
    }

    public static String trazaXYcono() {

        String mensaje = "";

        String ecua = "";
        String intercepcion = "(" + h + " , " + k + ")";
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = c(z);

        if (((x.charAt(0) == '-') || (x.charAt(0) == '=' && x.charAt(1) != '-')
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            DeterminarABCono(y, x);
            double dividendo = a / b;

            ecua = "x =  " + "(+/- raiz cuadrada(y + " + k + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            mensaje = "z=" + j + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(x,y):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if (((y.charAt(0) == '-') || (y.charAt(0) == '=' && y.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABCono(x, y);
            double dividendo = a / b;

            ecua = "y =  " + "+/- raiz cuadrada((x + " + h + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            mensaje = "z=" + j + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(y,x):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if ((((x.charAt(0) == '+') || x.charAt(0) == '(' || x.charAt(1) != 'x') && (y.charAt(0) == '+')
                || y.charAt(0) == '(' || y.charAt(1) != 'x')) {
            double z1 = c + j;
            double z2 = -c + j;
            if (DeterminarABElip(x, y)) {
                x1 = a + h;
                x2 = h - a;
                y1 = k;
                y2 = k;
                b1 = k + b;
                b2 = k - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse z=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (x,y) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,y) en:  " + "(" + h
                        + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "(" + h + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse z=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (x,y) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (x,y) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "("
                        + h + " , " + b2 + ")";
            } else {
                DeterminarABElip(y, x);
                x1 = h;
                x2 = h;
                y1 = k + a;
                y2 = k - a;
                b1 = h + b;
                b2 = h - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse z=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (y,x) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (y,x) en:  " + "(" + k
                        + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "(" + k + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse z=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (y,x) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (y,x) en:  " + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "("
                        + k + " , " + b2 + ")";
                ;
            }
        }
        return "                  XY   " + "\n" + "\n" + mensaje;
    }

    public static String trazaXZcono() {

        String mensaje = "";

        String ecua = "";
        String intercepcion = "(" + h + " , " + j + ")";
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = c(y);

        if (((x.charAt(0) == '-') || (x.charAt(0) == '=' && x.charAt(1) != '-')
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABCono(z, x);
            double dividendo = a / b;

            ecua = "x =  " + "(+/- raiz cuadrada(z + " + j + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            mensaje = "y=" + k + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(x,z):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if (((z.charAt(0) == '-') || (z.charAt(0) == '=' && z.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABCono(x, z);
            double dividendo = a / b;

            ecua = "z =  " + "+/- raiz cuadrada((x + " + h + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            mensaje = "y=" + k + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(z,x):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if ((((x.charAt(0) == '+') || x.charAt(0) == '(' || x.charAt(0) == 'x') && (z.charAt(0) == '+')
                || z.charAt(0) == '(' || z.charAt(0) == 'z')) {
            double z1 = c + k;
            double z2 = -c + k;
            if (DeterminarABElip(x, z)) {
                x1 = a + h;
                x2 = h - a;
                y1 = j;
                y2 = j;
                b1 = j + b;
                b2 = j - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse y=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (x,z) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,z) en:  " + "(" + h
                        + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "(" + h + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse y=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (x,z) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (x,z) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "("
                        + h + " , " + b2 + ")";
            } else {
                DeterminarABElip(z, x);
                x1 = h;
                x2 = h;
                y1 = j + a;
                y2 = j - a;
                b1 = h + b;
                b2 = h - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse y=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (z,x) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (z,x) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "(" + j + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse y=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (z,x) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (z,x) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,x) en:  " + "("
                        + j + " , " + b2 + ")";
                ;
            }
        }
        return "                  XZ   " + "\n" + "\n" + mensaje;
    }

    public static String trazaYZcono() {

        String mensaje = "";

        String ecua = "";
        String intercepcion = "(" + h + " , " + j + ")";
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = c(x);

        if (((y.charAt(0) == '-') || (y.charAt(0) == '=' && y.charAt(1) != '-')
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABCono(z, y);
            double dividendo = a / b;

            ecua = "y =  " + "(+/- raiz cuadrada(z + " + j + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            mensaje = "x=" + h + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(y,z):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if (((z.charAt(0) == '-') || (z.charAt(0) == '=' && z.charAt(1) != '-')
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            DeterminarABCono(y, z);
            double dividendo = a / b;

            ecua = "z =  " + "+/- raiz cuadrada((y + " + h + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            mensaje = "x=" + h + "\n"
                    + "La traza forma una figura compuesta de dos lineas rectas  con igual pendiente  que se inteceptan en un punto"
                    + "\n" + "\n" + "intercecion(z,y):  " + "\n" + intercepcion + "\n" + "la ecuacion de la traza es:"
                    + "\n" + ecua;
        } else if ((((y.charAt(0) == '+') || y.charAt(0) == '(' || y.charAt(0) == 'x') && (z.charAt(0) == '+')
                || z.charAt(0) == '(' || z.charAt(0) == 'z')) {
            double z1 = c + h;
            double z2 = -c + h;
            if (DeterminarABElip(y, z)) {
                x1 = a + k;
                x2 = k - a;
                y1 = j;
                y2 = j;
                b1 = j + b;
                b2 = j - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse y=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (y,z) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (y,z)) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (y,z) en:  " + "(" + k
                        + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "(" + k + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse y=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (y,z) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (y,z) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "("
                        + k + " , " + b2 + ")";
            } else {
               DeterminarABElip(z, y);

                x1 = k;
                x2 = k;
                y1 = j + a;
                y2 = j - a;
                b1 = k + b;
                b2 = k - b;
                mensaje = "La traza forma dos elipses" + "\n" + "\n" + "     primera elipse y=  " + z1 + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (z,y) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (z,y) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (z,y) en:  " + "(" + j
                        + " , " + b1 + ")" + "\n" + "Punto 4 (z,y) en:  " + "(" + j + " , " + b2 + ")" + "\n" + "\n"
                        + "     segunda elipse y=  " + z2 + "\n" + "\n" + "puntos:" + "\n" + "\n" + "Punto 1 (z,y) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (z,y) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (z,y) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,y) en:  " + "("
                        + j + " , " + b2 + ")";
                ;
            }
        }
        return "                  YZ   " + "\n" + "\n" + mensaje;
    }

    public static String Cono() {
        String centro = "(" + h + ", " + k + ", " + j + ")";
        String mensaje = "         CONO           " + "\n" + "\n"
                + "Esta figura tiene tres trazas xy, yz, xz, dos elipses, y dos que son  lineas rectas interseptadas en un punto"
                + "\n" + "\n" + "CENTRO:" + centro;
        return mensaje;
    }
    // Hiperboloide de una hojaaaaaaa

    public static boolean isH1() {
        if (((x.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        if (((y.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        if (((z.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        return false;
    }

    public static void DeterminarABHiper(String variable1, String variable2) {

        if (variable1.contains("/")  ) {
            double n1 = Double.parseDouble(Sacardividendo(variable1, i));
            a= Math.sqrt(n1);
        }else{
               a = 1;
        }
         if (variable2.contains("/")  ) {
            double n2 = Double.parseDouble(Sacardividendo(variable1, i));
              b= Math.sqrt(n2);
        }else{
               b = 1;
        }
        

    }

    public static String elipseY() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (k == 0) {
            aux = k + 5;
        }
        else{
        aux = k * 2;
        }
        Xnueva = 1 + (Math.pow(aux + (k * -1), 2) / (c(y) * c(y)));

        if (DeterminarABElip(x, z)) {
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = a + h;
            x2 = h - a;
            y1 = j;
            y2 = j;
            b1 = j + b;
            b2 = j - b;
            mensaje = "y toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en y= " + aux + "\n" + "\n" + "Punto 1 (x,z) en: " + "(" + x1 + " , " + y1
                    + ")  " + "\n" + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,z) en:  "
                    + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "(" + h + " , " + b2 + ")";
            ;
        } else {
            DeterminarABElip(z, x);
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = h;
            x2 = h;
            y1 = j + a;
            y2 = j - a;
            b1 = h + b;
            b2 = h - b;
            mensaje = "y toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en y= " + aux + "\n" + "\n" + "Punto 1 (z,x) en: " + "(" + y1 + " , " + x1
                    + ")  " + "\n" + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (z,x) en:  "
                    + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,x) en:  " + "(" + j + " , " + b2 + ")";

        }
        return mensaje;
    }

    public static String elipseZ() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (j == 0) {
            aux = j + 5;
        }else{

        aux = j * 2;
        }
        Xnueva = 1 + (Math.pow(aux + (j * -1), 2) )/ (c(z) * c(z));

        if (DeterminarABElip(x, y)) {
            a = a*a * Xnueva;
            b = b*b * Xnueva;
          
            x1 = Math.sqrt(a) + h;
            x2 = h - Math.sqrt(a);
            y1 = k;
            y2 = k;
            b1 = k + Math.sqrt(b);
            b2 = k - Math.sqrt(b);
            mensaje = "z toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en z= " + aux + "\n" + "\n" + "Punto 1 (x,y) en: " + "(" + x1 + " , " + y1
                    + ")  " + "\n" + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,y) en:  "
                    + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "(" + h + " , " + b2 + ")";
            ;
        } else {
             DeterminarABElip(y, x);
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            
            x1 = h;
            x2 = h;
            y1 = k + a;
            y2 = k - a;
            b1 = h + b;
            b2 = h - b;
            mensaje = "z toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en z= " + aux + "\n" + "\n" + "Punto 1 (y,x) en: " + "(" + y1 + " , " + x1
                    + ")  " + "\n" + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (y,x) en:  "
                    + "(" + b1 + " , " + k + ")" + "\n" + "Punto 4 (y,x) en:  " + "(" + b2 + " , " + k + ")" ;

        }
        return mensaje;
    }

    public static String elipseX() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (h == 0) {
            aux = h + 5;
        }
        else{
        aux = h * 2;
        }
        Xnueva = 1 + (Math.pow(aux + (h * -1), 2) / (c(x) * c(x)));

        if (DeterminarABElip(y, z)) {
             a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = a + k;
            x2 = k - a;
            y1 = j;
            y2 = j;
            b1 = j + b;
            b2 = j - b;
            mensaje = "x toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en x= " + aux + "\n" + "\n" + "Punto 1 (y,z) en: " + "(" + x1 + " , " + y1
                    + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (y,z) en:  "
                    + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "(" + k + " , " + b2 + ")";
            ;
        } else {
             DeterminarABElip(z, y);
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = k;
            x2 = k;
            y1 = j + a;
            y2 = j - a;
            b1 = k + b;
            b2 = k - b;
            mensaje = "x toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "La primera en x= " + aux + "\n" + "\n" + "Punto 1 (y,z) en: " + "(" + y1 + " , " + x1
                    + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (y,z) en:  "
                    + "(" + b1 + " , " + j + ")" + "\n" + "Punto 4 (y,z) en:  " + "(" + b2 + " , " + j + ")" ;

        }
        return mensaje;
    }
    public static String elipse(String variable1,String variable4,String variable5, String variable2,String variable, double uno, double dos,double tres, String letra) {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (uno == 0) {
            aux = uno + 5;
        }
        else{
        aux = uno * 2;
        }
        Xnueva = 1 + (Math.pow(aux + (uno * 1), 2) / (c(variable) * c(variable)));

        if (DeterminarABElip(variable1, variable2)) {
             a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = a + dos;
            x2 = dos - a;
            y1 = tres;
            y2 = tres;
            b1 = tres + b;
            b2 = tres - b;
            mensaje =  "\n"
                    + "\n" + "La segunda en "+ letra +"= " + -aux + "\n" + "\n" + "Punto 1 ("+variable4+","+variable5+")"+ "en: " + "(" + x1 + " , " + y1
                    + ")  " + "\n" + "Punto 2 ("+variable4+","+variable5+") en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 ("+variable4+","+variable5+") en:  "
                    + "(" + dos + " , " + b1 + ")" + "\n" + "Punto 4 ("+variable4+","+variable5+") en:  " + "(" + dos + " , " + b2 + ")";
            ;
        } else {
             DeterminarABElip(variable2, variable1);
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = dos;
            x2 = dos;
            y1 = tres + a;
            y2 = tres - a;
            b1 = dos + b;
            b2 = dos - b;
            mensaje =  "\n"
                    + "\n" + "La segunda en " + letra +"= " + -aux + "\n" + "\n" + "Punto 1 ("+variable5+","+variable4+") en: " + "(" + y1 + " , " + x1
                    + ")  " + "\n" + "Punto 2 ("+variable5+","+variable4+") en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 ("+variable5+","+variable4+") en:  "
                    + "(" + tres + " , " + b1 + ")" + "\n" + "Punto 4 ("+variable5+","+variable4+") en:  " + "(" + tres + " , " + b2 + ")";

        }
        return mensaje;
    }
  

    public static String trazaXYH1() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;
        String aux2 = " ";
        if (((x.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            aux2 = "                  XY   ";
            DeterminarABHiper(y, x);
            x1 = h;
            x2 = h;
            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (k + aux) + ")";
            foco2 = "(" + h + " , " + (k - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h+b) + " , " + k + ")";
            conjugado2 = "(" + (h-b) + " , " + k + ")";
            mensaje = "la traza forma una hiperbole en z= " + j + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado ((x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n"
                    + "Forma tambien dos elipses" + "\n" + "\n" + elipseX() + elipse(y,"y","z",z,x,h,k,j,"x")+ "\n" + "\n";

        } else if (((y.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABHiper(x, y);
            aux2 = "                  XY   ";
            x1 = h + a;
            x2 = h - a;
            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + k + ")";
            foco2 = "(" + (h - aux) + " , " + k + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + h + " , " + (k+b) + ")";
            conjugado2 = "(" + h + " , " + (k-b) + ")";
            mensaje = "la traza forma una hiperbole en z= " + j + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n"
                    + "Forma tambien dos elipses" + "\n" + "\n" + elipseY() +elipse(x,"x","z",z,y,k,h,j,"y")+ "\n" + "\n";

        } else if ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i)))) {
            aux2 = "                  XY   ";
            if (DeterminarABElip(x, y)) {
                x1 = a + h;
                x2 = h - a;
                y1 = k;
                y2 = k;
                b1 = k + b;
                b2 = k - b;
                mensaje = "la traza forma una elipse en z= " + j + "\n" + "\n" + "\n" + "\n" + "Punto 1 (x,y) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (x,y) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "("
                        + h + " , " + b2 + ")";
                ;
            } else {
                DeterminarABElip(y, x);
                x1 = h;
                x2 = h;
                y1 = k + a;
                y2 = k - a;
                b1 = h + b;
                b2 = h - b;
                mensaje = "la traza forma una elipse en z= " + j + "\n" + "\n" + "\n" + "\n" + "Punto 1 (y,x) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (y,x) en:  " + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "("
                        + k + " , " + b2 + ")";
                ;
            }
        }

        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String trazaXZH1() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;
        String aux2 = "";

        if (((x.charAt(0) == '-') && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABHiper(z, x);
            aux2 = "                  XZ   ";

            x1 = h;
            x2 = h;
            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            y1 = j + a;
            y2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h+b) + " , " + j + ")";
            conjugado2 = "(" + (h-b) + " , " + j + ")";
            mensaje = "la traza forma una hiperbole en y = " + k + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1
                    + " " + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (((z.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABHiper(x, z);
            aux2 = "                  XZ   ";
            x1 = h + a;
            x2 = h - a;
            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            y1 = j;
            y2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + j + ")";
            foco2 = "(" + (h - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + h + " , " + (j+b) + ")";
            conjugado2 = "(" + h + " , " + (j-b) + ")";
            mensaje = "la traza forma una hiperbole en y= " + k + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)))) {
            aux2 = "                  XZ   ";
            if (DeterminarABElip(x, z)) {
                x1 = a + h;
                x2 = h - a;
                y1 = j;
                y2 = j;
                b1 = j + b;
                b2 = j - b;
                mensaje = "la traza forma una elipse en y= " + k + "\n" + "\n" + "\n" + "\n" + "Punto 1 (x,z) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (x,z) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "("
                        + h + " , " + b2 + ")";
                ;
            } else {
                DeterminarABElip(z, x);
                x1 = h;
                x2 = h;
                y1 = j + a;
                y2 = j - a;
                b1 = j + b;
                b2 = j - b;
                mensaje = "la traza forma una elipse en y= " + k + "\n" + "\n" + "\n" + "\n" + "Punto 1 (z,x) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (z,x) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,x) en:  " + "("
                        + j + " , " + b2 + ")";
                ;
            }
        }

        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String trazaYZH1() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String aux2 = "  ";
        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;

        if (((y.charAt(0) == '-') && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            aux2 = "                  YZ   ";
            DeterminarABHiper(z, y);
            x1 = k;
            x2 = k;
            String centro = "centro (y,z)" + "\n" + "(" + k + " , " + j + ")";
            y1 = j + a;
            y2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (k+b) + " , " + j + ")";
            conjugado2 = "(" + (k-b) + " , " + j + ")";
            mensaje = "la traza forma una hiperbole en x = " + h + "\n" + "\n" + "vertices (z,y)" + "\n" + vertice1
                    + " " + vertice2 + "\n" + "\n" + "focos (z,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (z,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (((z.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            aux2 = "                  YZ   ";
            DeterminarABHiper(y, z);
            x1 = k + a;
            x2 = k - a;
            String centro = "centro (x,z)" + "\n" + "(" + k + " , " + j + ")";
            y1 = j;
            y2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + k + " , " + (j+b) + ")";
            conjugado2 = "(" + k + " , " + (j-b) + ")";
            mensaje = "la traza forma una hiperbole en x= " + h + "\n" + "\n" + "vertices (z,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (z,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (z,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n"
                    + "Forma tambien dos elipses" + "\n" + "\n" + elipseZ() +elipse(x,"x","y",y,z,j,h,k,"z")+ "\n" + "\n";

        } else if ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)))) {
            aux2 = "                  YZ   ";
            if (DeterminarABElip(y, z)) {
                x1 = a + k;
                x2 = k - a;
                y1 = j;
                y2 = j;
                b1 = j + b;
                b2 = j - b;
                mensaje = "la traza forma una elipse en x= " + h + "\n" + "\n" + "\n" + "\n" + "Punto 1 (y,z) en: "
                        + "(" + x1 + " , " + y1 + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")"
                        + "\n" + "Punto 3 (y,z) en:  " + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "("
                        + k + " , " + b2 + ")";

            } else {
                DeterminarABElip(z, y);
                x1 = k;
                x2 = k;
                y1 = j + a;
                y2 = j - a;
                b1 = k + b;
                b2 = k - b;
                mensaje = "la traza forma una elipse en x= " + h + "\n" + "\n" + "\n" + "\n" + "Punto 1 (z,y) en: "
                        + "(" + y1 + " , " + x1 + ")  " + "\n" + "Punto 2 (z,y) en:  " + "(" + y2 + " , " + x2 + ")"
                        + "\n" + "Punto 3 (z,y) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,y) en:  " + "("
                        + j + " , " + b2 + ")";
                ;
            }
        }

        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String H1() {
        String centro = "(" + h + ", " + k + ", " + j + ")";
        String mensaje = "         HIPERBOLOIDE DE UNA HOJA           " + "\n" + "\n"
                + "Esta figura tiene cinco trazas, tres elipses, y dos hiperbolas" + "\n" + "\n" + "CENTRO:" + centro;
        return mensaje;
    }

    // Hiperboloide de dos hojaaaaaaas
    public static boolean isH2() {
        if ((x.charAt(0) == '-') && (y.charAt(0) == '-') && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        if ((z.charAt(0) == '-') && (x.charAt(0) == '-') && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        if ((z.charAt(0) == '-') && (y.charAt(0) == '-') && (x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))) {
            if (ecuacion1.equals("=1")) {
                return true;
            }
        }
        return false;
    }
 public static String elipseH(String variable1,String variable4, String variable5, String variable2,String variable, double uno, double dos,double tres, String letra) {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (uno == 0) {
            aux = uno + 5;
        }
        else{
        aux = uno * 2;
        }
        Xnueva =(1 - (Math.pow(aux + (uno * 1), 2) / (c(variable) * c(variable)))) * -1;

        if (DeterminarABElip(variable1, variable2)) {
             a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = a + dos;
            x2 = dos - a;
            y1 = tres;
            y2 = tres;
            b1 = tres + b;
            b2 = tres - b;
            mensaje =  "\n"
                    + "\n" + "La segunda en "+ letra +"= " + -aux + "\n" + "\n" + "Punto 1 ("+variable4+","+variable5+")"+ "en: " + "(" + x1 + " , " + y1
                    + ")  " + "\n" + "Punto 2 ("+variable4+","+variable5+") en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 ("+variable4+","+variable5+") en:  "
                    + "(" + dos + " , " + b1 + ")" + "\n" + "Punto 4 ("+variable4+","+variable5+") en:  " + "(" + dos + " , " + b2 + ")";
            ;
        } else {
             DeterminarABElip(variable2, variable1);
            a = Math.sqrt(a*a * Xnueva);
            b =  Math.sqrt(b*b * Xnueva);
            x1 = dos;
            x2 = dos;
            y1 = tres + a;
            y2 = tres - a;
            b1 = dos + b;
            b2 = dos - b;
            mensaje =  "\n"
                    + "\n" + "La segunda en " + letra +"= " + -aux + "\n" + "\n" + "Punto 1 ("+variable5+","+variable4+") en: " + "(" + y1 + " , " + x1
                    + ")  " + "\n" + "Punto 2 ("+variable5+","+variable4+") en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 ("+variable5+","+variable4+") en:  "
                    + "(" + tres + " , " + b1 + ")" + "\n" + "Punto 4 ("+variable5+","+variable4+") en:  " + "(" + tres + " , " + b2 + ")";

        }
        return mensaje;
    }
    public static String elipseH2X() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (h == 0) {
            aux = h + 5;
        }
        else{
        aux = h * 2;
        }
        Xnueva = (1 - (Math.pow(aux + (h * -1), 2) / (c(x) * c(x)))) * -1;
        if (DeterminarABElip(y, z)) {
             a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = Math.sqrt(a) + k;
            x2 = k - Math.sqrt(a);
            y1 = j;
            y2 = j;
            b1 = j + Math.sqrt(b);
            b2 = j - Math.sqrt(b);
            mensaje = "x toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en x= " + aux + "\n" + "\n" + "Punto 1 (y,z) en: " + "(" + x1 + " , "
                    + y1 + ")  " + "\n" + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n"
                    + "Punto 3 (y,z) en:  " + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "(" + k
                    + " , " + b2 + ")";
            ;
        } else {
            DeterminarABElip(z, y);
            a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = k;
            x2 = k;
            y1 = j + Math.sqrt(a);
            y2 = j - Math.sqrt(a);
            b1 = k + Math.sqrt(b);
            b2 = k - Math.sqrt(b);
            mensaje = "x toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en x= " + aux + "\n" + "\n" + "Punto 1 (z,y) en: " + "(" + y1 + " , "
                    + x1 + ")  " + "\n" + "Punto 2 (z,y) en:  " + "(" + y2 + " , " + x2 + ")" + "\n"
                    + "Punto 3 (z,y) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,y) en:  " + "(" + j
                    + " , " + b2 + ")";

        }
        return mensaje;

    }

    public static String elipseH2Y() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (k == 0) {
            aux = k + 5;
        }
        else{

        aux = k * 2;
        }
        Xnueva = (1 - (Math.pow(aux + (k * -1), 2) / (c(y) * c(y)))) * -1;

        if (DeterminarABElip(x, z)) {
            a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = Math.sqrt(a) + h;
            x2 = h - Math.sqrt(a);
            y1 = j;
            y2 = j;
            b1 = j + Math.sqrt(b);
            b2 = j - Math.sqrt(b);
            mensaje = "y toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en y= " + aux + "\n" + "\n" + "Punto 1 (x,z) en: " + "(" + x1 + " , "
                    + y1 + ")  " + "\n" + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n"
                    + "Punto 3 (x,z) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "(" + h
                    + " , " + b2 + ")";
            ;
        } else {
            DeterminarABElip(z, x);
            a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = h;
            x2 = h;
            y1 = j + Math.sqrt(a);
            y2 = j - Math.sqrt(a);
            b1 = h + Math.sqrt(b);
            b2 = h - Math.sqrt(b);
            mensaje = "y toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en y= " + aux + "\n" + "\n" + "Punto 1 (z,x) en: " + "(" + y1 + " , "
                    + x1 + ")  " + "\n" + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n"
                    + "Punto 3 (z,x) en:  " + "(" + j + " , " + b1 + ")" + "\n" + "Punto 4 (z,x) en:  " + "(" + j
                    + " , " + b2 + ")";

        }
        return mensaje;
    }

    public static String elipseZH2() {
        double aux;
        double Xnueva;
        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String mensaje = "";
        if (j == 0) {
            aux = j + 5;
        }
        else{

        aux = j * 2;
        }
        Xnueva = (1 - (Math.pow(aux + (j * -1), 2) / (c(z) * c(z)))) * -1;

        if (DeterminarABElip(x, y)) {
             a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = Math.sqrt(a) + h;
            x2 = h - Math.sqrt(a);
            y1 = k;
            y2 = k;
            b1 = k + Math.sqrt(b);
            b2 = k - Math.sqrt(b);
            mensaje = "z toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en z= " + aux + "\n" + "\n" + "Punto 1 (x,y) en: " + "(" + x1 + " , "
                    + y1 + ")  " + "\n" + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")" + "\n"
                    + "Punto 3 (x,y) en:  " + "(" + h + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "(" + h
                    + " , " + b2 + ")";
            ;
        } else {
            DeterminarABElip(y, x);
            a = a*a * Xnueva;
            b = b*b * Xnueva;
            x1 = h;
            x2 = h;
            y1 = k + Math.sqrt(a);
            y2 = k - Math.sqrt(a);
            b1 = h + Math.sqrt(b);
            b2 = h - Math.sqrt(b);
            mensaje = "z toma este valor, para que cuando se grafique se pueda apreciar bien la grafica y no se desborde"
                    + "\n" + "\n" + "La primera en z= " + aux + "\n" + "\n" + "Punto 1 (y,x) en: " + "(" + y1 + " , "
                    + x1 + ")  " + "\n" + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n"
                    + "Punto 3 (y,x) en:  " + "(" + k + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "(" + k
                    + " , " + b2 + ")" + "\n" + "\n";

        }
        return mensaje;
    }

    public static String trazaXZH2() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;
        String aux2 = "";

        if (((x.charAt(0) == '-') && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABHiper(z, x);
            aux2 = "                  XZ   ";

            x1 = h;
            x2 = h;
            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            y1 = j + a;
            y2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + b + " , " + j + ")";
            conjugado2 = "(" + -b + " , " + j + ")";
            mensaje = "la traza forma una hiperbole en y = " + k + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1
                    + " " + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n"
                    ;
            ;

        } else if (((z.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABHiper(x, z);
            aux2 = "                  XZ   ";
            x1 = h + a;
            x2 = h - a;
            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            y1 = j;
            y2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + j + ")";
            foco2 = "(" + (h - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + h + " , " + b + ")";
            conjugado2 = "(" + h + " , " + -b + ")";
            mensaje = "la traza forma una parabola en y= " + k + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (x.charAt(0) == '-' && z.charAt(0) == '-') {
            aux2 = "                  XY   ";
            mensaje = "la traza forma dos elipses" + "\n" + "\n" + elipseH2Y() +"\n" + "\n" + elipseH(x,"x","z",z,y,k,h,j,"y")+ "\n" + "\n";
        }

        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String trazaXYH2() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;
        String aux2 = " ";
        if (((x.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            aux2 = "                  XY   ";
            DeterminarABHiper(y, x);
            x1 = h;
            x2 = h;
            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (k + aux) + ")";
            foco2 = "(" + h + " , " + (k - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + b + " , " + k + ")";
            conjugado2 = "(" + -b + " , " + k + ")";
            mensaje = "la traza forma una hiperbole en z= " + j + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (((y.charAt(0) == '-') && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABHiper(x, y);
            aux2 = "                  XY   ";
            x1 = h + a;
            x2 = h - a;
            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + k + ")";
            foco2 = "(" + (h - aux) + " , " + k + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + h + " , " + b + ")";
            conjugado2 = "(" + h + " , " + -b + ")";
            mensaje = "la traza forma una hiperbole en z= " + j + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (x.charAt(0) == '-' && y.charAt(0) == '-') {
            aux2 = "                  XY   ";
            mensaje = "la traza forma dos elipses" + "\n" + "\n" + elipseZH2() +"\n" + "\n" + elipseH(x,"x","y",y,z,j,h,k,"z") + "\n" + "\n";
        }

        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String trazaYZH2() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        String aux2 = "  ";
        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        double aux;

        if (((y.charAt(0) == '-') && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            aux2 = "                  YZ   ";
            DeterminarABHiper(z, y);
            x1 = k;
            x2 = k;
            String centro = "centro (y,z)" + "\n" + "(" + k + " , " + j + ")";
            y1 = j + a;
            y2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + b + " , " + j + ")";
            conjugado2 = "(" + -b + " , " + j + ")";
            mensaje = "la traza forma una hiperbole en x = " + h + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1
                    + " " + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (((z.charAt(0) == '-') && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            aux2 = "                  YZ   ";
            DeterminarABHiper(y, z);
            x1 = k + a;
            x2 = k - a;
            String centro = "centro (x,z)" + "\n" + "(" + k + " , " + j + ")";
            y1 = j;
            y2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + k + " , " + b + ")";
            conjugado2 = "(" + k + " , " + -b + ")";
            mensaje = "la traza forma una hiperbole en x= " + h + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (z.charAt(0) == '-' && y.charAt(0) == '-') {
            aux2 = "                  YZ   ";
            mensaje = "la traza forma dos elipses" + "\n" + "\n" + elipseH2X() +"\n" + "\n" + elipseH(y,"y","z",z,x,h,k,j,"x") + "\n" + "\n";
        }
        return aux2 + "\n" + "\n" + mensaje;
    }

    public static String H2() {

        String mensaje = "         HIPERBOLOIDE DE DOS HOJAS           " + "\n" + "\n"
                + "Esta figura tiene 4 trazas, dos elipses, y dos hiperbolas" + "\n" + "\n" + "CENTRO: " + "(" + h
                + " , " + k + " , " + j + ")";
        return mensaje;
    }

    // Paraboloide elipticoooooooo
    public static boolean isPara1() {
        if (((x.charAt(0) == '-' && !isCuadrado(x) && !isdividir(x))
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((y.charAt(0) == '-' && !isCuadrado(y) && !isdividir(y))
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((z.charAt(0) == '-' && !isCuadrado(z) && !isdividir(z))
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPara2() {
        if (((x.charAt(0) == '=' && !isdividir(x) && !isCuadrado(x)) && x.charAt(1) != '-'
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((y.charAt(0) == '=' && !isdividir(y) && !isCuadrado(y)) && y.charAt(1) != '-'
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((z.charAt(0) == '=' && !isdividir(z) && !isCuadrado(z)) && z.charAt(1) != '-'
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {

            return true;

        }
        return false;
    }

    public static boolean isCuadrado(String variable) {
        if (variable.contains("^2")) {
            return true;
        }
        return false;
    }

    public static boolean isPara() {
        if (isPara1()) {
            return true;
        } else if (isPara2()) {
            return true;
        }
        return false;
    }

    public static String trazaXYPara() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = p(z);
        double punto = 0;
        double puntodos = 0;

        if (((x.charAt(0) == '-') || (x.charAt(0) == '=' && x.charAt(1) != '-')
                && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            DeterminarABPara(y, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = ((((a * 3) + (-k)) * ((a * 3) + (-k))) / dividendo) + h;
            puntodos = ((((-a * 3) +(-k)) * ((-a * 3) + (-k))) / dividendo) + h;
            mensaje = "  cuando z=" + j + "\n" + "La traza forma una parabola con un vertice en (x,y)" + "\n" + "(" + k
                    + " , " + h + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (((y.charAt(0) == '-') || (y.charAt(0) == '=' && y.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABPara(x, y);
            double dividendo = a * a * b;

            ecua = "y =  " + "((y + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = ((((a * 3) + (-h)) * ((a * 3) + (-h))) / dividendo) + k;
            puntodos = ((((-a * 3) + (-h)) * ((-a * 3) + (-h))) / dividendo) + k;
            mensaje = "  cuando z=" + j + "\n" + "La traza forma una parabola con un vertice en (y,x)" + "\n" + "(" + h
                    + " , " + k + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if ((((x.charAt(0) == '+') || x.charAt(0) == '(' || x.charAt(1) != 'x') && (y.charAt(0) == '+')
                || y.charAt(0) == '(' || y.charAt(1) != 'x')) {

            if (DeterminarABElip(x, y)) {
                Double aux;
                if (j != 0) {
                    aux = j+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a*a * c);
                    b = (b*a * c);
                }

                x1 = Math.sqrt(a) + h;
                x2 = h - Math.sqrt(a);
                y1 = k;
                y2 = k;
                b1 = k + Math.sqrt(b);
                b2 = k - Math.sqrt(b);

                mensaje = "La traza forma una elipse" + "\n" + "\n" + "     cuando z=  " + aux + "\n" + "\n" + "puntos:"
                        + "\n" + "\n" + "Punto 1 (x,y) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (x,y) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,y) en:  " + "(" + h
                        + " , " + b1 + ")" + "\n" + "Punto 4 (x,y) en:  " + "(" + h + " , " + b2 + ")" + "\n" + "\n";
            } else {
                DeterminarABElip(y, x);
                Double aux;
                if (j != 0) {
                     aux = j+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a*a * c);
                    b = (b*b * c);
                }
                x1 = h;
                x2 = h;
                y1 = k + Math.sqrt(a);
                y2 = k - Math.sqrt(a);
                b1 = h + Math.sqrt(b);
                b2 = h - Math.sqrt(b);
                mensaje = "La traza forma una elipse" + "\n" + "\n" + "     cuando z=  " + aux + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (y,x) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (y,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (y,x) en:  " + "(" + k
                        + " , " + b1 + ")" + "\n" + "Punto 4 (y,x) en:  " + "(" + k + " , " + b2 + ")" + "\n" + "\n";

            }
        }
        return "                  XY   " + "\n" + "\n" + mensaje;
    }

    public static String trazaXZPara() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = p(y);
        double punto = 0;
        double puntodos = 0;

        if (((x.charAt(0) == '-') || (x.charAt(0) == '=' && x.charAt(1) != '-')
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABPara(z, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "((z + " + (j * -1) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = ((((a * 3) + (j * -1)) * ((a * 3) + (j * -1))) / dividendo) + h;
            puntodos = ((((-a * 3) + (j * -1)) * ((-a * 3) + (j * -1))) / dividendo) + h;
            mensaje = "  cuando y=" + k + "\n" + "La traza forma una parabola con un vertice en (x,z)" + "\n" + "(" + j
                    + " , " + h + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,x):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (((z.charAt(0) == '-') || (z.charAt(0) == '=' && z.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABPara(x, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "((x + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = ((((a * 3) + (h * -1)) * ((a * 3) + (h * -1))) / dividendo) + j;
            puntodos = ((((-a * 3) + (h * -1)) * ((-a * 3) + (h * -1))) / dividendo) + j;
            mensaje = "  cuando y=" + k + "\n" + "La traza forma una parabola con un vertice en (z,x)" + "\n" + "(" + h
                    + " , " + j + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,x);" + "\n" + "punto 1:" + "(" + punto + " , " + (-a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if ((((x.charAt(0) == '+') || x.charAt(0) == '(' || x.charAt(1) != 'x') && (z.charAt(0) == '+')
                || z.charAt(0) == '(' || z.charAt(1) != 'x')) {

            if (DeterminarABElip(x, z)) {
                Double aux;
                if (k != 0) {
                   aux = k+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a*a * c);
                    b = (b*b * c);
                }
                x1 = Math.sqrt(a)+ h;
                x2 = h - Math.sqrt(a);
                y1 = j;
                y2 = j;
                b1 = j + Math.sqrt(b);
                b2 = j - Math.sqrt(b);
                mensaje = "La traza forma una elipse" + "\n" + "\n" + "     cuando y=  " + aux + "\n" + "\n" + "puntos:"
                        + "\n" + "\n" + "Punto 1 (x,z) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (x,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (x,z) en:  " + "(" + h
                        + " , " + b1 + ")" + "\n" + "Punto 4 (x,z) en:  " + "(" + h + " , " + b2 + ")" + "\n" + "\n";
            } else {
                DeterminarABElip(z, x);
                Double aux;
                if (k != 0) {
                    aux = k+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a * c);
                    b = (b * c);
                }
                x1 = h;
                x2 = h;
                y1 = j + Math.sqrt(a);
                y2 = j - Math.sqrt(a);
                b1 = h + Math.sqrt(b);
                b2 = h - Math.sqrt(b);
                mensaje = "La traza forma una elipses" + "\n" + "\n" + "     cuando y=  " + aux + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (z,x) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (z,x) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (z,x) en:  " + "(" + j
                        + " , " + b1 + ")" + "\n" + "Punto 4 (z,x) en:  " + "(" + j + " , " + b2 + ")" + "\n" + "\n";

            }
        }
        return "                  XZ   " + "\n" + "\n" + mensaje;
    }

    public static String trazaYZPara() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;
        double c = p(x);
        double punto = 0;
        double puntodos = 0;

        if (((y.charAt(0) == '-') || (y.charAt(0) == '=' && y.charAt(1) != '-')
                && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            DeterminarABPara(z, y);
            double dividendo = a * a * b;

            ecua = "y =  " + "((z + " + (-j) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = (((((a * 3) + (j * -1)) * ((a * 3) + (j * -1)))) / dividendo) + k;
            puntodos = (((((-a * 3) + (j * -1)) * ((-a * 3) + (j * -1)))) / dividendo) + k;
            mensaje = "  cuando x=" + h + "\n" + "La traza forma una parabola con un vertice en (z,y)" + "\n" + "(" + j
                    + " , " + k + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (y,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (((z.charAt(0) == '-') || (z.charAt(0) == '=' && z.charAt(1) != '-')
                && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))))) {
            DeterminarABPara(x, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            punto = (((((a * 3) + (k * -1)) * ((a * 3) + (k * -1)))) / dividendo) + j;
            puntodos = (((((-a * 3) + (k * -1)) * ((-a * 3) + (k * -1)))) / dividendo) + j;
            mensaje = "  cuando x=" + h + "\n" + "La traza forma una parabola con un vertice en (z,y)" + "\n" + "(" + k
                    + " , " + j + ")" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n" + ecua + "\n"
                    + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos(y,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if ((((y.charAt(0) == '+') || y.charAt(0) == '(' || y.charAt(1) != 'x') && (y.charAt(0) == '+')
                || z.charAt(0) == '(' || z.charAt(1) != 'x')) {

            if (DeterminarABElip(y, z)) {
                Double aux;
                if (h != 0) {
                    aux = h+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a *a* c);
                    b = (b *b* c);
                }
                x1 = Math.sqrt(a) + k;
                x2 = k - Math.sqrt(a);
                y1 = j;
                y2 = j;
                b1 = j + Math.sqrt(b);
                b2 = j - Math.sqrt(b);
                mensaje = "La traza forma una elipse" + "\n" + "\n" + "     cuando x=  " + aux + "\n" + "\n" + "puntos:"
                        + "\n" + "\n" + "Punto 1 (y,z) en: " + "(" + x1 + " , " + y1 + ")  " + "\n"
                        + "Punto 2 (y,z) en:  " + "(" + x2 + " , " + y2 + ")" + "\n" + "Punto 3 (y,z) en:  " + "(" + k
                        + " , " + b1 + ")" + "\n" + "Punto 4 (y,z) en:  " + "(" + k + " , " + b2 + ")" + "\n" + "\n";
            } else {
                DeterminarABElip(z, y);
                Double aux;
                if (h != 0) {
                    aux = h+1;
                    a = (a*a * c);
                    b = (b*b * c);
                } else {
                    aux = 1.0;
                    a = (a*a * c);
                    b = (b*b * c);
                }
                x1 = k;
                x2 = k;
                y1 = j + Math.sqrt(a);
                y2 = j - Math.sqrt(a);
                b1 = k + Math.sqrt(b);
                b2 = k - Math.sqrt(b);
                mensaje = "La traza forma una elipses" + "\n" + "\n" + "     cuando x=  " + aux + "\n" + "\n"
                        + "puntos:" + "\n" + "\n" + "Punto 1 (z,y) en: " + "(" + y1 + " , " + x1 + ")  " + "\n"
                        + "Punto 2 (z,y) en:  " + "(" + y2 + " , " + x2 + ")" + "\n" + "Punto 3 (z,y) en:  " + "(" + j
                        + " , " + b1 + ")" + "\n" + "Punto 4 (z,y) en:  " + "(" + j + " , " + b2 + ")" + "\n" + "\n";

            }
        }
        return "                  YZ   " + "\n" + "\n" + mensaje;
    }

    public static String paraboloideHeliptico() {
        String origen = "(" + h + ", " + k + ", " + j + ")";
        String mensaje = "         PARABOLOIDE HELIPTICO           " + "\n" + "\n"
                + "Esta figura tiene 3 trazas  1 elipse, y dos parabolas," + "\n" + "\n" + "origen:" + origen;
        return mensaje;
    }
    // hiperboloide hiperbolico

    public static boolean isSillaDeMontar() {
        if (((x.charAt(0) == '-' && !isCuadrado(x) && !isdividir(x))
                && ((y.charAt(0) == '-' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '-' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((y.charAt(0) == '-' && !isCuadrado(y) && !isdividir(y))
                && ((x.charAt(0) == '-' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '-' || z.charAt(0) == '(' || esXYZ(z, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        if (((z.charAt(0) == '-' && !isCuadrado(z) && !isdividir(z))
                && ((x.charAt(0) == '-' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '-' || y.charAt(0) == '(' || esXYZ(y, i))))) {
            if (ecuacion1.equals("=0")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSillaDeMontar2() {
        if (((x.charAt(0) == '=' && !isdividir(x) && !isCuadrado(x)) && x.charAt(1) != '-'
                && ((y.charAt(0) == '-' || y.charAt(0) == '(' || esXYZ(y, i))
                && (z.charAt(0) == '-' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((y.charAt(0) == '=' && !isdividir(y) && !isCuadrado(y)) && y.charAt(1) != '-'
                && ((x.charAt(0) == '-' || x.charAt(0) == '(' || esXYZ(x, i))
                && (z.charAt(0) == '-' || z.charAt(0) == '(' || esXYZ(z, i))))) {

            return true;

        }
        if (((z.charAt(0) == '=' && !isdividir(z) && !isCuadrado(z)) && z.charAt(1) != '-'
                && ((x.charAt(0) == '-' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.charAt(0) == '-' || y.charAt(0) == '(' || esXYZ(y, i))))) {

            return true;

        }
        return false;
    }

    public static boolean isSilla() {
        if (isSillaDeMontar()) {
            return true;
        } else if (isSillaDeMontar2()) {
            return true;
        }
        return false;
    }

    public static void DeterminarABSillaM(String variable1, String variable2, double c) {

        double n1 = Double.parseDouble(Sacardividendo(variable1, i));

        double n2 = Double.parseDouble(Sacardividendo(variable2, i));
        a = Math.sqrt(n1 * c);
        b = Math.sqrt(n2 * c);
    }

    public static String trazaXYSillaMontarPo() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double y1;
        double y2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";
        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        String mss1 = "";
        String mss2 = "";
        String mss3 = "";
        String mss4 = "";
        String mss5 = "";
        double aux;
        double prod = p(z);
        int modificar = 0;
        String aux2 = "                  XY   ";
        double zAux;
        double punto;
        double puntodos;
        double igual;
        if ((((x.charAt(0) == '-')) && (y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i)) && z.contains("*"))) {
            aux2 = "                  XY   ";
            DeterminarABSillaM(y, x, prod);
            if (j != 0) {
                zAux = (j + 1);
            } else {
                zAux = 1;
            }

            x1 = h;
            x2 = h;

            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (k + aux) + ")";
            foco2 = "(" + h + " , " + (k - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h + b) + " , " + k + ")";
            conjugado2 = "(" + (h - b) + " , " + k + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en z= " + zAux + "\n" + "\n" + "vertices (x,y)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
DeterminarABSillaM(y, x, prod);
            
           zAux += 4;
            igual = zAux;
            zAux = zAux + (j * -1);
            if (( zAux * prod)  > 0) {
                
                a = (Math.sqrt(a * a * zAux));
                b = (Math.sqrt((b * b * zAux)));
            } else {
                
                a = (Math.sqrt(b * b * zAux));
                b = (Math.sqrt((a * a * zAux)));
            }
            x1 = h ;
            x2 = h ;

            y1 = k+a;
            y2 = k-a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h ) + " , " + (k+aux) + ")";
            foco2 = "(" + (h ) + " , " + (k-aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h+b) + " , " + (k ) + ")";
            conjugado2 = "(" + (h-b) + " , " + (k ) + ")";

            mss2 = "la segunda Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
DeterminarABSillaM(y, x, prod);
           zAux = zAux + ((zAux * -1) - 1);
            igual = zAux;
            zAux = zAux + (j * -1);

             if ((zAux *prod)<0) {
                a = (Math.sqrt(((b * b) * (zAux) * -1)));
                b = (Math.sqrt(((a * a) * (zAux) * -1)));
                  x1 = h + a;
            x2 = h - a;

            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h+aux) + " , " + (k ) + ")";
            foco2 = "(" + (h-aux) + " , " + (k ) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h ) + " , " + (k+ b) + ")";
            conjugado2 = "(" + (h ) + " , " + (k- b) + ")";
                
               
            } else {
                  zAux=1;
                  igual=zAux;
                  zAux = zAux + (j * -1);
                a = (Math.sqrt((a * a) * zAux));
                b = (Math.sqrt(((b * b) * zAux)));
                 

            x1 = h;
            x2 = h;

            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            
            foco1 = "(" + (h ) + " , " + (k+ aux) + ")";
            foco2 = "(" + (h) + " , " + (k - aux) + ")";
            vertice1 = "(" + y1 + " , " + x1 + ")";
            vertice2 = "(" + y2 + " , " + x2 + ")";
            conjugado1 = "(" + (h+ b) + " , " + (k ) + ")";
            conjugado2 = "(" + (h - b) + " , " + (k) + ")";
            }

            mss3 = "la tercera Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
DeterminarABSillaM(y, x, prod);
           zAux = zAux + ((zAux * -1) - 5);
            igual = zAux;
            zAux = zAux + (j * -1);

            if ((zAux *prod)<0) {
                a = (Math.sqrt(((b * b) * (zAux) * -1)));
                b = (Math.sqrt(((a * a) * (zAux) * -1)));
                  x1 = h + a;
            x2 = h - a;

            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h+aux) + " , " + (k ) + ")";
            foco2 = "(" + (h-aux) + " , " + (k ) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h ) + " , " + (k+ b) + ")";
            conjugado2 = "(" + (h ) + " , " + (k- b) + ")";
                
               
            } else {
                  zAux=5;
                  igual=zAux;
                  zAux = zAux + (j * -1);
                a = (Math.sqrt((a * a) * zAux));
                b = (Math.sqrt(((b * b) * zAux)));
                 

            x1 = h;
            x2 = h;

            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            
            foco1 = "(" + (h ) + " , " + (k+ aux) + ")";
            foco2 = "(" + (h) + " , " + (k - aux) + ")";
            vertice1 = "(" + y1 + " , " + x1 + ")";
            vertice2 = "(" + y2 + " , " + x2 + ")";
            conjugado1 = "(" + (h+ b) + " , " + (k ) + ")";
            conjugado2 = "(" + (h - b) + " , " + (k) + ")";
            }
            mss4 = "la cuarta  Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
        } else if (((y.charAt(0) == '-') && (x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && z.contains("*"))) {
            DeterminarABSillaM(x, y, prod);
            if (j != 0) {
                zAux = (j + 1);
            } else {
                zAux = 1;
            }
            
            x1 = h + a;
            x2 = h - a;

            String centro = "centro (x,y)" + "\n" + "(" + h + " , " + k + ")";
            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (k) + ")";
            foco2 = "(" + (h - aux) + " , " + (k) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h) + " , " + (k + b) + ")";
            conjugado2 = "(" + (h) + " , " + (k - b) + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en z= " + zAux + "\n" + "\n" + "vertices (x,y)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(x, y, prod);
            zAux += 4;
            igual = zAux;
            zAux = zAux + (j * -1);
            if (( zAux * prod)  < 0) {
                
                a = (Math.sqrt((b * b) * zAux));
                b = (Math.sqrt(((a * a) * zAux)));
            } else {
                
                a = (Math.sqrt((a * a) * zAux));
                b = (Math.sqrt(((b * b) * zAux)));
            }
            x1 = h + a;
            x2 = h - a;

            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (k) + ")";
            foco2 = "(" + (h - aux) + " , " + (k) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h) + " , " + (k + b) + ")";
            conjugado2 = "(" + (h) + " , " + (k - b) + ")";
            mss2 = "la segunda Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(x, y, prod);
            zAux = zAux + ((zAux * -1) - 1);
            igual = zAux;
            zAux = zAux + (j * -1);

            if ((zAux *prod)<0) {
                a = (Math.sqrt(((b * b) * (zAux) * -1)));
                b = (Math.sqrt(((a * a) * (zAux) * -1)));
                 x1 = h;
            x2 = h;

            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h) + " , " + (k + aux) + ")";
            foco2 = "(" + (h) + " , " + (k - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h + b) + " , " + (k) + ")";
            conjugado2 = "(" + (h - b) + " , " + (k) + ")";
            } else {
                  zAux=1;
                  igual=zAux;
                  zAux = zAux + (j * -1);
                a = (Math.sqrt((a * a) * zAux));
                b = (Math.sqrt(((b * b) * zAux)));
                 x1 = h;
                 x2 = h;

            x1 = h + a;
            x2 = h - a;

            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (k) + ")";
            foco2 = "(" + (h - aux) + " , " + (k) + ")";
            vertice1 = "(" + y1 + " , " + x1 + ")";
            vertice2 = "(" + y2 + " , " + x2 + ")";
            conjugado1 = "(" + (h) + " , " + (k + b) + ")";
            conjugado2 = "(" + (h) + " , " + (k - b) + ")";
            }
          

            mss3 = "la tercera Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(x, y, prod);
            zAux = zAux - (j * -1) - 4;
            igual = zAux;
            zAux = zAux + (j * -1);
            if ((zAux *prod)<0) {
              
                a = (Math.sqrt(((b * b) * (zAux) * -1)));
                b = (Math.sqrt(((a * a) * (zAux) * -1)));
                 x1 = h;
            x2 = h;

            y1 = k + a;
            y2 = k - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h) + " , " + (k + aux) + ")";
            foco2 = "(" + (h) + " , " + (k - aux) + ")";
            vertice1 = "(" + x1 + " , " + y1 + ")";
            vertice2 = "(" + x2 + " , " + y2 + ")";
            conjugado1 = "(" + (h + b) + " , " + (k) + ")";
            conjugado2 = "(" + (h - b) + " , " + (k) + ")";
            } else {
                  zAux= 5;
                    igual=zAux;
                    zAux = zAux + (j * -1);
                a = (Math.sqrt((a * a) * zAux));
                b = (Math.sqrt(((b * b) * zAux)));
                 x1 = h;
                 x2 = h;

            x1 = h + a;
            x2 = h - a;

            y1 = k;
            y2 = k;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (k) + ")";
            foco2 = "(" + (h - aux) + " , " + (k) + ")";
            vertice1 = "(" + y1 + " , " + x1 + ")";
            vertice2 = "(" + y2 + " , " + x2 + ")";
            conjugado1 = "(" + (h) + " , " + (k + b) + ")";
            conjugado2 = "(" + (h) + " , " + (k - b) + ")";
            }

           

            mss4 = "la cuarta  Hiperbole en z= " + igual + "\n" + "\n" + "vertices (x,y)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,y)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,y)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (x.contains("*") && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i)))) {
            DeterminarABPara(y, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = ((((a * 3) + (k * -1)) * ((a * 3) + (k * -1))) / dividendo) + h;
            puntodos = ((((-a * 3) + (k * -1)) * ((-a * 3) + (k * -1))) / dividendo) + h;
            mensaje = "  cuando z=" + j + "\n" + "\n" + "La traza forma una parabola con un vertice en (y,x)" + "\n"
                    + "(" + k + " , " + h + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (y.contains("*") && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i)))) {
            DeterminarABPara(x, y);
            double dividendo = a * a * b;

            ecua = "y =  " + "((x + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = ((((a * 3) + h * -1) * ((a * 3) + (h * -1))) / dividendo) + k;
            puntodos = ((((-a * 3) + h * -1) * ((-a * 3) + (h * -1))) / dividendo) + k;
            mensaje = "  cuando z=" + j + "\n" + "\n" + "La traza forma una parabola con un vertice en (x,y)" + "\n"
                    + "(" + h + " , " + k + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (y,x):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (x.contains("*") && ((y.charAt(0) == '-'))) {
            DeterminarABPara(y, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "-((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = (((((a * 3) + (k * -1)) * ((a * 3) + (k * -1))) / dividendo) * -1) + h;
            puntodos = (((((-a * 3) + (k * -1)) * ((-a * 3) + (k * -1))) / dividendo) * -1) + h;
            mensaje = "  cuando z=" + j + "\n" + "\n" + "La traza forma una parabola con un vertice en (y,x)" + "\n"
                    + "(" + k + " , " + h + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (y.contains("*") && ((x.charAt(0) == '-'))) {
            DeterminarABPara(x, y);
            double dividendo = a * a * b;

            ecua = "y =  " + "-((x + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = (((((a * 3) + (h * -1)) * ((a * 3) + (h * -1))) * -1) / dividendo) + k;
            puntodos = (((((-a * 3) + (h * -1)) * ((-a * 3) + (h * -1))) * -1) / dividendo) + k;
            mensaje = "  cuando z=" + j + "\n" + "\n" + "La traza forma una parabola con un vertice en (x,y)" + "\n"
                    + "(" + h + " , " + k + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (y,x):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        }

        return aux2 + "\n" + "\n" + mensaje + "\n" + "\n" + mss1 + "\n" + mss2 + "\n" + mss3 + "\n" + mss4 + "\n";
    }

    public static String trazaXZSillaMontar() {

        String mensaje = "";

        String ecua = "";

        double x1;
        double x2;
        double z1;
        double z2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";

        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        String mss1 = "";
        String mss2 = "";
        String mss3 = "";
        String mss4 = "";
        String mss5 = "";
        double aux;
        double prod = p(y);
        int modificar = 0;
        String aux2 = "                  XZ   ";
        double yAux;
        double punto;
        double puntodos;
        double igual;
        if ((((x.charAt(0) == '-')) && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)) && y.contains("*"))) {
            aux2 = "                  XZ   ";
            DeterminarABSillaM(z, x, prod);
            if (k != 0) {
                yAux = (k + 1);
            } else {
                yAux = 1;
            }

            x1 = h;
            x2 = h;

            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + j + ")";
            conjugado2 = "(" + (h - b) + " , " + j + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en y= " + yAux + "\n" + "\n" + "vertices (x,z)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
 DeterminarABSillaM(z, x, prod);
            yAux += 4;
            igual = yAux;
            yAux = yAux + (k * -1);
            if (yAux < 0) {
                yAux *= -1;
            }
            if (yAux * prod > 0) {
                a = (Math.sqrt(((a * a) * (yAux) * -1)));
                b = (Math.sqrt(((b * b) * (yAux) * -1)));
            } else {
                a = (Math.sqrt((b * b) * yAux));
                b = (Math.sqrt(((a * a) * yAux)));
            }
            x1 = h;
            x2 = h;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + j + ")";
            conjugado2 = "(" + (h - b) + " , " + j + ")";

            mss2 = "la segunda Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(z, x, prod);
            yAux = yAux + ((yAux * -1) - 1);
            igual = yAux;
            yAux = yAux + (k * -1);
            if (yAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (yAux) * -1)));
                b = (Math.sqrt(((a * a) * (yAux) * -1)));
                 x1 = h + a;
            x2 = h - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + j + ")";
            foco2 = "(" + (h - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + h + " , " + (j + b) + ")";
            conjugado2 = "(" + h + " , " + (j - b) + ")";
            } else {
                yAux=1;
                igual=yAux;
                 yAux = yAux + (k * -1);
                a = (Math.sqrt((b * b) * yAux));
                b = (Math.sqrt(((a * a) * yAux)));
                x1 = h;
            x2 = h;

            z1 = j + a;
            z2 = j - a;
            
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + j + ")";
            conjugado2 = "(" + (h - b) + " , " + j + ")";
            }

            
            mss3 = "la tercera Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
 DeterminarABSillaM(z, x, prod);
            yAux = yAux + ((yAux * -1) - 1);
            igual = yAux;
            yAux = yAux + (k * -1);
            if (yAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (yAux) * -1)));
                b = (Math.sqrt(((a * a) * (yAux) * -1)));
                 x1 = h + a;
            x2 = h - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + j + ")";
            foco2 = "(" + (h - aux) + " , " + j + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + h + " , " + (j + b) + ")";
            conjugado2 = "(" + h + " , " + (j - b) + ")";
            } else {
                yAux=5;
                igual=yAux;
                 yAux = yAux + (k * -1);
                a = (Math.sqrt((b * b) * yAux));
                b = (Math.sqrt(((a * a) * yAux)));
                x1 = h;
            x2 = h;

            z1 = j + a;
            z2 = j - a;
             
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + h + " , " + (j + aux) + ")";
            foco2 = "(" + h + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + j + ")";
            conjugado2 = "(" + (h - b) + " , " + j + ")";
            }

            mss4 = "la cuarta  Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
        } else if (((z.charAt(0) == '-') && (x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && y.contains("*"))) {
            DeterminarABSillaM(x, z, prod);
            if (k != 0) {
                yAux = (k + 1);
            } else {
                yAux = 1;
            }

            x1 = h + a;
            x2 = h - a;

            String centro = "centro (x,z)" + "\n" + "(" + h + " , " + j + ")";
            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (j) + ")";
            foco2 = "(" + (h - aux) + " , " + (j) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h) + " , " + (j + b) + ")";
            conjugado2 = "(" + (h) + " , " + (j - b) + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en y= " + yAux + "\n" + "\n" + "vertices (x,z)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
           DeterminarABSillaM(x, z, prod);
            yAux += 4;
            igual = yAux;
            yAux = yAux + (k * -1);
            if (yAux*prod < 0) {
                a = (Math.sqrt((b * b) * -yAux));
                b = (Math.sqrt(((a * a) * -yAux)));
            } else {
                a = (Math.sqrt((a * a) * yAux));
                b = (Math.sqrt(((b * b) * yAux)));
            }
            x1 = h + a;
            x2 = h - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (j) + ")";
            foco2 = "(" + (h - aux) + " , " + (j) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h) + " , " + (j + b) + ")";
            conjugado2 = "(" + (h) + " , " + (j - b) + ")";
            mss2 = "la segunda Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
           DeterminarABSillaM(x, z, prod);
            yAux = yAux + ((yAux * -1) - 1);
            igual = yAux;
            yAux = yAux + (k * -1);

            if (yAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (yAux) * -1)));
                b = (Math.sqrt(((a * a) * (yAux) * -1)));
                 x1 = h;
            x2 = h;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h) + " , " + (j + aux) + ")";
            foco2 = "(" + (h) + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + (j) + ")";
            conjugado2 = "(" + (h - b) + " , " + (j) + ")";
            } else {
                a = (Math.sqrt((a * a) * yAux));
                b = (Math.sqrt(((b * b) * yAux)));
                 yAux=1;
                 igual=yAux;
                 yAux = yAux + (k * -1);
                x1 = h + a;
            x2 = h - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (j) + ")";
            foco2 = "(" + (h - aux) + " , " + (j) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h) + " , " + (j + b) + ")";
            conjugado2 = "(" + (h) + " , " + (j - b) + ")";
            }
            

            mss3 = "la tercera Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
             DeterminarABSillaM(x, z, prod);
            yAux = yAux + ((yAux * -1) - 4);
            igual = yAux;
            yAux = yAux + (k * -1);

            if (yAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (yAux) * -1)));
                b = (Math.sqrt(((a * a) * (yAux) * -1)));
                 x1 = h;
            x2 = h;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h) + " , " + (j + aux) + ")";
            foco2 = "(" + (h) + " , " + (j - aux) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h + b) + " , " + (j) + ")";
            conjugado2 = "(" + (h - b) + " , " + (j) + ")";
            } else {
                a = (Math.sqrt((a * a) * yAux));
                b = (Math.sqrt(((b * b) * yAux)));
                 yAux=5;
                 igual=yAux;
                 yAux = yAux + (k * -1);
                x1 = h + a;
            x2 = h - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (h + aux) + " , " + (j) + ")";
            foco2 = "(" + (h - aux) + " , " + (j) + ")";
            vertice1 = "(" + x1 + " , " + z1 + ")";
            vertice2 = "(" + x2 + " , " + z2 + ")";
            conjugado1 = "(" + (h) + " , " + (j + b) + ")";
            conjugado2 = "(" + (h) + " , " + (j - b) + ")";
            }

            mss4 = "la cuarta  Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (x.contains("*") && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)))) {
            DeterminarABPara(z, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "((z + " + (-j) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = ((((a * 3) + (j * -1)) * ((a * 3) + (j * -1))) / dividendo) + h;
            puntodos = ((((-a * 3) + (j * -1)) * ((-j * 3) + (j * -1))) / dividendo) + h;
            mensaje = "  cuando y=" + k + "\n" + "\n" + "La traza forma una parabola con un vertice en (z,x)" + "\n"
                    + "(" + j + " , " + h + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (z.contains("*") && ((x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i)))) {
            DeterminarABPara(x, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "((x + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            punto = ((((a * 3) + h * -1) * ((a * 3) + (h * -1))) / dividendo) + j;
            puntodos = ((((-a * 3) + h * -1) * ((-a * 3) + (h * -1))) / dividendo) + j;
            mensaje = "  cuando y=" + k + "\n" + "\n" + "La traza forma una parabola con un vertice en (x,z)" + "\n"
                    + "(" + h + " , " + j + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,x):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (x.contains("*") && ((z.charAt(0) == '-'))) {
            DeterminarABPara(z, x);
            double dividendo = a * a * b;

            ecua = "x =  " + "-((z + " + (-j) + ")^2) / " + dividendo + ")  + " + "(" + h + ")";
            punto = (((((a * 3) + (j * -1)) * ((a * 3) + (j * -1))) / dividendo) * -1) + h;
            puntodos = (((((-a * 3) + (j * -1)) * ((-a * 3) + (j * -1))) / dividendo) * -1) + h;
            mensaje = "  cuando y=" + k + "\n" + "\n" + "La traza forma una parabola con un vertice en (z,x)" + "\n"
                    + "(" + j + " , " + h + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (x,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (z.contains("*") && ((x.charAt(0) == '-'))) {
            DeterminarABPara(x, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "-((x + " + (-h) + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            punto = (((((a * 3) + (h * -1)) * ((a * 3) + (h * -1))) * -1) / dividendo) + j;
            puntodos = (((((-a * 3) + (h * -1)) * ((-a * 3) + (h * -1))) * -1) / dividendo) + j;
            mensaje = "  cuando y=" + k + "\n" + "\n" + "La traza forma una parabola con un vertice en (x,z)" + "\n"
                    + "(" + h + " , " + j + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a x que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,x):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        }

        return aux2 + "\n" + "\n" + mensaje + "\n" + "\n" + mss1 + "\n" + mss2 + "\n" + mss3 + "\n" + mss4 + "\n";
    }

    public static String trazaYZSillaMontar() {

        String mensaje = "";

        String ecua = "";

        double y1;
        double y2;
        double z1;
        double z2;
        double b1;
        double b2;

        String vertice1 = "";
        String vertice2 = "";

        String foco1 = "";
        String foco2 = "";
        String conjugado1 = "";
        String conjugado2 = "";
        String mss1 = "";
        String mss2 = "";
        String mss3 = "";
        String mss4 = "";
        String mss5 = "";
        double aux;
        double prod = p(x);
        int modificar = 0;
        String aux2 = "                  YZ   ";
        double xAux;
        double punto;
        double puntodos;
        double igual;
        if ((((y.charAt(0) == '-')) && (z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)) && x.contains("*"))) {
            aux2 = "                  YZ   ";
            DeterminarABSillaM(z, y, prod);
            if (h != 0) {
                xAux = (h + 1);
            } else {
                xAux = 1;
            }

            y1 = k;

            y2 = k;

            String centro = "centro (y,z)" + "\n" + "(" + k + " , " + j + ")";
            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k + b) + " , " + j + ")";
            conjugado2 = "(" + (k - b) + " , " + j + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en x= " + xAux + "\n" + "\n" + "vertices (y,z)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
 DeterminarABSillaM(z, y, prod);
            xAux += 4;
            igual = xAux;
            xAux = xAux + (h * -1);
           
            if (xAux*prod > 0) {
                a = (Math.sqrt(((a * a) * (xAux) * -1)));
                b = (Math.sqrt(((b * b) * (xAux) * -1)));
            } else {
                a = (Math.sqrt((b * b) * xAux));
                b = (Math.sqrt(((a * a) * xAux)));
            }
            y1 = k;
            y2 = k;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k + b) + " , " + j + ")";
            conjugado2 = "(" + (k - b) + " , " + j + ")";

            mss2 = "la segunda Hiperbole en x= " + igual + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
 DeterminarABSillaM(z, y, prod);
            xAux = xAux + ((xAux * -1) - 1);
            igual = xAux;
            xAux = xAux + (h * -1);
            if (xAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (xAux) * -1)));
                b = (Math.sqrt(((a * a) * (xAux) * -1)));
                 y1 = k + a;
            y2 = k - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";
            } else {
             xAux=1;
                  igual=xAux;
                  xAux = xAux + (h * -1);
                
                a = (Math.sqrt((b * b) * xAux));
                b = (Math.sqrt(((a * a) * xAux)));
                
                y1 = k;
            y2 = k;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k + b) + " , " + j + ")";
            conjugado2 = "(" + (k - b) + " , " + j + ")";
            }

           

            mss3 = "la tercera Hiperbole en x= " + igual + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
 DeterminarABSillaM(z, y, prod);
            xAux = xAux + ((xAux * -1) - 1);
            igual = xAux;
            xAux = xAux + (h * -1);
            if (xAux*prod < 0) {
                a = (Math.sqrt(((b * b) * (xAux) * -1)));
                b = (Math.sqrt(((a * a) * (xAux) * -1)));
                 y1 = k + a;
            y2 = k - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";
            } else {
             xAux=5;
                  igual=xAux;
                  xAux = xAux + (h * -1);
                
                a = (Math.sqrt((b * b) * xAux));
                b = (Math.sqrt(((a * a) * xAux)));
                
                y1 = k;
            y2 = k;

            z1 = j + a;
            z2 = j - a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + k + " , " + (j + aux) + ")";
            foco2 = "(" + k + " , " + (j - aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k + b) + " , " + j + ")";
            conjugado2 = "(" + (k - b) + " , " + j + ")";
            }

            mss4 = "la cuarta  Hiperbole en y= " + igual + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
        } else if (((z.charAt(0) == '-') && (x.charAt(0) == '+' || x.charAt(0) == '(' || esXYZ(x, i))
                && (y.contains("*")))) {
            DeterminarABSillaM(y, z, prod);
            if (h != 0) {
                xAux = (h + 1);
            } else {
                xAux = 1;
            }

            y1 = k + a;
            y2 = k - a;

            String centro = "centro (y,z)" + "\n" + "(" + k + " , " + j + ")";
            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";

            mss1 = "La traza forma varias hiperboles, para una mejor compresion de la figura, acontinuacion se mostran 4 de estas:"
                    + "\n" + "\n" + "la primera Hiperbole en x= " + xAux + "\n" + "\n" + "vertices (y,z)" + "\n"
                    + vertice1 + " " + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(y, z, prod);
            xAux += 4;
            igual = xAux;
            xAux = xAux + (k * -1);
            if (xAux*prod  < 0) {
                a = (Math.sqrt((b * b) * -xAux));
                b = (Math.sqrt(((a * a) * -xAux)));
            } else {
                a = (Math.sqrt((a * a) * xAux));
                b = (Math.sqrt(((b * b) * xAux)));
            }
            y1 = k + a;
            y2 = k - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";
            mss2 = "la segunda Hiperbole en x= " + igual + "\n" + "\n" + "vertices (y,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (y,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (y,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
           DeterminarABSillaM(y, z, prod);
            xAux = xAux + ((xAux * -1) - 1);
            igual = xAux;
            xAux = xAux + (h * -1);

            if (xAux < 0) {
                a = (Math.sqrt(((b * b) * (xAux) * -1)));
                b = (Math.sqrt(((a * a) * (xAux) * -1)));
                y1 = k ;
            y2 = k ;

            z1 = j+ a;
            z2 = j-a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k ) + " , " + (j+aux) + ")";
            foco2 = "(" + (k) + " , " + (j-aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k+ b) + " , " + (j ) + ")";
            conjugado2 = "(" + (k- b) + " , " + (j ) + ")";
            } else {
                 xAux=1;
                  igual=xAux;
                  xAux = xAux + (j * -1);
                
                a = (Math.sqrt((b * b) * xAux));
                b = (Math.sqrt(((a * a) * xAux)));
                 y1 = k + a;
            y2 = k - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";
            }
            

            mss3 = "la tercera Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";
            DeterminarABSillaM(y, z, prod);
            xAux = xAux + ((xAux * -1) - 1);
            igual = xAux;
            xAux = xAux + (h * -1);

            if (xAux < 0) {
                a = (Math.sqrt(((b * b) * (xAux) * -1)));
                b = (Math.sqrt(((a * a) * (xAux) * -1)));
                y1 = k ;
            y2 = k ;

            z1 = j+ a;
            z2 = j-a;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k ) + " , " + (j+aux) + ")";
            foco2 = "(" + (k) + " , " + (j-aux) + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + (k+ b) + " , " + (j ) + ")";
            conjugado2 = "(" + (k- b) + " , " + (j ) + ")";
            } else {
                 xAux=5;
                  igual=xAux;
                  xAux = xAux + (j * -1);
                
                a = (Math.sqrt((b * b) * xAux));
                b = (Math.sqrt(((a * a) * xAux)));
                 y1 = k + a;
            y2 = k - a;

            z1 = j;
            z2 = j;
            aux = Math.sqrt((a * a) + (b * b));
            foco1 = "(" + (k + aux) + " , " + j + ")";
            foco2 = "(" + (k - aux) + " , " + j + ")";
            vertice1 = "(" + y1 + " , " + z1 + ")";
            vertice2 = "(" + y2 + " , " + z2 + ")";
            conjugado1 = "(" + k + " , " + (j + b) + ")";
            conjugado2 = "(" + k + " , " + (j - b) + ")";
            }

            mss4 = "la cuarta  Hiperbole en y= " + igual + "\n" + "\n" + "vertices (x,z)" + "\n" + vertice1 + " "
                    + vertice2 + "\n" + "\n" + "focos (x,z)" + "\n" + foco1 + " " + foco2 + "\n" + "\n"
                    + "eje conjugado (x,z)" + "\n" + conjugado1 + conjugado2 + "\n" + "\n" + centro + "\n" + "\n";

        } else if (y.contains("*") && ((z.charAt(0) == '+' || z.charAt(0) == '(' || esXYZ(z, i)))) {
            DeterminarABPara(z, y);
            double dividendo = a * a * b;

            ecua = "y =  " + "((z + " + (-j) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = ((((a * 3) + (j * -1)) * ((a * 3) + (j * -1))) / dividendo) + k;
            puntodos = ((((-a * 3) + (j * -1)) * ((-j * 3) + (j * -1))) / dividendo) + k;
            mensaje = "  cuando x=" + h + "\n" + "\n" + "La traza forma una parabola con un vertice en (z,y)" + "\n"
                    + "(" + j + " , " + k + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (y,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (z.contains("*") && ((y.charAt(0) == '+' || y.charAt(0) == '(' || esXYZ(y, i)))) {
            DeterminarABPara(y, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            punto = ((((a * 3) + k * -1) * ((a * 3) + (k * -1))) / dividendo) + j;
            puntodos = ((((-a * 3) + k * -1) * ((-a * 3) + (k * -1))) / dividendo) + j;
            mensaje = "  cuando x=" + h + "\n" + "\n" + "La traza forma una parabola con un vertice en (y,z)" + "\n"
                    + "(" + k + " , " + j + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (y.contains("*") && ((z.charAt(0) == '-'))) {
            DeterminarABPara(z, x);
            double dividendo = a * a * b;

            ecua = "y =  " + "-((z + " + (-j) + ")^2) / " + dividendo + ")  + " + "(" + k + ")";
            punto = (((((a * 3) + (j * -1)) * ((a * 3) + (j * -1))) / dividendo) * -1) + k;
            puntodos = (((((-a * 3) + (j * -1)) * ((-a * 3) + (j * -1))) / dividendo) * -1) + k;
            mensaje = "  cuando x=" + h + "\n" + "\n" + "La traza forma una parabola con un vertice en (z,y)" + "\n"
                    + "(" + j + " , " + k + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a z que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (y,z):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        } else if (z.contains("*") && ((y.charAt(0) == '-'))) {
            DeterminarABPara(x, z);
            double dividendo = a * a * b;

            ecua = "z =  " + "-((y + " + (-k) + ")^2) / " + dividendo + ")  + " + "(" + j + ")";
            punto = (((((a * 3) + (k * -1)) * ((a * 3) + (k * -1))) * -1) / dividendo) + j;
            puntodos = (((((-a * 3) + (k * -1)) * ((-a * 3) + (k * -1))) * -1) / dividendo) + j;
            mensaje = "  cuando x=" + h + "\n" + "\n" + "La traza forma una parabola con un vertice en (y,z)" + "\n"
                    + "(" + k + " , " + j + ")" + "\n" + "\n" + "la ecuacion que describe la parabola es:" + "\n" + "\n"
                    + ecua + "\n" + "\n"
                    + "para una mejor compresion de la traza se le dan dos valores a y que permitan ver la forma de la parabola"
                    + "\n" + "\n" + "Puntos (z,y):" + "\n" + "punto 1:" + "(" + punto + " , " + (a * 3) + ")" + "\n"
                    + "punto 2: (" + puntodos + " , " + (-a * 3) + ")";
        }

        return aux2 + "\n" + "\n" + mensaje + "\n" + "\n" + mss1 + "\n" + mss2 + "\n" + mss3 + "\n" + mss4 + "\n";
    }

    public static String Silla() {
        String centro = "(" + h + ", " + k + ", " + j + ")";
        String mensaje = "         PARABOLOIDE HIPERBOLICO (SILLA DE DE MONTAR D:)          " + "\n" + "\n"
                + "Esta figura esta conformada por muchas hiperboles y 2 parabolas" + "\n" + "\n" + "CENTRO:" + centro;

        return mensaje;
    }

}
