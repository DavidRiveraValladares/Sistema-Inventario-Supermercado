public class Cliente {

    // Representa a un cliente de la tienda.
    // Prioridades: 1 = Basico, 2 = Afiliado, 3 = Premium

    //Atributos
    private String nombre;
    private String correo;
    private int prioridad;
    private ListaProductos carrito;

    //Constructor
    public Cliente(String nombre, String correo, int prioridad) {
        this.nombre = nombre;
        this.correo = correo;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    //Getters
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public int getPrioridad() { return prioridad; }
    public ListaProductos getCarrito() { return carrito; }

    //Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    public void agregarAlCarrito(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        carrito.insertarNodoFinal(nombre, precio, categoria, fechaVencimiento, cantidad);
    }

    public String describePrioridad() {
        switch (prioridad) {
            case 1: return "Basico";
            case 2: return "Afiliado";
            case 3: return "Premium";
            default: return "Desconocido";
        }
    }

    //toString()
    public String toString() {
        return "\nNombre: " + nombre +
                "\nCorreo: " + correo +
                "\nTipo de cliente: " + describePrioridad() + " (prioridad " + prioridad + ")";
    }
}