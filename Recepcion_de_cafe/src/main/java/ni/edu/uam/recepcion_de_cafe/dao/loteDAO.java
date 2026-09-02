package ni.edu.uam.recepcion_de_cafe.dao;
import ni.edu.uam.recepcion_de_cafe.model.Lote;

import java.util.ArrayList;
import java.util.List;

public class loteDAO {
    private final List<Lote> lotes = new ArrayList<>();

    public void agregar(Lote lote) {
        lotes.add(lote);
    }

    public List<Lote> obtenerTodos() {
        return lotes;
    }

    public Lote buscarPorCodigo(String codigo) {
        for (Lote lote : lotes) {
            if (lote.getCodigo().equalsIgnoreCase(codigo)) {
                return lote;
            }
        }
        return null;
    }

    public void eliminar(Lote lote) {
        lotes.remove(lote);
    }
}

