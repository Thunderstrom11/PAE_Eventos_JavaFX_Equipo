package ni.edu.uam.tiendaartesanias;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import ni.edu.uam.tiendaartesanias.model.Articulo;

public class TiendaController {
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtImagen;

    @FXML
    private TableView<Articulo> tvCatalogo;
    @FXML
    private TableColumn<Articulo, ImageView> colImagen;
    @FXML
    private TableColumn<Articulo, String> colNombre;
    @FXML
    private TableColumn<Articulo, String> colCategoria;
    @FXML
    private TableColumn<Articulo, Double> colPrecio;
    @FXML
    private TableColumn<Articulo, Integer> colStock;

    private ObservableList<Articulo> articulos = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tvCatalogo.setItems(articulos);
    }


    @FXML
    private void nuevoProducto(ActionEvent event) {
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
    }


    @FXML
    private void verCatalogo(ActionEvent event) {
    }


    @FXML
    private void registrarVenta(ActionEvent event) {
    }


    @FXML
    private void acercaDe(ActionEvent event) {
    }
}
