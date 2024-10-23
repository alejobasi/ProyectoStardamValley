import java.util.*;

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
        boolean existe = false;
        for (HashMap<Semillas, Integer> mapa : semillas) {
            if (mapa.containsKey(semilla)) {
                int cantidadActual = mapa.get(semilla);
                mapa.put(semilla, cantidadActual + cant);
                existe = true;
                break;
            }
        }


        if (!existe) {
            HashMap<Semillas, Integer> nuevoMapa = new HashMap<>();
            nuevoMapa.put(semilla, cant);
            semillas.add(nuevoMapa);
        }
    }


    public Semillas elegirSemillaPlantar() {
        int index = 1;
        Scanner sc = new Scanner(System.in);
        List<Semillas> lista = new ArrayList<>();

        // Llenar la lista y mostrar las opciones
        for (HashMap<Semillas, Integer> mapa : this.semillas) {
            for (Semillas semilla : mapa.keySet()) {
                System.out.println(index + ". " + semilla.getNombre());
                index++;
                lista.add(semilla);
            }
        }


        System.out.println("Dime el número de la semilla que quieres elegir: ");
        int num = sc.nextInt();


        if (num > 0 && num <= lista.size()) {
            restarSemillaAlmacen(lista.get(num - 1));
            return lista.get(num - 1);
        } else {
            System.out.println("El número no existe, se elige la primera");
            return lista.get(0);
        }
    }

    public void restarSemillaAlmacen(Semillas sem){
int cantidad;
boolean salir=false;
        for (HashMap<Semillas, Integer> mapa : this.semillas) {

            for (Semillas semilla: mapa.keySet()){

                if (mapa.containsKey(sem)&&salir==false){

                    int resta=mapa.get(sem);

                    resta=resta-1;

                    if (resta>0){
                        mapa.put(sem,resta);

                    }else {
                        mapa.remove(sem);
                    }
                    salir=true;
                }

            }
        }
    }

    public void anadirFrutoAlmacen(int idSemilla){
        Random random = new Random();
        List<Semillas>listaSemillas=GestionFSemillas.cargarSemillas();

        for (Semillas sem:listaSemillas){
            if (sem.getId()==idSemilla){
                int posiblesFrutos=sem.getCantidad();
                int cantidad=random.nextInt(posiblesFrutos)+1;

                if (this.frutos.containsKey(sem)) {
                    int cantidadActual = this.frutos.get(sem);

                    this.frutos.put(sem, cantidadActual + cantidad);
                } else {
                    this.frutos.put(sem, cantidad);
                }

            }
        }

    }

    public void verFrutos() {


        List<Semillas> lista = new ArrayList<>();
        for (Map.Entry<Semillas, Integer> entry : this.frutos.entrySet()) {

            Semillas semilla = entry.getKey();
            int cantidad = entry.getValue();


            System.out.println("[" + semilla.getNombre() + " - " + cantidad+"]");


        }


    }



}
