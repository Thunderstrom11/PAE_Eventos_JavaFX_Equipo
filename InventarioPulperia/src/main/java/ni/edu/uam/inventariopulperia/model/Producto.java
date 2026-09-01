package ni.edu.uam.inventariopulperia.model;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

}
