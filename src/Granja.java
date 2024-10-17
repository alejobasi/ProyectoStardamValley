import com.arquitecturajava.singleton.SingletonProperties;

import java.util.Map;
import java.util.Scanner;

public class Granja {
    private int diaJuego;
    private Tienda tienda;
    private double presupuesto;
    private Estaciones estacion;
    private int diasPorEstacion;
    private Map semillasPorEstacion;

    public Granja() {
        tienda=new Tienda();
        diaJuego=0;

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

}






