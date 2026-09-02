package ni.edu.uam.tiendaartesanias.model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter

public class Articulo {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
    private ImageView imagen;

    public Articulo(String nombre, String categoria, double precio, int stock, String rutaImagen){
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.imagen = new ImageView(new Image(getClass().getResourceAsStream(rutaImagen)));
        this.imagen.setFitWidth(60);
        this.imagen.setPreserveRatio(true);
    }
}


