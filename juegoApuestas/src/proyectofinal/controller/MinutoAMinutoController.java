package proyectofinal.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import proyectofinal.Aplicacion;
import proyectofinal.domain.*;
import proyectofinal.hilos.Hilo;

public class MinutoAMinutoController implements Initializable {

    private Aplicacion app;

    @FXML
    private ToggleGroup equipos;

    @FXML
    private Label labelEquipos;

    @FXML
    public static Text txtInfo;

    @FXML
    public static Text txtMinuto;

    @FXML
    public static Text txtMarcador;

    @FXML
    public TextArea txtResumen;

    @FXML
    private VBox paneBotones;

    @FXML
    private VBox paneResumen;

    @FXML
    private VBox paneInfo;

    @FXML
    private VBox paneNoti;

    @FXML
    private Button btRegresar;

    private Equipo equipoSelect;

    @FXML
    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    public void inicializarCampos() {
        deshabilitarBtn();
        equipos.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {// Propiedad
                    public void changed(ObservableValue<? extends Toggle> ov,
                            final Toggle toggle, final Toggle new_toggle) {
                        if (new_toggle != null) {
                            equipoSelect = Aplicacion.torneo
                                    .buscarEquipo(new Equipo("", ((ToggleButton) new_toggle).getText(), null, 0, ""));
                            String nombres = "";

                            for (int i = 0; i < equipos.getToggles().size(); i++) {
                                ((ToggleButton) equipos.getToggles().get(i)).setDisable(true);
                            }

                            Partido partido = null;

                            ArrayList<Partido> nuevaFase = new ArrayList<>();
                            Partido nuevoPartido = new Partido(null, null, new ArrayList<>());

                            ArrayList<Partido> fase = Aplicacion.torneo.obtenerFase();

                            for (Partido partidoAux : fase) {
                                
                                if (nuevoPartido.getEquipo1() == null) {
                                    nuevoPartido.setEquipo1(partidoAux.determinarGanador());
                                    if (Aplicacion.torneo.indexFase == 4) {
                                        Aplicacion.torneo.ganador = nuevoPartido.getEquipo1();
                                    }
                                } else if (nuevoPartido.getEquipo2() == null) {
                                    nuevoPartido.setEquipo2(partidoAux.determinarGanador());
                                    nuevaFase.add(nuevoPartido);
                                    nuevoPartido = new Partido(null, null, new ArrayList<>());
                                }

                                if (partidoAux.getEquipo1().getCodigo().equals(equipoSelect.getCodigo())
                                        || partidoAux.getEquipo2().getCodigo().equals(equipoSelect.getCodigo())) {
                                    partido = partidoAux;
                                }
                                partidoAux.generarEventos();
                            }

                            switch (Aplicacion.torneo.indexFase) {
                                case 1:
                                    Aplicacion.torneo.cuartos.addAll(nuevaFase);
                                    break;

                                case 2:
                                    Aplicacion.torneo.semis.addAll(nuevaFase);
                                    break;

                                case 3:
                                    Aplicacion.torneo.finales.addAll(nuevaFase);
                                    break;
                            }

                            Aplicacion.torneo.indexFase += 1;

                            //partido.generarEventos();

                            nombres += partido.getEquipo1().getNombre() + " - " + partido.getEquipo2().getNombre();

                            labelEquipos.setText(nombres);

                            txtMarcador.setText("0 - 0");

                            for (Apuesta apuesta : ((Usuario) Aplicacion.usuarioActivo).getHistorialApuestas()) {
                                if (apuesta.getEstado().equals(EstadoApuesta.ACTIVA)) {
                                    apuesta.setEstado(EstadoApuesta.FINALIZADA);
                                    if (Aplicacion.torneo.indexFase == 5) {
                                        if (apuesta.getEquipo().getCodigo()
                                                .equals(Aplicacion.torneo.ganador.getCodigo())) {
                                            apuesta.setGanado(true);
                                            ((Usuario) Aplicacion.usuarioActivo).modificarSaldo(apuesta);
                                        }
                                    }
                                    for (Partido partidoAux : nuevaFase) {
                                        if ((apuesta.getEquipo().getCodigo()
                                                .equals(partidoAux.getEquipo1().getCodigo()))
                                                || (apuesta.getEquipo().getCodigo()
                                                        .equals(partidoAux.getEquipo2().getCodigo()))) {
                                            apuesta.setGanado(true);
                                            ((Usuario) Aplicacion.usuarioActivo).modificarSaldo(apuesta);
                                        }
                                    }
                                }
                            }

                            // Iniciar Hilo...
                            Hilo hilo = new Hilo(partido, txtResumen, btRegresar);
                            hilo.start();

                        } else {
                            labelEquipos.setText("...");
                            txtInfo.setText("...");
                            txtMinuto.setText("...");
                        }
                    }
                });

    }

    public void deshabilitarBtn() {
        boolean disable;
        ArrayList<Partido> fase = Aplicacion.torneo.obtenerFase();
        for (Toggle toggle : equipos.getToggles()) {// La fase dentro del arreglo de botones
            disable = true;
            String boton = ((ToggleButton) toggle).getText();
            for (Partido partido : fase) {
                if (partido.getEquipo1().getCodigo().equals(boton) || partido.getEquipo2().getCodigo().equals(boton)) {
                    disable = false;
                }
            }
            ((ToggleButton) toggle).setDisable(disable);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
        txtInfo = new Text();
        txtMarcador = new Text();
        txtMinuto = new Text();

        txtMinuto.getStyleClass().addAll("texto", "subtitulo");
        txtInfo.getStyleClass().addAll("texto", "subtitulo");
        txtMarcador.getStyleClass().addAll("texto", "subtitulo");

        paneInfo.getChildren().add(0, txtMinuto);
        paneInfo.getChildren().add(txtMarcador);
        paneNoti.getChildren().add(txtInfo);
        inicializarCampos();
    }
}
