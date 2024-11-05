import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gallina extends Animales{
    public Gallina(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto) {
        super(id, tipo, nombre, dia_insercion, alimento, producto);
    }
    public int producir(Granja granja) {
        int huevos=0;
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatosql = fecha.format(formato);

        if (getDia_insercion() < 3) {
            System.out.println("La gallina " + getNombre() + " es muy joven y todavía no da huevos.");
        } else if (getDia_insercion() >= 3 && getDia_insercion() < 40) {

            GestionBBDD.historialProduccion(getId(), 2, formatosql);
            huevos=2;
            GestionBBDD.actualizarCantidadProducto(getProducto().getId(), 2);

        } else {
            GestionBBDD.historialProduccion(getId(), 1, formatosql);
            huevos=1;
            GestionBBDD.actualizarCantidadProducto(getProducto().getId(), 1);

            
        }
        return huevos;
    }


}
