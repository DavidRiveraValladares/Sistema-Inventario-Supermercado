public class Cliente {

    // cada cliente tiene su carrito y una prioridad para la cola de atencion
    // prioridad: 1 = Basico, 2 = Afiliado, 3 = Premium
    private String nombre;
    private String correo;
    private int prioridad;
    private String ubicacion; // donde vive (para el grafo)
    private ListaProductos carrito;

    public Cliente(String nombre, String correo, int prioridad, String ubicacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.prioridad = prioridad;
        this.ubicacion = ubicacion;
        this.carrito = new ListaProductos();
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    // setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // agregar un producto al carrito del cliente
    public void agregarAlCarrito(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        carrito.insertarNodoFinal(nombre, precio, categoria, fechaVencimiento, cantidad);
    }

    // convierte el numero de prioridad a texto para mostrarlo
    public String describePrioridad() {
        switch (prioridad) {
            case 1: return "Basico";
            case 2: return "Afiliado";
            case 3: return "Premium";
            default: return "Desconocido";
        }
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre +
                "\nCorreo: " + correo +
                "\nTipo de cliente: " + describePrioridad() + " (prioridad " + prioridad + ")" +
                "\nUbicacion: " + ubicacion;
    }
}