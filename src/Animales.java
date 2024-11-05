import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public void alimentar(){
        List<Alimento> alimentos=GestionBBDD.recogerAlimentos();

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatosql=fecha.format(formato);

        for (Alimento alimento:alimentos){
            if (this.getAlimento().getId()==alimento.getId() && alimento.getCantidad()>0){
                this.setAlimentado(true);

                GestionBBDD.historialConsumo(this.getId(),1,formatosql);

                GestionBBDD.actualizarAlimento(alimento, 1);
                if (this.isAlimentado()==true)
                    System.out.println("La "+this.getTipo()+" llamada "+this.getNombre()+" está alimentada");
            }
        }
    }



}
