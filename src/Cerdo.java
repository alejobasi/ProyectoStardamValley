import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Cerdo extends Animales{
    public Cerdo(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, dia_insercion, alimento, producto);
    }
    public int producir(Granja granja) {
        int cantidadTrufa = 0;
        LocalDateTime fecha = LocalDateTime.now();
        String formatosql = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Random random = new Random();

        if (granja.getEstacion() == Estaciones.PRIMAVERA || granja.getEstacion() == Estaciones.VERANO) {
            cantidadTrufa = random.nextInt(2) + 2;
        } else if (granja.getEstacion() == Estaciones.OTOÑO) {
            cantidadTrufa = random.nextInt(2);
        }

        if (cantidadTrufa > 0) {
            GestionBBDD.historialProduccion(this.getId(), cantidadTrufa, formatosql);
            GestionBBDD.actualizarCantidadProducto(this.getProducto().getId(), cantidadTrufa);
        }

        return cantidadTrufa;
    }


}
