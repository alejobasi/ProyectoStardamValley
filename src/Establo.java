import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Establo implements Serializable {
    private List<Animales> animales;

    public Establo() {
        this.animales = new ArrayList<>();
    }

    public List<Animales> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animales> animales) {
        this.animales = animales;
    }

    public void mostrarEstablo(){

        if (animales.isEmpty()) {
            System.out.println("El establo está vacío.");

        }else {
            for (Animales animal:animales){
                System.out.println("Tipo de animal: " + animal.getTipo());
                System.out.println("Nombre: " + animal.getNombre());
                System.out.println("Día de inserción: " + animal.getDia_insercion());
                if (animal instanceof Oveja) {
                    Oveja oveja=(Oveja) animal;
                    System.out.println("Fecha de esquilado: " + oveja.getDiaEsquilado());
                } else if (animal instanceof Vaca) {
                    Vaca vaca=(Vaca) animal;
                    System.out.println("Peso: " + vaca.getPeso() + " kg");
                }
                System.out.println("Alimento: "+animal.getAlimento().getNombre());
                System.out.println("Producto: "+animal.getProducto().getNombre());
            }
        }
    }

    public void rellenarComedero(Granja granja){

        List<Alimento>listaAlimentos=GestionBBDD.recogerAlimentos();
        int cantidadMax=25;
        double presupuestoPrimero=granja.getPresupuesto();
        for (Alimento al:listaAlimentos){
            int cantidad=GestionBBDD.recogerCantidadAlimeto(al.getId());
            if (cantidad<25){
                int cantidadAnadir=25-cantidad;
                double precio=cantidadAnadir* al.getPrecio();
                if (granja.getPresupuesto()>precio){
                    System.out.println("Has comprado "+cantidadAnadir+" de "+al.getNombre()+" por: "+precio);
                    granja.setPresupuesto(granja.getPresupuesto()-precio);
                    GestionBBDD.rellenarCantidadAlimento(al.getId());
                }

            }
        }
        System.out.println("Has gastado en total: "+(presupuestoPrimero- granja.getPresupuesto()));


    }


}
