import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Almacen {
    protected List<HashMap<Semillas, Integer>> semillas;
    protected HashMap<Semillas, Integer> frutos;

    public Almacen() {
        this.semillas = new ArrayList<>();
        this.frutos = new HashMap<>();
    }

    public List<HashMap<Semillas, Integer>> getSemillas() {
        return semillas;
    }

    public void setSemillas(List<HashMap<Semillas, Integer>> semillas) {
        this.semillas = semillas;
    }

    public HashMap<Semillas, Integer> getFrutos() {
        return frutos;
    }

    public void setFrutos(HashMap<Semillas, Integer> frutos) {
        this.frutos = frutos;
    }

    public void agregarSemilla(Semillas semilla, Integer cant) {
        HashMap<Semillas, Integer> mapaSemillas = new HashMap<>();
        mapaSemillas.put(semilla, cant);
        this.semillas.add(mapaSemillas);
    }

    public Semillas elegirSemillaPlantar(){
    int index=1;
        Scanner sc=new Scanner(System.in);
        List<Semillas> lista = new ArrayList<>();
        for (HashMap<Semillas, Integer> mapa : this.semillas) {

            for (Semillas semilla: mapa.keySet()){

                System.out.println(index+". "+semilla.getNombre());
                index++;
                lista.add(semilla);
            }
        }
        System.out.println("Dime el numero de la semilla que quieres elegir: ");
        int num=sc.nextInt();

        if (num<lista.size()){
            return lista.get(num-1);
        }else {
            System.out.println("El numero no existe, se elige la primera");
            return lista.get(0);
        }
    }


}
