package proyectofinal.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import proyectofinal.Aplicacion;
import proyectofinal.domain.Usuario;

public class RegistroController implements Initializable {

    private Aplicacion app;

    @FXML
    private TextField txtSaldo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasenia;

    private final double MIN_SALDO = 10000;
    private final double MAX_SALDO = 1000000;

    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    public void registrar() {
        boolean registrado = false;
        Alert mensaje;
        try {
            if (!(txtSaldo.getText().equals("") || txtNombre.getText().equals("") || txtDocumento.getText().equals("")
                    || txtCorreo.getText().equals("") || txtContrasenia.getText().equals(""))) {
                double saldo = Double.parseDouble(txtSaldo.getText());
                String nombre = txtNombre.getText();
                String documento = txtDocumento.getText();
                String correo = txtCorreo.getText();
                String contrasenia = txtContrasenia.getText();

                if (saldo < MIN_SALDO || saldo > MAX_SALDO) {
                    mensaje = new Alert(AlertType.ERROR);
                    mensaje.setContentText("Tu saldo está por fuera del monto permitido");
                } else {
                    Usuario usuario = new Usuario(nombre, documento, correo, contrasenia, saldo, new ArrayList<>());

                    if (Aplicacion.torneo.registrarUsuario(usuario)) {
                        mensaje = new Alert(AlertType.CONFIRMATION);
                        mensaje.setContentText("El usuario se ha registrado con éxito");
                        registrado = true;
                    } else {
                        mensaje = new Alert(AlertType.ERROR);
                        mensaje.setContentText("No se ha podido registrar el usuario");
                    }
                }
                mensaje.showAndWait();
                if(registrado){
                    app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
                }
            } else {
                mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("Debes llenar todos los campos");
                mensaje.showAndWait();
            }
        } catch (NumberFormatException ex) {
            mensaje = new Alert(AlertType.ERROR);
            mensaje.setContentText("Debes ingresar valores numéricos en el saldo");
            mensaje.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
    }
}
