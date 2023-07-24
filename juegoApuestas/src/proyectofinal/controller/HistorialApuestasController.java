package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import proyectofinal.Aplicacion;
import proyectofinal.domain.*;

public class HistorialApuestasController implements Initializable {

    private Aplicacion app;

    @FXML
    private Label labelSaldo;

    @FXML
    private TextArea txtHistorial;

    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    public void mostrarInfo() {
        labelSaldo.setText("$" + String.format("%.2f", ((Usuario) Aplicacion.usuarioActivo).getSaldo()));
        System.out.println("Apuestas: " + ((Usuario) Aplicacion.usuarioActivo).getHistorialApuestas().size());
        for (Apuesta apuesta : ((Usuario) Aplicacion.usuarioActivo).getHistorialApuestas()) {
            String info = "Codigo : " + apuesta.getCodigo() + "\nEquipo : " + apuesta.getEquipo().getNombre()
                    + "\nEstado : " + apuesta.getEstado() 
                    + "\nValor apostado : " + apuesta.getValorApuesta()
                    + "\nGanado : " + (apuesta.getEstado().equals(EstadoApuesta.ACTIVA) ? "En juego..."  : apuesta.isGanado() ? "Ganaste vé, gastáme alguito" : "Perdiste vé, muy suave")
                    + "\n\n";
            txtHistorial.appendText(info);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
        mostrarInfo();
    }

}
