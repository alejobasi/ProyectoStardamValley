import com.arquitecturajava.singleton.SingletonProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Granja implements Serializable {
    private int diaJuego;
    private Tienda tienda;
    private double presupuesto;
    private Estaciones estacion;
    private int diasPorEstacion;
    private List<Semillas>semillasDisponibles;
    private Map<Semillas,List<Estaciones>> semillasPorEstacion;
    private Map semillasPorId;
    private Almacen almacen;


    public Granja() {
        diaJuego=1;
        tienda=new Tienda();
        semillasPorEstacion=GestionFSemillas.semillasporEstacion(GestionFSemillas.cargarSemillas());
        almacen=new Almacen();

    }

    public int getDiaJuego() {
        return diaJuego;
    }

    public void setDiaJuego(int diaJuego) {
        this.diaJuego = diaJuego;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Estaciones getEstacion() {
        return estacion;
    }

    public void setEstacion(Estaciones estacion) {
        this.estacion = estacion;
    }

    public int getDiasPorEstacion() {
        return diasPorEstacion;
    }

    public void setDiasPorEstacion(int diasPorEstacion) {
        this.diasPorEstacion = diasPorEstacion;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public List<Semillas> getSemillasDisponibles() {
        return semillasDisponibles;
    }

    public void setSemillasDisponibles(List<Semillas> semillasDisponibles) {
        this.semillasDisponibles = semillasDisponibles;
    }

    public Map<Semillas, List<Estaciones>> getSemillasPorEstacion() {
        return semillasPorEstacion;
    }

    public void setSemillasPorEstacion(Map<Semillas, List<Estaciones>> semillasPorEstacion) {
        this.semillasPorEstacion = semillasPorEstacion;
    }

    public Map getSemillasPorId() {
        return semillasPorId;
    }

    public void setSemillasPorId(Map semillasPorId) {
        this.semillasPorId = semillasPorId;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    /**
     * Sirve para vender los frutos que tenemos en el Almacen y ganar dinero,
     * lo unico que hace es recorrer el HashMap de Frutos y multiplicar la cantidad que se tiene de cada Fruto por el precio de venta
     */
    public void venderFrutos(){

        List<Semillas> semillasEliminar = new ArrayList<>();
        for (Map.Entry<Semillas, Integer> entry : this.getAlmacen().getFrutos().entrySet()) {

            Semillas semilla = entry.getKey();
            int cantidad = entry.getValue();

            this.setPresupuesto(this.presupuesto+semilla.getPrecioVenta()*cantidad);


        semillasEliminar.add(semilla);
        }
        for (Semillas semilla : semillasEliminar) {
            this.getAlmacen().getFrutos().remove(semilla);
        }
    }

    /**
     * Lo unico que hace es cambiar a la siguiente, se ejecuta cuando es justo el tiempo
     */
    public void cambiarEstacion(){
        Estaciones estacion=getEstacion();

        switch (estacion){
            case PRIMAVERA:
                setEstacion(Estaciones.VERANO);
                break;
           case VERANO:
                setEstacion(Estaciones.OTOÑO);
                break;
            case OTOÑO :
                setEstacion(Estaciones.INVIERNO);
                break;
            case INVIERNO:
                setEstacion(Estaciones.PRIMAVERA);
                break;


        }
    }
    /**
     * Ejecuta los metodos para mostra por pantalla todos los valores de la granja
     * @param granja
     */
    public static void mostrarGranja(Granja granja){
        System.out.println("INFORMACIÓN DE LA GRNAJA: ");
        System.out.println(" - Día de juego: "+granja.getDiaJuego());
        System.out.println(" - Dinero disponible: "+granja.getPresupuesto());
        System.out.println(" - Estación: "+granja.getEstacion());
        System.out.print(" - Semillas en Venta: ");Tienda.mostrarTiendaDiaria(granja);
        System.out.println("");
        System.out.println(" - Frutos en almacén : ");granja.getAlmacen().verFrutos();
        System.out.println(" - Estado del huerto: ");GestionFHuerto.mostrarHuerto();

    }

}






