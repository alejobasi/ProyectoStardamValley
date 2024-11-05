import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Oveja extends Animales implements Serializable {
    private LocalDateTime diaEsquilado;

    public Oveja(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, dia_insercion, alimento, producto);
        this.diaEsquilado=LocalDateTime.of(2024, 5, 5, 12,0);

    }

    public LocalDateTime getDiaEsquilado() {
        return diaEsquilado;
    }

    public void setDiaEsquilado(LocalDateTime diaEsquilado) {
        this.diaEsquilado = diaEsquilado;
    }

    public int producir(Granja granja) {
        int lana = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        if (this.diaEsquilado.isBefore(fechaActual.minusDays(2))) {
            lana = 5;
            diaEsquilado = fechaActual;
            String formatosql = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            GestionBBDD.historialProduccion(this.getId(), lana, formatosql);
            GestionBBDD.actualizarCantidadProducto(this.getProducto().getId(), lana);
        } else {
            System.out.println("Todavía no han pasado 2 días y no se puede esquilar a la oveja.");
        }

        return lana;
    }
}
