package ni.edu.uam.recepcion_de_cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.recepcion_de_cafe.dao.loteDAO;
import ni.edu.uam.recepcion_de_cafe.model.Lote;
import ni.edu.uam.recepcion_de_cafe.validator.LoteValidator;

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

    private final  loteDAO loteDAO = new loteDAO();
    private final ObservableList<Lote> lotes = FXCollections.observableArrayList();
    private Lote loteSeleccionado = null;

    @FXML
    // de donde salen las cosas de la observable list
    protected void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colQuintales.setCellValueFactory(new PropertyValueFactory<>("quintales"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioQuintal"));
        tvLotes.setItems(lotes);
        llenarTabla();
    }

    @FXML
    private void agregarLote(ActionEvent event) {
        try {
            Lote lote = leerDatos();
            if (loteDAO.buscarPorCodigo(lote.getCodigo()) != null) {
                throw new IllegalArgumentException("Ya existe un lote con ese código.");
            }
            loteDAO.agregar(lote);
            llenarTabla();
            limpiarCampos();
            lblDetalle.setText("Lote agregado.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta(e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("No se pudo agregar el lote.");
        }
    }
    @FXML
    private void mostrarDetalle(MouseEvent event) {
        Lote lote = tvLotes.getSelectionModel().getSelectedItem();
        if (lote == null) {
            return;
        }
        double total = lote.getQuintales() * lote.getPrecioQuintal();
        lblDetalle.setText(String.format(
                "Código: %s | Productor: %s | Quintales: %.2f | Precio: %.2f | Total: %.2f",
                lote.getCodigo(), lote.getProductor(),
                lote.getQuintales(), lote.getPrecioQuintal(), total
        ));
    }

    @FXML
    private void editarLote(ActionEvent event) {
        Lote lote = tvLotes.getSelectionModel().getSelectedItem();
        if (lote == null) {
            mostrarAlerta("Seleccione un lote para editar.");
            return;
        }
        loteSeleccionado = lote;
        txtCodigo.setText(lote.getCodigo());
        txtProductor.setText(lote.getProductor());
        txtQuintales.setText(String.valueOf(lote.getQuintales()));
        txtPrecio.setText(String.valueOf(lote.getPrecioQuintal()));
        txtCodigo.setDisable(true);
        btnAgregar.setDisable(true);
        btnGuardar.setDisable(false);
    }

    @FXML
    private void guardarCambios(ActionEvent event) {
        try {
            if (loteSeleccionado == null) {
                throw new IllegalArgumentException("No hay un lote en edición.");
            }
            Lote datos = leerDatos();
            loteSeleccionado.setProductor(datos.getProductor());
            loteSeleccionado.setQuintales(datos.getQuintales());
            loteSeleccionado.setPrecioQuintal(datos.getPrecioQuintal());
            tvLotes.refresh();
            loteSeleccionado = null;
            limpiarCampos();
            txtCodigo.setDisable(false);
            btnAgregar.setDisable(false);
            btnGuardar.setDisable(true);
            lblDetalle.setText("Cambios guardados.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta(e.getMessage());
        } catch (Exception e) {
            mostrarAlerta("No se pudieron guardar los cambios.");
        }
    }

    @FXML
    private void eliminarLote(ActionEvent event) {
        Lote lote = tvLotes.getSelectionModel().getSelectedItem();
        if (lote == null) {
            mostrarAlerta("Seleccione un lote para eliminar.");
            return;
        }
        loteDAO.eliminar(lote);
        llenarTabla();
        if (lote == loteSeleccionado) {
            loteSeleccionado = null;
            limpiarCampos();
            txtCodigo.setDisable(false);
            btnAgregar.setDisable(false);
            btnGuardar.setDisable(true);
        }
        lblDetalle.setText("Lote eliminado.");
    }

    private Lote leerDatos() {
        return LoteValidator.crearLote(
                txtCodigo.getText(),
                txtProductor.getText(),
                txtQuintales.getText(),
                txtPrecio.getText()
        );
    }

    private void llenarTabla() {
        lotes.clear();
        lotes.addAll(loteDAO.obtenerTodos());
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtProductor.clear();
        txtQuintales.clear();
        txtPrecio.clear();
        tvLotes.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



}
