package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import proyectofinal.Aplicacion;
import proyectofinal.domain.*;

public class PrincipalController implements Initializable {

    private Aplicacion app;

    // -----------------------------ESCUDOS OCTAVOS DE
    // FINAL--------------------------//
    @FXML
    private ImageView imgOctavos1;

    @FXML
    private ImageView imgOctavos2;

    @FXML
    private ImageView imgOctavos3;

    @FXML
    private ImageView imgOctavos4;

    @FXML
    private ImageView imgOctavos5;

    @FXML
    private ImageView imgOctavos6;

    @FXML
    private ImageView imgOctavos7;

    @FXML
    private ImageView imgOctavos8;

    @FXML
    private ImageView imgOctavos9;

    @FXML
    private ImageView imgOctavos10;

    @FXML
    private ImageView imgOctavos11;

    @FXML
    private ImageView imgOctavos12;

    @FXML
    private ImageView imgOctavos13;

    @FXML
    private ImageView imgOctavos14;

    @FXML
    private ImageView imgOctavos15;

    @FXML
    private ImageView imgOctavos16;

    // -----------------------------ESCUDOS CUARTOS DE
    // FINAL--------------------------//
    @FXML
    private ImageView imgCuartos1;

    @FXML
    private ImageView imgCuartos2;

    @FXML
    private ImageView imgCuartos3;

    @FXML
    private ImageView imgCuartos4;

    @FXML
    private ImageView imgCuartos5;

    @FXML
    private ImageView imgCuartos6;

    @FXML
    private ImageView imgCuartos7;

    @FXML
    private ImageView imgCuartos8;

    // -----------------------------ESCUDOS SEMIFINALES--------------------------//
    @FXML
    private ImageView imgSemis1;

    @FXML
    private ImageView imgSemis2;

    @FXML
    private ImageView imgSemis3;

    @FXML
    private ImageView imgSemis4;

    // -----------------------------ESCUDOS FINAL--------------------------//
    @FXML
    private ImageView imgFinal1;

    @FXML
    private ImageView imgFinal2;

    @FXML
    private ImageView imgGanador;

    @FXML
    private Button btnSesion;

    @FXML
    private Button btnRegistro;

    @FXML
    private Button btnSimular;

    @FXML
    private Button btnApuesta;

    @FXML
    private Button btnHistorial;

    @FXML
    private Label labelSaldo;

    private boolean apuestaActiva = false;

    public void simularFase() {

        Alert mensaje = new Alert(AlertType.ERROR);
        if (Aplicacion.usuarioActivo != null) {
            if (apuestaActiva) {
                app.cambiarVentana(Aplicacion.TITULO_MINUTO_A_MINUTO, Aplicacion.RUTA_MINUTO_A_MINUTO);
            } else {
                mensaje.setContentText("Debes realizar una apuesta antes de simular una fase");
                mensaje.showAndWait();
            }
        } else {
            mensaje.setContentText("Debes inciar sesión");
            mensaje.showAndWait();
        }
    }

    public void abrirLogin() {
        app.cambiarVentana(Aplicacion.TITULO_LOGIN, Aplicacion.RUTA_LOGIN);
    }

    public void abrirRegistro() {
        app.cambiarVentana(Aplicacion.TITULO_REGISTRO, Aplicacion.RUTA_REGISTRO);
    }

    public void abrirHistorialApuestas() {
        if (Aplicacion.usuarioActivo == null) {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setContentText("Debes iniciar sesión para apostar");
            mensaje.showAndWait();
        } else {
            app.cambiarVentana(Aplicacion.TITULO_HISTORIAL_APUESTAS, Aplicacion.RUTA_HISTORIAL_APUESTAS);
        }
    }

    public void abrirInfoEquipos() {
        app.cambiarVentana(Aplicacion.TITULO_INFO_EQUIPO, Aplicacion.RUTA_INFO_EQUIPO);
    }

    public void abrirApuesta() {
        if (Aplicacion.usuarioActivo == null) {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setContentText("Debes iniciar sesión para apostar");
            mensaje.showAndWait();
        } else {
            app.cambiarVentana(Aplicacion.TITULO_APUESTA, Aplicacion.RUTA_APUESTA);
        }
    }

    public void abrirResumen(){
        app.cambiarVentana(Aplicacion.TITULO_RESUMEN, Aplicacion.RUTA_ARBOL);
    }

    public void cerrarSesion() {
        Aplicacion.usuarioActivo = null;
        Alert mensaje;
        if (Aplicacion.usuarioActivo == null) {
            mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setContentText("Se ha cerrado la sesión");
            btnSesion.setText("Iniciar Sesión");
            btnSesion.setStyle("-fx-pref-width: 150;");
            btnSesion.setOnAction(event -> {
                abrirLogin();
            });
            btnRegistro.setVisible(true);
            btnRegistro.setManaged(true);
            labelSaldo.setVisible(false);
        } else {
            mensaje = new Alert(AlertType.ERROR);
            mensaje.setContentText("No se ha cerrado la sesión");
        }
        mensaje.showAndWait();
    }

