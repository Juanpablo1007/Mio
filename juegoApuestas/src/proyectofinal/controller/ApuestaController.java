package proyectofinal.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import proyectofinal.Aplicacion;
import proyectofinal.domain.*;

public class ApuestaController implements Initializable {

    private Aplicacion app;

    @FXML
    private Label labelSaldo;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelCuota;

    @FXML
    private Label labelGanancia;

    @FXML
    private Label labelSigEncuentro;

    @FXML
    private TextField txtValor;

    @FXML
    private ToggleGroup equipo;

    private Equipo equipoSelect;

    private double ganancia;

    public void hacerApuesta() {
        Alert mensaje;
        
        try {
            String codigo = Apuesta.generarCodigo();
            double valorApuesta = Double.parseDouble(txtValor.getText());
            float cuota = Float.parseFloat(labelCuota.getText());

            if (valorApuesta > ((Usuario) Aplicacion.usuarioActivo).getSaldo()) {
                mensaje = new Alert(AlertType.ERROR);
                mensaje.setContentText("Saldo insuficiente");
            } else {
                if (ganancia != 0) {
                    Apuesta apuesta = new Apuesta(codigo, equipoSelect, valorApuesta, cuota, ganancia,
                            EstadoApuesta.COLOCADA);

                    ((Usuario) Aplicacion.usuarioActivo).colocarApuesta(apuesta);

                    mensaje = new Alert(AlertType.CONFIRMATION);
                    mensaje.setContentText("Tu apuesta se ha generado, puedes revisar tu historial");
                } else {
                    mensaje = new Alert(AlertType.ERROR);
                    mensaje.setContentText("Debes escoger un equipo e ingresar un valor para apostar");
                }
            }
            labelSaldo.setText("$" + String.format("%.2f", ((Usuario) Aplicacion.usuarioActivo).getSaldo()));
            mensaje.showAndWait();
        } catch (NumberFormatException exception) {
            mensaje = new Alert(AlertType.ERROR);
            mensaje.setContentText("Debes escoger un equipo e ingresar un valor para apostar");
            mensaje.showAndWait();
        }
    }

    public void inicializarCampos() {
        deshabilitarBtn();
        labelSaldo.setText("$" + String.format("%.2f", ((Usuario) Aplicacion.usuarioActivo).getSaldo()));
        equipo.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {// Propiedad
                    
                    public void changed(ObservableValue<? extends Toggle> ov,
                            final Toggle toggle, final Toggle new_toggle) {
                        if (new_toggle != null) {
                            equipoSelect = Aplicacion.torneo
                                    .buscarEquipo(new Equipo("", ((ToggleButton) new_toggle).getText(), null, 0, ""));
                            String nombre = (equipoSelect.getNombre());
                            float cuota = Apuesta.calcularCuota(equipoSelect);

                            labelNombre.setText(nombre);
                            labelCuota.setText(String.valueOf(cuota));
                            Equipo rival = Aplicacion.torneo.obtenerRival(equipoSelect);
                            labelSigEncuentro.setText(rival.getNombre());
                        } else {
                            labelNombre.setText("...");
                            labelCuota.setText("...");
                            labelGanancia.setText("...");
                        }
                    }
                });

        txtValor.textProperty().addListener((observable, oldValue, newValue) -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            try {
                if (equipo.getSelectedToggle() != null) {
                    ganancia = 0.0;
                    if (!newValue.equals("")) {
                        float cuota = Float.parseFloat(labelCuota.getText());
                        double valorApuesta = Double.parseDouble(newValue);
                        Apuesta apuesta = new Apuesta("", null, valorApuesta, cuota, 0, null);
                        ganancia = apuesta.calcularGanancia();
                    }
                    labelGanancia.setText(String.format("%.2f", ganancia));
                } else {
                    mensaje.setContentText("Selecciona un equipo para calcular tus ganancias");
                    mensaje.showAndWait();
                    txtValor.setText("");
                }
            } catch (NumberFormatException ex) {
                mensaje.setContentText("Debes ingresar valores númericos");
                mensaje.showAndWait();
            }
        });
    }

    public void deshabilitarBtn() {
        ArrayList<Partido> fase = Aplicacion.torneo.obtenerFase();
        boolean disable;
        for (Toggle button : equipo.getToggles()) {
            String codBtn = ((ToggleButton) button).getText();
            disable = true;
            for (Partido partido : fase) {
                if (partido.getEquipo1().getCodigo().equals(codBtn)
                        || partido.getEquipo2().getCodigo().equals(codBtn)) {
                    disable = false;
                }
            }
            ((ToggleButton) button).setDisable(disable);
        }
    }

    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
        inicializarCampos();
    }

}
