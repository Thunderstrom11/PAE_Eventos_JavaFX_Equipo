package ni.edu.uam.tiendaartesanias;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import ni.edu.uam.tiendaartesanias.model.Articulo;

import java.util.Comparator;

import java.text.NumberFormat;

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
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtStock.clear();
        txtImagen.clear();
        txtNombre.requestFocus();
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String categoria =  txtCategoria.getText().trim();
        String rutaImagen = txtImagen.getText().trim();

        if(nombre.isEmpty() || categoria.isEmpty() || rutaImagen.isEmpty()){
            mostrarAlerta("Datos incompletos", "Completa los campos");
            return;
        }
        try{
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            if(precio<=0 || stock < 0){
                mostrarAlerta("Error", "El precio debe ser mayor a 0 y el stock no puede ser negativo");
                return;
            }
            if(getClass().getResourceAsStream(rutaImagen)==null){
                mostrarAlerta("Imagen no encontrada", "No existe " + rutaImagen + " en resources");
                return;
            }
            Articulo a = new Articulo(nombre, categoria, precio, stock, rutaImagen);
            articulos.add(a);
            nuevoProducto(event);
        }catch (NumberFormatException e){
            mostrarAlerta("Hay datos invalidos", "Precio y stock deben ser numeros");
        }
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
        String texto = txtNombre.getText().trim();
        if (texto.isEmpty()) {
            mostrarAlerta("Busqueda vacia", "Escribe el nombre del articulo");
            return;
        }
        for (Articulo a : articulos) {
            if (a.getNombre().equalsIgnoreCase(texto)) {
                tvCatalogo.getSelectionModel().select(a);
                tvCatalogo.scrollTo(a);
                return;
            }
        }
        mostrarAlerta("Alerta", "No se encontro el articulo");
    }


    @FXML
    private void verCatalogo(ActionEvent event) {
        articulos.sort(Comparator.comparing(Articulo::getNombre));
    }


    @FXML
    private void registrarVenta(ActionEvent event) {
        Articulo seleccionado = tvCatalogo.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Sin seleccion", "Selecciona un articulo de la tabla");
            return;
        }
        if (seleccionado.getStock() <= 0) {
            mostrarAlerta("Sin existencias", "No queda stock de " + seleccionado.getNombre());
            return;
        }
        int indice = articulos.indexOf(seleccionado);
        Articulo actualizado = new Articulo(
                seleccionado.getNombre(),
                seleccionado.getCategoria(),
                seleccionado.getPrecio(),
                seleccionado.getStock() - 1,
                seleccionado.getRutaImagen());

        Alert venta = new Alert(Alert.AlertType.INFORMATION);
        venta.setTitle("Venta registrada");
        venta.setHeaderText(null);
        venta.setContentText("Se vendio 1 unidad de " + seleccionado.getNombre()
                + " por C$ " + String.format("%.2f", seleccionado.getPrecio()));
        venta.showAndWait();
    }


    @FXML
    private void acercaDe(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Acerca de");
        info.setHeaderText("Tienda de Artesanias");
        info.setContentText("Aplicacion de catalogo y ventas para artesanias nicaraguenses.\n"
                + "Programacion de Aplicaciones de Escritorio - UAM");
        info.showAndWait();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}