    public void mostrarEquipos() {
        switch (Aplicacion.torneo.indexFase) {
            case 1:
                llenarOctavos();
                break;

            case 2:
                llenarOctavos();
                llenarCuartos();
                break;

            case 3:
                llenarOctavos();
                llenarCuartos();
                llenarSemis();
                break;

            case 4:
                llenarOctavos();
                llenarCuartos();
                llenarSemis();
                llenarFinal();
                break;

            case 5:
                llenarOctavos();
                llenarCuartos();
                llenarSemis();
                llenarFinal();
                imgGanador.setImage(new Image(
                        getClass().getResourceAsStream(Aplicacion.torneo.ganador.getEscudo())));
                break;
        }
    }

    public void llenarOctavos() {
        System.out.println("Octavos=" + Aplicacion.torneo.octavos);
        imgOctavos1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(0).getEquipo1().getEscudo())));
        imgOctavos2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(0).getEquipo2().getEscudo())));

        imgOctavos3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(1).getEquipo1().getEscudo())));
        imgOctavos4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(1).getEquipo2().getEscudo())));

        imgOctavos5.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(2).getEquipo1().getEscudo())));
        imgOctavos6.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(2).getEquipo2().getEscudo())));

        imgOctavos7.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(3).getEquipo1().getEscudo())));
        imgOctavos8.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(3).getEquipo2().getEscudo())));

        imgOctavos9.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(4).getEquipo1().getEscudo())));
        imgOctavos10.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(4).getEquipo2().getEscudo())));

        imgOctavos11.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(5).getEquipo1().getEscudo())));
        imgOctavos12.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(5).getEquipo2().getEscudo())));

        imgOctavos13.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(6).getEquipo1().getEscudo())));
        imgOctavos14.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(6).getEquipo2().getEscudo())));

        imgOctavos15.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(7).getEquipo1().getEscudo())));
        imgOctavos16.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(7).getEquipo2().getEscudo())));
    }

    public void llenarCuartos() {
        imgCuartos1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(0).getEquipo1().getEscudo())));
        imgCuartos2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(0).getEquipo2().getEscudo())));

        imgCuartos3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(1).getEquipo1().getEscudo())));
        imgCuartos4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(1).getEquipo2().getEscudo())));

        imgCuartos5.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(2).getEquipo1().getEscudo())));
        imgCuartos6.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(2).getEquipo2().getEscudo())));

        imgCuartos7.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(3).getEquipo1().getEscudo())));
        imgCuartos8.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(3).getEquipo2().getEscudo())));
    }

    public void llenarSemis() {
        imgSemis1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(0).getEquipo1().getEscudo())));
        imgSemis2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(0).getEquipo2().getEscudo())));

        imgSemis3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(1).getEquipo1().getEscudo())));
        imgSemis4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(1).getEquipo2().getEscudo())));
    }

    public void llenarFinal() {
        imgFinal1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.finales.get(0).getEquipo1().getEscudo())));
        imgFinal2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.finales.get(0).getEquipo2().getEscudo())));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
        Alert mensaje;
        if (Aplicacion.usuarioActivo != null) {// Hay un usuario activo
            btnSesion.setText("Cerrar sesión (" + ((Usuario) Aplicacion.usuarioActivo).getNombre() + ")");
            btnSesion.setStyle("-fx-pref-width: -1;");
            btnSesion.setOnAction(event -> {
                cerrarSesion();
            });// click
            btnRegistro.setVisible(false);
            btnRegistro.setManaged(false);
            labelSaldo.setText(
                    "Saldo disponible: $" + String.format("%.2f", ((Usuario) Aplicacion.usuarioActivo).getSaldo()));
            labelSaldo.setVisible(true);

            for (Apuesta apuesta : ((Usuario) Aplicacion.usuarioActivo).getHistorialApuestas()) {
                if (apuesta.getEstado().equals(EstadoApuesta.COLOCADA)) {
                    apuesta.setEstado(EstadoApuesta.ACTIVA);
                }
                if (apuesta.getEstado().equals(EstadoApuesta.ACTIVA)) {
                    apuestaActiva = true;
                }
            }

            if (Aplicacion.torneo.indexFase == 5) {
                mensaje = new Alert(AlertType.INFORMATION);
                if (((Usuario) Aplicacion.usuarioActivo).getSaldo() > Aplicacion.torneo.saldoIni) {
                    mensaje.setContentText("Ganaste :)");
                } else {
                    mensaje.setContentText("Perdiste :(");
                }
                mensaje.showAndWait();
                btnApuesta.setText("Ver resumen del torneo");
                btnApuesta.setOnAction(event -> {
                    abrirResumen();
                });
                btnSimular.setDisable(true);
                btnSesion.setDisable(true);
                btnRegistro.setDisable(true);
                Torneo.arbol = new Arbol(Aplicacion.torneo.ganador);
                Torneo.arbol.crearArbol(1, Torneo.arbol.getRaiz());
                Torneo.arbol.mostrarArbol("", Torneo.arbol.getRaiz());
            } else if (!apuestaActiva) {
                if (((Usuario) Aplicacion.usuarioActivo).getSaldo() < 100) {
                    cerrarSesion();

                    mensaje = new Alert(AlertType.INFORMATION);
                    mensaje.setContentText("Perdiste :(");
                    mensaje.showAndWait();
                }
            }
        } else { // Asignar "expresiones lambda"
            btnSesion.setText("Iniciar Sesión");
            btnSesion.setOnAction(event -> {
                abrirLogin();
            });
            labelSaldo.setVisible(false);
        }
        mostrarEquipos();
    }
}
