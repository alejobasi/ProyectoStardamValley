import java.io.Serializable;

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
}
