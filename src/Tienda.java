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
import java.io.Serializable;
import java.util.*;

public class Tienda implements Serializable {

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

    /**
     * Genera una nueva tienda, con las semillas disponibles de esa estación
     * @param granja
     */
    public static void generarNuevaTienda(Granja granja){


List<Semillas>disponibles=granja.getSemillasDisponibles();
        Random random = new Random();
        disponibles.sort((a, b) -> random.nextInt(3 - 1));

        HashSet<Semillas>listaFinal=new HashSet<>();
        HashSet<Semillas>listaVacia=new HashSet<>();
        granja.getTienda().setTiendaDiaria(listaVacia);


        while (listaFinal.size()<3){

            int semillaAleatoria=random.nextInt(disponibles.size());
            listaFinal.add(disponibles.get(semillaAleatoria));

        }

        Iterator<Semillas> iterator = listaFinal.iterator();
        while (iterator.hasNext()) {
            Semillas s = iterator.next();
            if (s.getPrecioCompra() * 3 > granja.getPresupuesto()) {
                iterator.remove();
            }
        }

        granja.getTienda().setTiendaDiaria(listaFinal);


    }

    /**
     * Compra todos las semillas de la tienda, que podamos con nuestro presupuesto actual
     * @param granja
     */
    public static void comprarTiendaDiaria(Granja granja){
        int numSemillas=GestionFicheros.sacarColumnas();
        System.out.println("Tienda Diaria:");
        System.out.println("(Por cada compra se adquieren la cantidad de columnas )");




        Iterator<Semillas> iterator = granja.getTienda().getTiendaDiaria().iterator();

       while (iterator.hasNext()){
        Semillas semilla=iterator.next();
            if (semilla.getPrecioCompra()*3>granja.getPresupuesto()){
                System.out.println("Semilla: "+ semilla.getNombre() +" Precio: "+semilla.getPrecioCompra() * numSemillas+" - No tienes suficiente presupuesto.");
            }
            else {
                System.out.println(" Semilla: " + semilla.getNombre() + " Precio: " + semilla.getPrecioCompra() * numSemillas+": Comprada");
                granja.setPresupuesto(granja.getPresupuesto()-semilla.getPrecioCompra()*3);
                granja.getAlmacen().agregarSemilla(semilla,numSemillas);

                iterator.remove();
            }
          }
        System.out.println("Presupuesto actual: " + granja.getPresupuesto());





        }

    /**
     * Muestra las semillas que están en venta en la Tienda
     * @param granja
     */
    public static void mostrarTiendaDiaria(Granja granja){
            Iterator<Semillas> iterator = granja.getTienda().getTiendaDiaria().iterator();

            while (iterator.hasNext()){
                Semillas semilla=iterator.next();

                    System.out.print(semilla.getNombre() +", ");






            }
        }




}
