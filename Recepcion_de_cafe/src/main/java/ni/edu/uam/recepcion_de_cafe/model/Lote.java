package ni.edu.uam.recepcion_de_cafe.model;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter

public class Lote {
    private String codigo;
    private String productor;
    private double quintales;
    private double precioQuintal;
}
