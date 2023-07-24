package proyectofinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import proyectofinal.Aplicacion;
import proyectofinal.domain.Equipo;
import proyectofinal.domain.JugadorEquipo;

public class InfoEquipoController implements Initializable {

    @FXML
    private TableView<JugadorEquipo> equiposTable;

    @FXML
    private TableColumn<JugadorEquipo, Integer> columnDorsal;

    @FXML
    private TableColumn<JugadorEquipo, String> columnNombre;

    @FXML
    private TableColumn<JugadorEquipo, String> columnNacion;

    @FXML
    private TableColumn<JugadorEquipo, Integer> columnGoles;

    @FXML
    private Label labelCodigo;

    @FXML
    private Label labelGoles;

    @FXML
    private Label labelNombre;

    @FXML
    private ToggleGroup equipos;

    private Aplicacion app;

    private ObservableList<JugadorEquipo> jugadores = FXCollections.observableArrayList();

    public void regresar() {
        app.cambiarVentana(Aplicacion.TITULO_INICIO, Aplicacion.RUTA_INICIO);
    }

    public void inicializarCampos() {
        equipos.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {//Se añade listener al grupo de botones (equipo) para cambiar valores cuando se presione un nuevo botón
                    public void changed(ObservableValue<? extends Toggle> ov,
                            final Toggle toggle, final Toggle new_toggle) {
                        if (new_toggle != null) {
                            Equipo equipo = Aplicacion.torneo.buscarEquipo(new Equipo("", ((ToggleButton) new_toggle).getText(), null, 0, ""));
                            String nombre = equipo.getNombre();
                            String codigo = equipo.getCodigo();
                            String goles = String.valueOf(equipo.getGoles()) ;

                            labelNombre.setText(nombre);
                            labelCodigo.setText(codigo);
                            labelGoles.setText(goles);
                            llenarTabla(equipo);
                        } else {
                            labelNombre.setText("...");
                            labelCodigo.setText("...");
                            labelGoles.setText("...");
                        }
                    }
                });
    }

    public void llenarTabla(Equipo equipo) {
        equiposTable.getItems().clear();
        jugadores.addAll(equipo.getJugadores());
        equiposTable.setItems(jugadores);
        equiposTable.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = new Aplicacion();
        inicializarCampos();
        columnDorsal.setCellValueFactory(new PropertyValueFactory<>("dorsal"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnNacion.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        columnGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
    }
}