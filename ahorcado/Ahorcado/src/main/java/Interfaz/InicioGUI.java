/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioGUI extends JFrame {

    private JTextField nombreTextField;
    private JButton iniciarButton;
    private JButton verHistorialButton;

    private HistorialJugadoresGUI historialJugadoresGUI; // Nueva instancia de HistorialJugadoresGUI

    public InicioGUI() {
        super("Juego del Ahorcado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Crear un panel con fondo de imagen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\fondo.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JLabel tituloLabel = new JLabel("Juego del Ahorcado");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 32));
        tituloLabel.setForeground(Color.RED);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(tituloLabel);

        JLabel mensajeLabel = new JLabel("Ingresa tu nombre de usuario:");
        mensajeLabel.setForeground(Color.YELLOW);
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(mensajeLabel);

        nombreTextField = new JTextField();
        nombreTextField.setPreferredSize(new Dimension(100, 30)); // Establecer el ancho máximo del campo de texto
        nombreTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nombreTextField);
        panel.add(Box.createVerticalStrut(20));
        iniciarButton = new JButton("Iniciar Juego");
        iniciarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombreTextField.getText();

                AhorcadoGUI juegoAhorcado = new AhorcadoGUI();
                juegoAhorcado.player.setNombre(nombreUsuario);
                juegoAhorcado.iniciar();

                dispose();
            }
        });
        panel.add(iniciarButton);
panel.add(Box.createVerticalStrut(10));
        verHistorialButton = new JButton("Ver Historial");
        verHistorialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        verHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (historialJugadoresGUI == null) {
                    historialJugadoresGUI = new HistorialJugadoresGUI();
                } else {
                    historialJugadoresGUI.setVisible(true);
                }
            }
        });
        panel.add(verHistorialButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
