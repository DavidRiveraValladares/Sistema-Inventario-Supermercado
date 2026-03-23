public class Tienda {

    // Clase central del sistema, conecta el inventario con la cola de clientes

    //Atributos
    private String nombre;
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    //Constructor
    public Tienda(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }

    //Getters
    public String getNombre() { return nombre; }
    public ArbolProductos getInventario() { return inventario; }
    public ColaClientes getColaClientes() { return colaClientes; }

    //Operaciones
    public void agregarProducto(Producto producto) {
        boolean insertado = inventario.insertar(producto);
        if (insertado) {
            System.out.println("Producto \"" + producto.getNombre() + "\" agregado al inventario.");
        }
    }

    public Producto buscarProducto(String nombre) {
        NodoArbol nodoEncontrado = inventario.buscar(nombre);
        if (nodoEncontrado == null) return null;
        return nodoEncontrado.getProducto();
    }

    public void mostrarInventario() {
        System.out.println("\n--- INVENTARIO DE " + nombre.toUpperCase() + " ---");
        if (inventario.inventarioVacio()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        inventario.enOrden(inventario.getRaiz());
    }

    public void agregarCliente(Cliente cliente) {
        colaClientes.insertar(cliente);
    }

    // Atiende al siguiente cliente segun prioridad e imprime su factura
    public void atenderSiguienteCliente() {
        Cliente cliente = colaClientes.remover();

        if (cliente == null) return;

        System.out.println("\n==================================================");
        System.out.println("                    FACTURA");
        System.out.println("==================================================");
        System.out.println("Tienda: " + nombre);
        System.out.println("--------------------------------------------------");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Correo:  " + cliente.getCorreo());
        System.out.println("Tipo:    " + cliente.describePrioridad());
        System.out.println("--------------------------------------------------");
        System.out.println("Productos adquiridos:");
        cliente.getCarrito().imprimirReporteCostos();
        System.out.println("==================================================");
    }

    public void mostrarColaClientes() {
        System.out.println("\n--- COLA DE CLIENTES ---");
        colaClientes.imprimirCola();
    }
}