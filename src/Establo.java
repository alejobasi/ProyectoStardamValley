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
            System.out.print("| ID   | Tipo       |Alimentado | Alimento  | Producto   |\n" +
                    "-----------------------------------------------------------------------------");
            System.out.println();
            for (Animales animal:animales){
                String alimentado="";
                if(animal.isAlimentado()){
                    alimentado="Si";
                }else {
                    alimentado="No";
                }
                System.out.print("| " + animal.getTipo()+"   | "+animal.getTipo()+"   | "+alimentado+"        " +
                        "| "+animal.getAlimento().getNombre()+"      | "+animal.getProducto().getNombre()+"   |");
                System.out.println();
            }
        }
    }

    public void rellenarComedero(Granja granja){

        List<Alimento>listaAlimentos=GestionBBDD.recogerAlimentos();
        int cantidadMax=25;
        double presupuestoPrimero=granja.getPresupuesto();
        double presupuestoFinal;
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
        presupuestoFinal=presupuestoPrimero- granja.getPresupuesto();
        System.out.println("Has gastado en total: "+(presupuestoFinal));

        GestionBBDD.transsaccionesCompra(presupuestoFinal);


    }

    public void alimentar(Granja granja){

        List<Alimento>alimentos=GestionBBDD.recogerAlimentos();

     for (Animales animales:granja.getEstablo().getAnimales()){
        for (Alimento alimento:alimentos){
            if (animales.getAlimento().getId()==alimento.getId()){
                if (alimento.getCantidad()>0){
                    animales.setAlimentado(true);
                    GestionBBDD.actualizarAlimento(alimento);
                    if (animales.isAlimentado()==true)
                    System.out.println("La "+animales.getTipo()+" llamada "+animales.getNombre()+" está alimentada");
                }
            }
        }

     }

    }


}
