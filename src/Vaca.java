import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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


    public void alimentar() {
        List<Alimento> alimentos = GestionBBDD.recogerAlimentos();

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatosql = fecha.format(formato);

        for (Alimento alimento : alimentos) {
            if (this.getAlimento().getId() == alimento.getId()) {
                if (this.getDia_insercion()<10){
                    if (alimento.getCantidad()>0){
                        this.setAlimentado(true);

                        GestionBBDD.historialConsumo(this.getId(),1,formatosql);
                        GestionBBDD.actualizarAlimento(alimento, 1);
                        if (this.isAlimentado()==true)
                            System.out.println("La "+this.getTipo()+" llamada "+this.getNombre()+" está alimentada");
                    }
                } else if (this.getDia_insercion()>10&&this.getDia_insercion()<40) {
                    if (alimento.getCantidad()>2){
                        this.setAlimentado(true);

                        GestionBBDD.historialConsumo(this.getId(),3,formatosql);

                        GestionBBDD.actualizarAlimento(alimento, 3);
                        if (this.isAlimentado()==true)
                            System.out.println("La "+this.getTipo()+" llamada "+this.getNombre()+" está alimentada");
                    }
                }else {
                    if (alimento.getCantidad()>1){
                        this.setAlimentado(true);

                        GestionBBDD.historialConsumo(this.getId(),2,formatosql);

                        GestionBBDD.actualizarAlimento(alimento, 2);
                        if (this.isAlimentado()==true)
                            System.out.println("La "+this.getTipo()+" llamada "+this.getNombre()+" está alimentada");
                    }
                }
            }
        }}

}
