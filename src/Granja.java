import com.arquitecturajava.singleton.SingletonProperties;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Granja {
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


}






