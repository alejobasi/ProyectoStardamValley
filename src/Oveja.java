import java.io.Serializable;
import java.time.LocalDateTime;


public class Oveja extends Animales implements Serializable {
    private LocalDateTime diaEsquilado;

    public Oveja(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, dia_insercion, alimento, producto);

    }

    public LocalDateTime getDiaEsquilado() {
        return diaEsquilado;
    }

    public void setDiaEsquilado(LocalDateTime diaEsquilado) {
        this.diaEsquilado = diaEsquilado;
    }
}
