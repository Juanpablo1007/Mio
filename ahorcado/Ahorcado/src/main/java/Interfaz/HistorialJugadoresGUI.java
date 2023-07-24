/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Model.Historial;
import Model.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class HistorialJugadoresGUI extends JFrame {
    private JTable tablaHistorial;
    private Historial h = AhorcadoGUI.historia() ;
    private List<Jugador> jugadores = h.getLista();

    public HistorialJugadoresGUI() {
        setTitle("Historial de Jugadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        tablaHistorial = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        getContentPane().add(scrollPane);

        cargarDatosHistorial(jugadores);
    }

    private void cargarDatosHistorial(List<Jugador> jugadores) {
        String[] columnNames = {"Jugador", "Puntaje - Tiempo" };

        // Crear una matriz para almacenar los datos de los jugadores
        String[][] data = new String[jugadores.size()][2];

        // Llenar la matriz con los datos de los jugadores
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            data[i][0] = jugador.getNombre();
            data[i][1] = "Puntaje:" + jugador.getPuntaje() + "    |    Tiempo:"+ jugador.getTiempo();
        }

        // Crear un modelo de tabla y asignar los datos y nombres de columna
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Asignar el modelo de tabla a la tablaHistorial
        tablaHistorial.setModel(model);
    }

    public static void main(String[] args) {
        // Crear una lista de jugadores con datos de ejemplo
        List<Jugador> jugadores = new ArrayList<>(
                Arrays.asList(
                        new Jugador("Jugador1", 100,""),
                        new Jugador("Jugador2", 200,""),
                        new Jugador("Jugador3", 150,""),
                        new Jugador("Jugador4", 50,"")
                )
        );

        SwingUtilities.invokeLater(() -> {
            HistorialJugadoresGUI historialGUI = new HistorialJugadoresGUI();
            historialGUI.setVisible(true);
        });
    }
}


