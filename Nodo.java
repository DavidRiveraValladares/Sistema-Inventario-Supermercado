import java.util.ArrayList;

public class Nodo {

    // Atributos del producto almacenado en el nodo
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;

    // Lista que almacena las rutas de las imágenes del producto
    private ArrayList<String> listaImagenes;

    // Referencia al siguiente nodo
    private Nodo siguiente;

    // Constructor: inicializa el nodo con los datos del producto
    public Nodo(String pNombre, double pPrecio, String pCategoria, String pFecha, int pCantidad) {
        nombre = pNombre;
        precio = pPrecio;
        categoria = pCategoria;
        fechaVencimiento = pFecha;
        cantidad = pCantidad;
        listaImagenes = new ArrayList<>(); // Se inicializa la lista de imágenes vacía
        siguiente = null;
    }

    // ===== Getters =====
    // Permiten acceder a los datos del nodo

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

    // Getter para obtener la lista de imágenes
    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    // ===== Setters =====
    // Permiten modificar los datos del nodo cuando sea necesario

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

    // Metodo que permite conectar un nodo con el siguiente
    public void setSiguiente(Nodo nuevoSiguiente) {
        siguiente = nuevoSiguiente;
    }

    // Metodo para agregar una imagen al producto
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    // Metodo toString para mostrar la información del nodo
    // Se utiliza cuando se imprime el objeto directamente
    public String toString() {
        return "\nNombre: " + nombre +
                "\nPrecio: " + precio +
                "\nCategoria: " + categoria +
                "\nVencimiento: " + fechaVencimiento +
                "\nCantidad: " + cantidad +
                "\nImagenes: " + listaImagenes;
    }
}