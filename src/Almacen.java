import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Almacen {
    protected List<HashMap<Semillas, Integer>> semillas;
    protected HashMap<String, Integer> frutos;

    public Almacen() {
        this.semillas = new ArrayList<>();
        this.frutos = new HashMap<>();
    }

    public void agregarSemilla(Semillas semilla, Integer cantidad) {
        HashMap<Semillas, Integer> mapaSemillas = new HashMap<>();
        mapaSemillas.put(semilla, cantidad);
        this.semillas.add(mapaSemillas);
    }


}
