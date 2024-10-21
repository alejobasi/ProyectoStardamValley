import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Almacen {
    protected List<HashMap<Semillas, Integer>> semillas;
    protected HashMap<Semillas, Integer> frutos;

    public Almacen() {
        this.semillas = new ArrayList<>();
        this.frutos = new HashMap<>();
    }

    public void agregarSemilla(Semillas semilla, Integer cant) {
        HashMap<Semillas, Integer> mapaSemillas = new HashMap<>();
        mapaSemillas.put(semilla, cant);
        this.semillas.add(mapaSemillas);
    }


}
