package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import proyectofinal.Aplicacion;
import proyectofinal.domain.Evento;
import proyectofinal.domain.Partido;

public class ResumenController implements Initializable{

    @FXML
    private Label labelEquipos;

    @FXML
    private Label labelResultado;

    @FXML
    private TextArea txtEventos;

    private Aplicacion app;

    public void regresar(){
        app.cambiarVentana(Aplicacion.TITULO_RESUMEN, Aplicacion.RUTA_ARBOL);
    }

    public void llenarCampos(){
        Partido partido = ArbolController.partidoSelect;
        labelEquipos.setText(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
        labelResultado.setText(partido.getGolesEq1() + " - " + partido.getGolesEq2());
        int minuto = 0;
        String mensaje = "";
        while(minuto <= 90){
            for (int i = 0; i < partido.getEventos().size(); i++) {
                Evento evento = partido.getEventos().get(i);
                String nomEquipo = evento.getEquipo().getNombre();
                if(evento.getMinuto() == minuto){
                    mensaje += "\n" + minuto + "' " + evento.getTipoEvento().getNombre() + " (" + nomEquipo + ")";
                }
            }
            minuto += 1;
        }
        txtEventos.setText(mensaje);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        app = new Aplicacion();
        llenarCampos();
    }
}
