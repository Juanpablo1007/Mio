package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import proyectofinal.Aplicacion;
import proyectofinal.domain.Equipo;
import proyectofinal.domain.Partido;

public class ArbolController implements Initializable {

    private Aplicacion app;

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

    @FXML
    private ImageView imgFinal1;

    @FXML
    private ImageView imgFinal2;

    @FXML
    private ImageView imgGanador;

    @FXML
    private ImageView imgOctavos1;

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
    private ImageView imgSemis1;

    @FXML
    private ImageView imgSemis2;

    @FXML
    private ImageView imgSemis3;

    @FXML
    private ImageView imgSemis4;

    @FXML
    private Label labelCuartos1;

    @FXML
    private Label labelCuartos2;

    @FXML
    private Label labelCuartos3;

    @FXML
    private Label labelCuartos4;

    @FXML
    private Label labelCuartos5;

    @FXML
    private Label labelCuartos6;

    @FXML
    private Label labelCuartos7;

    @FXML
    private Label labelCuartos8;

    @FXML
    private Label labelFinal1;

    @FXML
    private Label labelFinal2;

    @FXML
    private Label labelGanador;

    @FXML
    private Label labelOctavos1;

    @FXML
    private Label labelOctavos10;

    @FXML
    private Label labelOctavos11;

    @FXML
    private Label labelOctavos12;

    @FXML
    private Label labelOctavos13;

    @FXML
    private Label labelOctavos14;

    @FXML
    private Label labelOctavos15;

    @FXML
    private Label labelOctavos16;

    @FXML
    private Label labelOctavos2;

    @FXML
    private Label labelOctavos3;

    @FXML
    private Label labelOctavos4;

    @FXML
    private Label labelOctavos5;

    @FXML
    private Label labelOctavos6;

    @FXML
    private Label labelOctavos7;

    @FXML
    private Label labelOctavos8;

    @FXML
    private Label labelOctavos9;

    @FXML
    private Label labelSemis1;

    @FXML
    private Label labelSemis2;

    @FXML
    private Label labelSemis3;

    @FXML
    private Label labelSemis4;

    public static Partido partidoSelect;

