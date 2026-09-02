package ni.edu.uam.inventariopulperia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.inventariopulperia.model.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InventarioController {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Producto> tvInventario;
    @FXML
    private TableColumn<Producto, String> colCodigo;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colCantidad;

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    protected void initialize(){
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tvInventario.setItems(productos);
    }

    @FXML
    private void buscarProducto(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            String texto = txtBuscar.getText().trim();

            if(texto.isEmpty()){
                mostrarAlerta("busqueda vacia", "ingresa datos");
                return;
            }

            for (Producto p: productos){
                if(p.getNombre().equalsIgnoreCase(texto)){
                    tvInventario.getSelectionModel().select(p);
                    tvInventario.scrollTo(p);
                    return;
                }
            }
            mostrarAlerta("Alerta", "No se encontro el producto");
            return;
        }
    }

    @FXML
    private void agregarProducto(ActionEvent event){
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        if(codigo.isEmpty() || nombre.isEmpty()){
            mostrarAlerta("datos incompletos", "completa los datos");
            return;
        }
        try{
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            if(cantidad<0 || precio<0){
                mostrarAlerta("Error", "se ingresaron datos invalidos");
                return;
            }
            Producto p = new Producto(codigo,nombre,precio,cantidad);
            productos.add(p);
            txtNombre.clear();
            txtCantidad.clear();
            txtPrecio.clear();
            txtCodigo.clear();
            txtBuscar.clear();
        }catch (NumberFormatException e){
            mostrarAlerta("hay datos invalidos", "corrige los datos");
            return;
        }


    }

    private void mostrarAlerta(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }




}
