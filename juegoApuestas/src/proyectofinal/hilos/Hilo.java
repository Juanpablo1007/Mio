package proyectofinal.hilos;

import java.nio.file.Paths;

import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import proyectofinal.Aplicacion;
import proyectofinal.controller.MinutoAMinutoController;
import proyectofinal.domain.*;

public class Hilo extends Thread {

    private Partido partido;
    private TextArea txtResumen;
    private Button btRegresar;

    public Hilo(Partido partido, TextArea txtResumen, Button btRegresar) {
        this.partido = partido;
        this.txtResumen = txtResumen;
        this.btRegresar = btRegresar;
    }

    @Override
    public void run() {
        Aplicacion.musica.setVolume(0.3);
        Aplicacion.ambiente.play();
        String file;
        MediaPlayer efecto;
        Media h;

        btRegresar.setDisable(true);
        int minuto = 0;
        String marcador = "0 - 0";
        int goles1 = 0;
        int goles2 = 0;
        while (minuto <= 90) {
            try {
                MinutoAMinutoController.txtMinuto.setText(minuto + "'");
                for (int i = 0; i < partido.getEventos().size(); i++) {
                    Evento evento = partido.getEventos().get(i);
                    if (partido.getEventos().get(i).getMinuto() == minuto) {
                        file = "src/proyectofinal/design/music/Referee_whistle_effect.mp3";
                        h = new Media(Paths.get(file).toUri().toString());
                        efecto = new MediaPlayer(h);
                        efecto.play();
                        String nomEquipo = evento.getEquipo().getNombre();
                        MinutoAMinutoController.txtInfo
                                .setText(minuto + "' " + evento.getTipoEvento().getNombre() + " (" + nomEquipo + ")");

                        this.txtResumen.appendText(
                                "\n" + minuto + "' " + evento.getTipoEvento().getNombre() + " (" + nomEquipo + ")");

                        if (evento.getTipoEvento().equals(TipoEvento.GOL)) {
                            if (evento.getEquipo().getCodigo().equals(partido.getEquipo1().getCodigo())) {
                                marcador = ++goles1 + " - " + goles2;
                            } else {
                                marcador = goles1 + " - " + ++goles2;
                            }
                            MinutoAMinutoController.txtMarcador.setText(marcador);
                            file = "src/proyectofinal/design/music/Goal_effect.mp3";
                            h = new Media(Paths.get(file).toUri().toString());
                            efecto = new MediaPlayer(h);
                            efecto.play();
                        }
                    }
                }
                minuto++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btRegresar.setDisable(false);
        Aplicacion.ambiente.stop();
        Aplicacion.musica.setVolume(0.6);
    }
}