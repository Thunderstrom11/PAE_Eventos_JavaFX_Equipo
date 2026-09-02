package ni.edu.uam.recepcion_de_cafe.validator;

import ni.edu.uam.recepcion_de_cafe.model.Lote;

public class LoteValidator {

    public static Lote crearLote(String codigo, String productor, String sQuintales, String sPrecio) {
        codigo = codigo == null ? "" : codigo.trim();
        productor = productor == null ? "" : productor.trim();
        sQuintales = sQuintales == null ? "" : sQuintales.trim();
        sPrecio = sPrecio == null ? "" : sPrecio.trim();

        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("Ingrese el código");
        }
        if (productor.isEmpty()) {
            throw new IllegalArgumentException("Ingrese el productor");
        }
        if (sQuintales.isEmpty()) {
            throw new IllegalArgumentException("Ingrese los quintales");
        }
        if (sPrecio.isEmpty()) {
            throw new IllegalArgumentException("Ingrese el precio X quintal");
        }

        double quintales;
        double precio;
        try {
            quintales = Double.parseDouble(sQuintales);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los quintales deben ser un número entero");
        }
        try {
            precio = Double.parseDouble(sPrecio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un número");
        }
        if (quintales <= 0) {
            throw new IllegalArgumentException("Los quintales deben ser mayores que 0");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        return new Lote(codigo, productor, quintales, precio);
    }
}
