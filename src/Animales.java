import java.io.Serializable;

public abstract class Animales implements Serializable {
    private int id;
    private Animal tipo;
    private String nombre;
    private int dia_insercion;
    private boolean alimentado;
    private Alimento alimento;
    private Producto producto;

    public Animales(int id, Animal tipo, String nombre, int dia_insercion, Alimento alimento, Producto producto) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.alimentado=false;
        this.dia_insercion = dia_insercion;
        this.alimento = alimento;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal getTipo() {
        return tipo;
    }

    public void setTipo(Animal tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia_insercion() {
        return dia_insercion;
    }

    public void setDia_insercion(int dia_insercion) {
        this.dia_insercion = dia_insercion;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isAlimentado() {
        return alimentado;
    }

    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    public abstract int  producir(Granja granja);
}
