package ni.edu.uam.recepcion_de_cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.recepcion_de_cafe.model.Lote;

public class RecepcionController {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtProductor;
    @FXML
    private TextField txtQuintales;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Label lblDetalle;
    @FXML
    private TableView<Lote> tvLotes;
    @FXML
    private TableColumn<Lote, String> colCodigo;
    @FXML
    private TableColumn<Lote, String> colProductor;
    @FXML
    private TableColumn<Lote, Double> colQuintales;
    @FXML
    private TableColumn<Lote, Double> colPrecio;

    private ObservableList<Lote> lotes = FXCollections.observableArrayList();
    private Lote loteSeleccionado = null;

    @FXML
    // de donde salen las cosas de la observable list
    protected void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colQuintales.setCellValueFactory(new PropertyValueFactory<>("quintales"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioQuintal"));
        tvLotes.setItems(lotes);
    }

    @FXML
    private void agregarLote(ActionEvent event){

    }

    @FXML
    private void mostrarDetalle(MouseEvent event){

    }

    @FXML
    private void editarLote(ActionEvent event){

    }

    @FXML
    private void guardarCambios(ActionEvent event) {
    }

    @FXML
    private void eliminarLote(ActionEvent event) {
    }

}
