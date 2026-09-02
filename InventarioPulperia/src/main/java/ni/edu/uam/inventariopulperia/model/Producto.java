package ni.edu.uam.inventariopulperia.model;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

}
