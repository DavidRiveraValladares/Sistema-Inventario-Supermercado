import java.util.ArrayList;

public class Nodo {

    // se guardan los datos de un producto y se apunta al siguiente nodo
    // asi funciona la lista enlazada: cada nodo sabe cual es el que sigue
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes; // rutas de las imagenes del producto
    private Nodo siguiente;

    public Nodo(String pNombre, double pPrecio, String pCategoria, String pFecha, int pCantidad) {
        nombre = pNombre;
        precio = pPrecio;
        categoria = pCategoria;
        fechaVencimiento = pFecha;
        cantidad = pCantidad;
        listaImagenes = new ArrayList<>();
        siguiente = null;
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    // setters
    public void setNombre(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    public void setPrecio(double nuevoPrecio) {
        precio = nuevoPrecio;
    }

    public void setCategoria(String nuevaCategoria) {
        categoria = nuevaCategoria;
    }

    public void setFechaVencimiento(String nuevaFecha) {
        fechaVencimiento = nuevaFecha;
    }

    public void setCantidad(int nuevaCantidad) {
        cantidad = nuevaCantidad;
    }

    public void setSiguiente(Nodo nuevoSiguiente) {
        siguiente = nuevoSiguiente;
    }

    // se agrega una ruta de imagen a este producto
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre +
                "\nPrecio: " + precio +
                "\nCategoria: " + categoria +
                "\nVencimiento: " + fechaVencimiento +
                "\nCantidad: " + cantidad +
                "\nImagenes: " + listaImagenes;
    }
}