import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Tienda {

   private static int NUM_SEMILLAS_TIENDA=3;
    private HashSet<Semillas> tiendaDiaria;

    public Tienda() {
        this.tiendaDiaria = new HashSet<>();

    }

    public HashSet<Semillas> getTiendaDiaria() {
        return tiendaDiaria;
    }

    public void setTiendaDiaria(HashSet<Semillas> tiendaDiaria) {
        this.tiendaDiaria = tiendaDiaria;
    }

    public static void generarNuevaTienda(Granja granja){


List<Semillas>disponibles=granja.getSemillasDisponibles();
        Random random = new Random();
        disponibles.sort((a, b) -> random.nextInt(3 - 1));
        HashSet<Semillas>listaFinal=new HashSet<>();

        while (listaFinal.size()<3){

            int semillaAleatoria=random.nextInt(disponibles.size());
            listaFinal.add(disponibles.get(semillaAleatoria));

        }
        for (Semillas s: listaFinal){
            if (s.getPrecioCompra()*3>granja.getPresupuesto()){
                listaFinal.remove(s);
            }
        }

        granja.getTienda().setTiendaDiaria(listaFinal);


    }

    public static void comprarTiendaDiaria(Granja granja){

        System.out.println("Tienda Diaria:");
        System.out.println("(Por cada compra se adquieren 3 semillas)");




        Iterator<Semillas> iterator = granja.getTienda().getTiendaDiaria().iterator();

       while (iterator.hasNext()){
        Semillas semilla=iterator.next();
            if (semilla.getPrecioCompra()*3>granja.getPresupuesto()){
                System.out.println("Semilla: "+ semilla.getNombre() +" Precio: "+semilla.getPrecioCompra() * NUM_SEMILLAS_TIENDA+" - No tienes suficiente presupuesto.");
            }
            else {
                System.out.println(" Semilla: " + semilla.getNombre() + " Precio: " + semilla.getPrecioCompra() * NUM_SEMILLAS_TIENDA+": Comprada");
                granja.setPresupuesto(granja.getPresupuesto()-semilla.getPrecioCompra()*3);
                granja.getAlmacen().agregarSemilla(semilla,NUM_SEMILLAS_TIENDA);

                iterator.remove();
            }
          }
        System.out.println("Presupuesto actual: " + granja.getPresupuesto());





        }

        public static void mostrarTiendaDiaria(Granja granja){
            Iterator<Semillas> iterator = granja.getTienda().getTiendaDiaria().iterator();

            while (iterator.hasNext()){
                Semillas semilla=iterator.next();

                    System.out.print(semilla.getNombre() +", ");






            }
        }



    public static void tienda(Granja granja){

        comprarTiendaDiaria(granja);

    }
}
