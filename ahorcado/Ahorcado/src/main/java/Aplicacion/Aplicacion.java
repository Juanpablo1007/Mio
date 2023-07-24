/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import Interfaz.AhorcadoGUI;
import Interfaz.InicioGUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author USUARIO
 */
public class Aplicacion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InicioGUI inicio = new InicioGUI();
                inicio.setVisible(true);
            }
        });
    }
}
