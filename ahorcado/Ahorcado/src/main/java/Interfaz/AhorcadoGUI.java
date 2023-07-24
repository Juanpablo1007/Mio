/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import Model.Historial;
import Model.JuegoAhorcado;
import Model.Jugador;
import Model.puntaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class AhorcadoGUI extends JFrame {

    private JuegoAhorcado juego;
    private JFrame frame;
    private JLabel palabraLabel;
    private JLabel intentosLabel;
    private JLabel puntajeLabel;
    private JLabel descripcionLabel;
    private JTextField letraTextField;
    private JButton intentarButton;
    private ImageIcon imagenActual;
    private ImageIcon imagenOriginal;
    private JLabel imagenLabel;
    
    private puntaje puntos = new puntaje();
    public Jugador player = new Jugador();
    private static Historial h = new Historial();
;
    public void iniciar() {
        juego = new JuegoAhorcado(this);
        crearInterfaz();
    }

    private void crearInterfaz() {
        frame = new JFrame("Juego de Ahorcado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Crear panel con color de fondo
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\preguntas.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panelPrincipal.setLayout(null);

        frame.getContentPane().add(panelPrincipal);

        palabraLabel = new JLabel(juego.estadoActual(), SwingConstants.CENTER);
        palabraLabel.setFont(new Font("Arial", Font.BOLD, 28));
        
       palabraLabel.setForeground(Color.YELLOW);
        palabraLabel.setBounds(10, 10, 380, 40);
        panelPrincipal.add(palabraLabel);
        

        descripcionLabel = new JLabel(juego.getDescripcion(), SwingConstants.CENTER);
       descripcionLabel.setFont(new Font("Arial", Font.BOLD, 15));

        descripcionLabel.setForeground(Color.YELLOW);
        descripcionLabel.setBounds(10, 60, 380, 20);
        panelPrincipal.add(descripcionLabel);

        puntajeLabel = new JLabel("Puntaje: " + puntos.getPuntos(), SwingConstants.RIGHT);
        puntajeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        puntajeLabel.setForeground(Color.WHITE);
        puntajeLabel.setBounds(220, 90, 170, 20);
        panelPrincipal.add(puntajeLabel);

        intentosLabel = new JLabel("Intentos restantes: " + juego.getIntentosRestantes(), SwingConstants.CENTER);
       intentosLabel.setFont(new Font("Arial", Font.BOLD, 12));
       intentosLabel.setPreferredSize(new Dimension(200, 20));
        intentosLabel.setForeground(Color.WHITE);
        intentosLabel.setBounds(140, 120, 120, 20);
        panelPrincipal.add(intentosLabel);

        letraTextField = new JTextField(1);
        letraTextField.setHorizontalAlignment(JTextField.CENTER);
        letraTextField.setFont(new Font("Arial", Font.PLAIN, 16));
       letraTextField.setForeground(Color.RED);
        letraTextField.setBounds(140, 150, 40, 30);
        panelPrincipal.add(letraTextField);

        intentarButton = new JButton("Intentar");
        intentarButton.setBounds(190, 150, 80, 30);
        intentarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String letraStr = letraTextField.getText().toUpperCase();
                if (letraStr.length() == 1) {
                    char letra = letraStr.charAt(0);
                    boolean adivino = juego.intentar(letra);

                    if (adivino) {

                    } else {
                        JOptionPane.showMessageDialog(frame, "Letra incorrecta. ¡Intenta de nuevo!");
                    }

                    actualizarInterfaz();

                    if (juego.gano()) {

                        JOptionPane.showMessageDialog(frame, "¡Felicidades! Ganaste el juego.");

                        reiniciarJuego();
                    } else if (juego.perdio()) {
                        JOptionPane.showMessageDialog(frame, "Lo siento, has perdido el juego. La palabra secreta era: " + juego.getPalabraSecreta());
                        reiniciarJuego();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingresa una sola letra.");
                }

                letraTextField.setText("");
                letraTextField.requestFocus();
            }
        });
        panelPrincipal.add(intentarButton);

        try {
            imagenOriginal = new ImageIcon("C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\6.png");
            Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            imagenActual = new ImageIcon(imagenRedimensionada);
            imagenLabel = new JLabel(imagenActual);
            imagenLabel.setBounds(110, 190, 180, 180);
            panelPrincipal.add(imagenLabel);
        } catch (Exception e) {
            System.out.println("hubo un error");
        }

        JButton terminarJuegoButton = new JButton("INICIO");
        terminarJuegoButton.setPreferredSize(new Dimension(300, 30));
        terminarJuegoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAInicio();
            }
        });
        
        terminarJuegoButton.setBounds(150, 380, 120, 30);
        panelPrincipal.add(terminarJuegoButton);

        frame.setSize(400, 460);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actualizarImagen(int intentosRestantes) {
        String rutaImagen = obtenerRutaImagen(intentosRestantes);
        imagenOriginal = new ImageIcon(rutaImagen);
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        imagenActual = new ImageIcon(imagenRedimensionada);

        imagenLabel.setIcon(imagenActual);
        imagenLabel.repaint();
    }

    private String obtenerRutaImagen(int intentosRestantes) {
        switch (intentosRestantes) {
            case 5:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\6.png";
            case 4:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\5.png";
            case 3:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\4.png";
            case 2:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\3.png";
            case 1:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\2.png";
            default:
                return "C:\\Users\\USUARIO\\OneDrive\\Desktop\\ahorcado\\1.png";
        }
    }

    private void actualizarInterfaz() {
        String estadoActual = juego.estadoActual();
        palabraLabel.setText(estadoActual);
        puntos.setPuntos(juego.getScore());
        if (estadoActual.equalsIgnoreCase(juego.getPalabraSecreta())) {
            JOptionPane.showMessageDialog(frame, "¡Has ganado! La palabra era: " + juego.getPalabraSecreta());
            reiniciarJuego();
        } else if (juego.perdio()) {
            JOptionPane.showMessageDialog(frame, "¡Has perdido! La palabra era: " + juego.getPalabraSecreta());
            reiniciarJuego();
        }

        descripcionLabel.setText(juego.getDescripcion()); // Agregar descripción de la palabra
        puntajeLabel.setText("Puntaje: " + puntos.getPuntos());
        intentosLabel.setText("Intentos restantes: " + juego.getIntentosRestantes());

        // Actualizar la palabra oculta con las letras adivinadas
        StringBuilder palabraOculta = new StringBuilder();
        for (int i = 0; i < juego.getPalabraSecreta().length(); i++) {
            char letra = juego.getPalabraSecreta().charAt(i);
            if (juego.getLetrasAdivinadas().contains(Character.toLowerCase(letra))) {
                palabraOculta.append(letra);
            } else {
                palabraOculta.append("_");
            }
            palabraOculta.append(" ");
        }
        palabraLabel.setText(palabraOculta.toString().trim());

        actualizarImagen(juego.getIntentosRestantes());
    }

    private void reiniciarJuego() {
        juego = new JuegoAhorcado(this); // Crea una nueva instancia de JuegoAhorcado
        descripcionLabel.setText(juego.getDescripcion());
        juego.setScore(puntos.getPuntos());
        actualizarInterfaz();
    }

    private void volverAInicio() {
        frame.dispose(); // Cerrar la interfaz actual
        InicioGUI inicio = new InicioGUI(); // Crear una instancia de InicioGUI
        player.setPuntaje(puntos.getPuntos());
        player.setTiempo(sacarTiempo ());
        h.getLista().add(player);
        inicio.setVisible(true); // Abrir la interfaz InicioGUI
    }

    public static Historial historia() {
        return h;
    }
public String sacarTiempo (){
    // Obtener la hora actual
        LocalTime horaActual = LocalTime.now();

        // Formatear la hora actual con minutos y segundos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       return "" + horaActual.format(formatter);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AhorcadoGUI juegoAhorcado = new AhorcadoGUI();
                juegoAhorcado.iniciar();
            }
        });
    }
}
