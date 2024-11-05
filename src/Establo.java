import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        System.out.println();
        System.out.println();

        List<Alimento>alimentos=GestionBBDD.recogerAlimentos();
        System.out.print("| Alimento   | Cantidad Disponible |\n" +
                "---------------------------------------");
        System.out.println();
        for (Alimento alimento:alimentos){
            System.out.print("| " + alimento.getNombre()+"   | "+alimento.getCantidad()+"   | ");
            System.out.println();
        }
        System.out.println();
        System.out.println();

        List<Producto>productos=GestionBBDD.recogerProductos();
        System.out.print("| Producto   | Cantidad Disponible |\n" +
                "---------------------------------------");
        System.out.println();
        for (Producto producto:productos){
            System.out.print("| " + producto.getNombre()+"   | "+producto.getCantidad()+"   | ");
            System.out.println();
        }
    }

    public void rellenarComedero(Granja granja){

        List<Alimento>listaAlimentos=GestionBBDD.recogerAlimentos();
        int cantidadMax=25;
        double presupuestoPrimero=granja.getPresupuesto();
        double presupuestoFinal;
        List<Alimento>todosAlimentos=GestionBBDD.recogerAlimentos();
        int total=0;
        for (Alimento a:todosAlimentos){
            if (a.getCantidad()==25){
            total++;
            }
        }

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
        if (total!=3){
        presupuestoFinal=presupuestoPrimero- granja.getPresupuesto();
        System.out.println("Has gastado en total: "+(presupuestoFinal));


            GestionBBDD.transsacciones(presupuestoFinal,Transaccion.COMPRA,Elemento.ALIMENTO);

        }else {
            System.out.println("Ya tienes los comederos llenos");
        }


    }

    public void alimentando(Granja granja){

        List<Alimento>alimentos=GestionBBDD.recogerAlimentos();

     for (Animales animales:granja.getEstablo().getAnimales()){
        animales.alimentar();

     }

    }

    public  void actualizarDia(Granja granja){
        for (Animales animal:granja.getEstablo().getAnimales()){
            int dia=animal.getDia_insercion();
            animal.setDia_insercion(dia+1);
        }
    }

    public  void reiniciarAlimento(Granja granja){

        for (Animales animales:granja.getEstablo().getAnimales()){
            animales.setAlimentado(false);
        }
    }

    public void produccion(Granja granja){
        boolean alimentados=true;
        HashMap<Producto,Integer> mapa=new HashMap();
        for (Animales animales:granja.getEstablo().getAnimales()){
            if (animales.isAlimentado()==true){




               int cant= animales.producir(granja);

                mapa.merge(animales.getProducto(),cant,Integer::sum);
                if (!alimentados){
                    alimentados=true;

                }
            }
            animales.setAlimentado(false);
        }
if (alimentados){


        System.out.println("Comenzando la produducción");
        for (Map.Entry<Producto, Integer> entry : mapa.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("Se han almacenado " + cantidad + " unidades de " + producto.getNombre());
        }
}else {
    System.out.println("No están alimentados");
}
    }

    public void venderProductos(Granja granja){
        List<Producto> productos=GestionBBDD.recogerProductos();
        double cantidadTotal=0;

        System.out.println("Vendiendo Productos...");
        for (Producto producto: productos){
            if (producto.getCantidad()>0){
                double precio=producto.getCantidad()*producto.getPrecio();
                cantidadTotal+=precio;
                granja.setPresupuesto(granja.getPresupuesto()+precio);
                System.out.println("  - Se han vendido "+producto.getCantidad()+" unidades de "+producto.getNombre()+" por "+precio);
                GestionBBDD.transsacciones(precio,Transaccion.VENTA,Elemento.PRODUCTO);
            }
        }
        System.out.println("TOTAL DE INGRESOS: "+cantidadTotal+"€");
    }


}
