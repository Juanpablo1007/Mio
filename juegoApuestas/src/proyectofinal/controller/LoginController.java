package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import proyectofinal.Aplicacion;
import proyectofinal.domain.*;

public class LoginController implements Initializable {

    private Aplicacion app;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField passContra;

    @FXML
    private Label labelError;

    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    public void ingresar() {
        if (!(txtUsuario.getText().equals("") || passContra.getText().equals(""))) {
            String usuario = txtUsuario.getText();
            String contrasenia = passContra.getText();
            boolean encontrado = false;
            for (Usuario jugador : Torneo.usuarios) {
                if (jugador.getNombre().equals(usuario) && jugador.getContrasenia().equals(contrasenia)) {
                    Aplicacion.usuarioActivo = jugador;
                    app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
                    encontrado = true;
                    Aplicacion.torneo.saldoIni = jugador.getSaldo();
                }
            }
            if(!encontrado){
                labelError.setVisible(true);
            }
        } else {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setContentText("Debes llenar todos los campos");
            mensaje.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();

        labelError.setVisible(false);
    }

}
