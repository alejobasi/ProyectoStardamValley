public class Granja {

    protected Tienda tienda;

    public Granja() {
        tienda=new Tienda();
        tienda.cargarSemillas();
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
