package proyectofinal;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.swing.text.Position;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proyectofinal.bd.EquipoDAO;
import proyectofinal.domain.*;

public class Aplicacion extends Application {

    public static Object usuarioActivo;
    //public static Arbol arbol;

    public static final Stage primaryStage = new Stage();

    public static final String RUTA_INICIO = "view/Principal.fxml";
    public static final String RUTA_LOGIN = "view/Login.fxml";
    public static final String RUTA_REGISTRO = "view/Registro.fxml";
    public static final String RUTA_APUESTA = "view/Apuesta.fxml";
    public static final String RUTA_INFO_EQUIPO = "view/InfoEquipo.fxml";
    public static final String RUTA_HISTORIAL_APUESTAS = "view/HistorialApuestas.fxml";
    public static final String RUTA_MINUTO_A_MINUTO = "view/MinutoAMinuto.fxml";
    public static final String RUTA_ARBOL = "view/Arbol.fxml";
    public static final String RUTA_RESUMEN = "view/Resumen.fxml";

    public static final String TITULO_INICIO = "Inicio";
    public static final String TITULO_LOGIN = "Login";
    public static final String TITULO_REGISTRO = "Registro";
    public static final String TITULO_APUESTA = "Apuesta";
    public static final String TITULO_INFO_EQUIPO = "Información equipo";
    public static final String TITULO_HISTORIAL_APUESTAS = "Historial de Apuestas";
    public static final String TITULO_MINUTO_A_MINUTO = "Minuto a minuto";
    public static final String TITULO_RESUMEN = "Resumen";

    public static MediaPlayer musica;
    public static MediaPlayer ambiente;

    public static final Torneo torneo = new Torneo();

    public EquipoDAO equipoDAO;

    @Override
    public void start(Stage stage) {
        try {
            equipoDAO = new EquipoDAO();
            cargarBD();

            Media championsLeague = new Media(this.getClass().getResource("design/music/Champions League Anthem.mp3").toExternalForm());
            Media ojitosLindos = new Media(this.getClass().getResource("design/music/Ojitos Lindos.mp3").toExternalForm());
            Media belixe = new Media(this.getClass().getResource("design/music/Belixe.mp3").toExternalForm());
            Media loveMeAgain = new Media(this.getClass().getResource("design/music/Love Me Again.mp3").toExternalForm());

            ObservableList<Media> mediaList = FXCollections.observableArrayList();
            mediaList.addAll(championsLeague, ojitosLindos, belixe, loveMeAgain);
            
            music(mediaList);

            Parent root = FXMLLoader.load(getClass().getResource(RUTA_INICIO));
            Scene scene = new Scene(root);

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION);

                    guardarBD();

                    if (Aplicacion.usuarioActivo != null) {
                        Aplicacion.usuarioActivo = null;

                        mensaje.setTitle("Mensaje");
                        mensaje.setHeaderText("Sesión cerrada");
                        mensaje.setContentText("La sesión en curso se ha cerrado");
                        mensaje.showAndWait();
                    }

                    mensaje.setHeaderText("Hasta luego :)");
                    mensaje.setContentText("Gracias por jugar");
                    mensaje.showAndWait();
                }
            });
            primaryStage.setTitle(Aplicacion.TITULO_INICIO);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.getIcons().add(new Image("proyectofinal/design/Logo2.png"));
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cambiarVentana(String titulo, String ruta) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Scene scene;

            if (primaryStage.isMaximized()) {
                double ancho = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth();
                double alto = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
                scene = new Scene(root, ancho, alto);
            } else {
                scene = new Scene(root, 1280, 720);
            }
            primaryStage.setScene(scene);
            primaryStage.setTitle(titulo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void playMediaTracks(ObservableList<Media> mediaList, ObservableList<Media> newList) {
        Random random = new Random();
        if (mediaList.size() == 0)
            mediaList.addAll(newList);

        Media cancion = mediaList.remove(random.nextInt(mediaList.size()));
        newList.add(cancion);
        musica = new MediaPlayer(cancion);
        musica.setVolume(0.6);
        musica.play();

        musica.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                playMediaTracks(mediaList, newList);
            }
        });
    }

    public void music(ObservableList<Media> mediaList) {
        int count = Integer.MAX_VALUE;
        String file = "src/proyectofinal/design/music/Stadium_effect.mp3";
        Media h = new Media(Paths.get(file).toUri().toString());
        ambiente = new MediaPlayer(h);
        ambiente.setCycleCount(count);

        ObservableList<Media> newList = FXCollections.observableArrayList();
        playMediaTracks(mediaList, newList);
    }

    private void cargarBD() {

        Aplicacion.torneo.cargarUsuarios();
        Aplicacion.torneo.cargarEquipos();

        Aplicacion.torneo.generarOctavos();
    }

    private void guardarBD() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
