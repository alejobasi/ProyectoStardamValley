import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                        "| "+animal.getAlimento().getNombre()+"      | "+animal.getProducto().getNombre()+"   |"+animal.getDia_insercion());
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

        GestionBBDD.transsacciones(presupuestoFinal,Transaccion.COMPRA,Elemento.ALIMENTO);


    }

    public void alimentar(Granja granja){

        List<Alimento>alimentos=GestionBBDD.recogerAlimentos();

     for (Animales animales:granja.getEstablo().getAnimales()){
         LocalDateTime fecha = LocalDateTime.now();
         DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formatosql=fecha.format(formato);

        for (Alimento alimento:alimentos){
            if (animales.getAlimento().getId()==alimento.getId()){
                if (animales instanceof Vaca){

                    if (animales.getDia_insercion()<10){
                        if (alimento.getCantidad()>0){
                            animales.setAlimentado(true);

                            GestionBBDD.historialConsumo(animales.getId(),1,formatosql);
                            GestionBBDD.actualizarAlimento(alimento, 1);
                            if (animales.isAlimentado()==true)
                                System.out.println("La "+animales.getTipo()+" llamada "+animales.getNombre()+" está alimentada");
                        }
                    } else if (animales.getDia_insercion()>10&&animales.getDia_insercion()<40) {
                        if (alimento.getCantidad()>2){
                            animales.setAlimentado(true);

                            GestionBBDD.historialConsumo(animales.getId(),3,formatosql);

                            GestionBBDD.actualizarAlimento(alimento, 3);
                            if (animales.isAlimentado()==true)
                                System.out.println("La "+animales.getTipo()+" llamada "+animales.getNombre()+" está alimentada");
                        }
                    }else {
                        if (alimento.getCantidad()>1){
                            animales.setAlimentado(true);

                            GestionBBDD.historialConsumo(animales.getId(),2,formatosql);

                            GestionBBDD.actualizarAlimento(alimento, 2);
                            if (animales.isAlimentado()==true)
                                System.out.println("La "+animales.getTipo()+" llamada "+animales.getNombre()+" está alimentada");
                        }
                    }

                }else {


                if (alimento.getCantidad()>0){

                    animales.setAlimentado(true);



                    GestionBBDD.historialConsumo(animales.getId(),1,formatosql);

                    GestionBBDD.actualizarAlimento(alimento, 1);
                    if (animales.isAlimentado()==true)
                    System.out.println("La "+animales.getTipo()+" llamada "+animales.getNombre()+" está alimentada");
                }
                }
            }
        }

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

    public void producir(Granja granja){
        int huevos=0;
        int leche=0;
        int lana=0;
        int trufa=0;

        for (Animales animales:granja.getEstablo().getAnimales()){
            if (animales.isAlimentado()==true){

                LocalDateTime fecha = LocalDateTime.now();
                DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formatosql=fecha.format(formato);


                if (animales instanceof Gallina){
                    System.out.println(animales.getDia_insercion());
                    if (animales.getDia_insercion()<3){
                        System.out.println("La gallina "+animales.getNombre()+" es muy joven y todavia no da huevos");

                    }else if (animales.getDia_insercion()>3 && animales.getDia_insercion()<40){
                        huevos+=2;
                        GestionBBDD.historialProduccion(animales.getId(),2,formatosql);
                        GestionBBDD.actualizarCantidadProducto(animales.getProducto().getId(),2);

                    }else {
                        GestionBBDD.historialProduccion(animales.getId(),1,formatosql);

                        GestionBBDD.actualizarCantidadProducto(animales.getProducto().getId(),1);
                        huevos+=1;

                    }

                }


                if (animales instanceof Oveja){

                    Oveja oveja=(Oveja) animales;
                    LocalDateTime fechaEsquilada=oveja.getDiaEsquilado();

                    if (fechaEsquilada.isBefore(LocalDateTime.now().minusDays(2))) {
                        GestionBBDD.historialProduccion(animales.getId(),5,formatosql);

                        GestionBBDD.actualizarCantidadProducto(oveja.getProducto().getId(), 5);
                        lana+=5;
                    }else {
                        System.out.println("Todavia no han pasado 2 dias y no se puede esquilar");
                    }
                }

                if (animales instanceof Vaca){
                    Vaca vaca=(Vaca) animales;

                    int cantidad=vaca.getPeso()/100;
                    System.out.println(cantidad);
                    leche+=cantidad;
                    GestionBBDD.historialProduccion(animales.getId(),cantidad,formatosql);

                    GestionBBDD.actualizarCantidadProducto(vaca.getProducto().getId(), cantidad);


                }

                if (animales instanceof Cerdo) {
                    Cerdo cerdo = (Cerdo) animales;

                    if (granja.getEstacion()==Estaciones.PRIMAVERA || granja.getEstacion()==Estaciones.VERANO){

                        Random random= new Random();
                        int cantidad= random.nextInt(2)+2;
                        trufa+=cantidad;
                        GestionBBDD.historialProduccion(animales.getId(),cantidad,formatosql);

                        GestionBBDD.actualizarCantidadProducto(cerdo.getProducto().getId(), cantidad);
                    } else if (granja.getEstacion()==Estaciones.OTOÑO) {
                        Random random= new Random();
                        int cantidad= random.nextInt(2);
                        trufa+=cantidad;
                        GestionBBDD.historialProduccion(animales.getId(),cantidad,formatosql);

                        GestionBBDD.actualizarCantidadProducto(cerdo.getProducto().getId(), cantidad);
                        
                    }

                }

            }
            animales.setAlimentado(false);
        }

        System.out.println("Comenzando la produducción");
        System.out.println("Se han almacenado "+huevos+" unidades de huevo");
        System.out.println("Se han almacenado "+lana+" unidades de lana");
        System.out.println("Se han almacenado "+trufa+" unidades de trufa");
        System.out.println("Se han almacenado "+leche+" unidades de leche");
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