    public void mostrarEquipos() {
        llenarOctavos();
        llenarCuartos();
        llenarSemis();
        llenarFinal();
        imgGanador.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.ganador.getEscudo())));
        labelGanador.setText(Aplicacion.torneo.ganador.getCodigo() + ", 0");
    }

    public void llenarOctavos() {
        System.out.println("Octavos=" + Aplicacion.torneo.octavos);
        imgOctavos1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(0).getEquipo1().getEscudo())));
        labelOctavos1.setText(String.valueOf(Aplicacion.torneo.octavos.get(0).getCodigo()));
        System.out.println("Partido codigo =" + String.valueOf(Aplicacion.torneo.semis.get(0).getCodigo()));
        imgOctavos2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(0).getEquipo2().getEscudo())));
        labelOctavos2.setText(String.valueOf(Aplicacion.torneo.octavos.get(0).getCodigo()));

        imgOctavos3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(1).getEquipo1().getEscudo())));
        labelOctavos3.setText(String.valueOf(Aplicacion.torneo.octavos.get(1).getCodigo()));
        imgOctavos4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(1).getEquipo2().getEscudo())));
        labelOctavos4.setText(String.valueOf(Aplicacion.torneo.octavos.get(1).getCodigo()));

        imgOctavos5.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(2).getEquipo1().getEscudo())));
        labelOctavos5.setText(String.valueOf(Aplicacion.torneo.octavos.get(2).getCodigo()));
        imgOctavos6.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(2).getEquipo2().getEscudo())));
        labelOctavos6.setText(String.valueOf(Aplicacion.torneo.octavos.get(2).getCodigo()));

        imgOctavos7.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(3).getEquipo1().getEscudo())));
        labelOctavos7.setText(String.valueOf(Aplicacion.torneo.octavos.get(3).getCodigo()));
        imgOctavos8.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(3).getEquipo2().getEscudo())));
        labelOctavos8.setText(String.valueOf(Aplicacion.torneo.octavos.get(3).getCodigo()));

        imgOctavos9.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(4).getEquipo1().getEscudo())));
        labelOctavos9.setText(String.valueOf(Aplicacion.torneo.octavos.get(4).getCodigo()));
        imgOctavos10.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(4).getEquipo2().getEscudo())));
        labelOctavos10.setText(String.valueOf(Aplicacion.torneo.octavos.get(4).getCodigo()));

        imgOctavos11.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(5).getEquipo1().getEscudo())));
        labelOctavos11.setText(String.valueOf(Aplicacion.torneo.octavos.get(5).getCodigo()));
        imgOctavos12.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(5).getEquipo2().getEscudo())));
        labelOctavos12.setText(String.valueOf(Aplicacion.torneo.octavos.get(5).getCodigo()));

        imgOctavos13.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(6).getEquipo1().getEscudo())));
        labelOctavos13.setText(String.valueOf(Aplicacion.torneo.octavos.get(6).getCodigo()));
        imgOctavos14.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(6).getEquipo2().getEscudo())));
        labelOctavos14.setText(String.valueOf(Aplicacion.torneo.octavos.get(6).getCodigo()));

        imgOctavos15.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(7).getEquipo1().getEscudo())));
        labelOctavos15.setText(String.valueOf(Aplicacion.torneo.octavos.get(7).getCodigo()));
        imgOctavos16.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.octavos.get(7).getEquipo2().getEscudo())));
        labelOctavos16.setText(String.valueOf(Aplicacion.torneo.octavos.get(7).getCodigo()));
    }

    public void llenarCuartos() {
        imgCuartos1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(0).getEquipo1().getEscudo())));
        labelCuartos1.setText(String.valueOf(Aplicacion.torneo.cuartos.get(0).getCodigo()));
        imgCuartos2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(0).getEquipo2().getEscudo())));
        labelCuartos2.setText(String.valueOf(Aplicacion.torneo.cuartos.get(0).getCodigo()));

        imgCuartos3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(1).getEquipo1().getEscudo())));
        labelCuartos3.setText(String.valueOf(Aplicacion.torneo.cuartos.get(1).getCodigo()));
        imgCuartos4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(1).getEquipo2().getEscudo())));
        labelCuartos4.setText(String.valueOf(Aplicacion.torneo.cuartos.get(1).getCodigo()));

        imgCuartos5.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(2).getEquipo1().getEscudo())));
        labelCuartos5.setText(String.valueOf(Aplicacion.torneo.cuartos.get(2).getCodigo()));
        imgCuartos6.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(2).getEquipo2().getEscudo())));
        labelCuartos6.setText(String.valueOf(Aplicacion.torneo.cuartos.get(2).getCodigo()));

        imgCuartos7.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(3).getEquipo1().getEscudo())));
        labelCuartos7.setText(String.valueOf(Aplicacion.torneo.cuartos.get(3).getCodigo()));
        imgCuartos8.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.cuartos.get(3).getEquipo2().getEscudo())));
        labelCuartos8.setText(String.valueOf(Aplicacion.torneo.cuartos.get(3).getCodigo()));
    }

    public void llenarSemis() {
        imgSemis1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(0).getEquipo1().getEscudo())));
        labelSemis1.setText(String.valueOf(Aplicacion.torneo.semis.get(0).getCodigo()));
        imgSemis2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(0).getEquipo2().getEscudo())));
        labelSemis2.setText(String.valueOf(Aplicacion.torneo.semis.get(0).getCodigo()));

        imgSemis3.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(1).getEquipo1().getEscudo())));
        labelSemis3.setText(String.valueOf(Aplicacion.torneo.semis.get(1).getCodigo()));
        imgSemis4.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.semis.get(1).getEquipo2().getEscudo())));
        labelSemis4.setText(String.valueOf(Aplicacion.torneo.semis.get(1).getCodigo()));
    }

    public void llenarFinal() {
        imgFinal1.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.finales.get(0).getEquipo1().getEscudo())));
        labelFinal1.setText(String.valueOf(Aplicacion.torneo.finales.get(0).getCodigo()));
        imgFinal2.setImage(new Image(
                getClass().getResourceAsStream(Aplicacion.torneo.finales.get(0).getEquipo2().getEscudo())));
        labelFinal2.setText(String.valueOf(Aplicacion.torneo.finales.get(0).getCodigo()));
    }

    @FXML
    public void abrirResumen(MouseEvent evt){
        
        int codigo = Integer.parseInt(((Label)evt.getSource()).getText());
        
        boolean encontrado = false;

        System.out.println("Buscando partido...");
        for (int i = 0; !encontrado && i < Aplicacion.torneo.octavos.size(); i++) {
                if(codigo == Aplicacion.torneo.octavos.get(i).getCodigo()){
                        partidoSelect = Aplicacion.torneo.octavos.get(i);
                        encontrado = true;
                        break;
                }
        }

        for (int i = 0; !encontrado && i < Aplicacion.torneo.cuartos.size(); i++) {
                if(codigo == Aplicacion.torneo.cuartos.get(i).getCodigo()){
                        partidoSelect = Aplicacion.torneo.cuartos.get(i);
                        encontrado = true;
                        break;
                }
        }

        for (int i = 0; !encontrado && i < Aplicacion.torneo.semis.size(); i++) {
                if(codigo == Aplicacion.torneo.semis.get(i).getCodigo()){
                        partidoSelect = Aplicacion.torneo.semis.get(i);
                        encontrado = true;
                        break;
                }
        }

        for (int i = 0; !encontrado && i < Aplicacion.torneo.finales.size(); i++) {
                if(codigo == Aplicacion.torneo.finales.get(i).getCodigo()){
                        partidoSelect = Aplicacion.torneo.finales.get(i);
                        encontrado = true;
                        break;
                }
        }
        System.out.println("Partido encontrado...");
        app.cambiarVentana(Aplicacion.TITULO_RESUMEN, Aplicacion.RUTA_RESUMEN);
    }

    public void regresar(){
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new Aplicacion();
        mostrarEquipos();
    }
}
