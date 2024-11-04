import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vaca extends Animales implements Serializable {
    private int peso;


    public Vaca(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto, int peso) {
        super(id, tipo, nombre, dia_insercion, alimento, producto);
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int producir(Granja granja) {
        LocalDateTime fecha = LocalDateTime.now();
        String formatosql = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int cantidad=this.getPeso()/100;
        System.out.println(cantidad);

        GestionBBDD.historialProduccion(this.getId(),cantidad,formatosql);

        GestionBBDD.actualizarCantidadProducto(this.getProducto().getId(), cantidad);

        return cantidad;
    }
}
