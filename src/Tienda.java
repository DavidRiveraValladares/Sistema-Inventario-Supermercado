public class Tienda {

    // clase principal que une todo el sistema
    // se conecta el inventario (arbol BST), la cola de clientes y el grafo de ubicaciones
    private String nombre;
    private String ubicacion;         // vertice del grafo donde esta la tienda
    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    private Grafo grafo;

    public Tienda(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
        this.grafo = new Grafo();
        // la tienda ya existe en el grafo desde el inicio
        this.grafo.agregarVertice(ubicacion);
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    // operaciones del inventario

    // se agrega un producto al inventario
    public String agregarProducto(Producto producto) {
        boolean insertado = inventario.insertar(producto);
        if (insertado) {
            return "Producto \"" + producto.getNombre() + "\" agregado al inventario.";
        }
        return "Ya existe un producto con ese nombre en el inventario.";
    }

    // se busca un producto por nombre y se devuelve (null si no existe)
    public Producto buscarProducto(String nombre) {
        NodoArbol nodoEncontrado = inventario.buscar(nombre);
        if (nodoEncontrado == null) return null;
        return nodoEncontrado.getProducto();
    }

    // se devuelve el inventario completo como texto
    public String mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- INVENTARIO DE ").append(nombre.toUpperCase()).append(" ---\n");

        if (inventario.inventarioVacio()) {
            sb.append("El inventario esta vacio.\n");
        } else {
            sb.append(inventario.obtenerInventarioTexto(inventario.getRaiz()));
        }

        return sb.toString();
    }

    // operaciones de clientes

    // se agrega un cliente a la cola (se valida que su ubicacion exista en el grafo)
    public String agregarCliente(Cliente cliente) {
        if (!grafo.existeVertice(cliente.getUbicacion())) {
            return "ERROR: La ubicacion \"" + cliente.getUbicacion() + "\" no existe en el grafo. " +
                    "El cliente no puede ser registrado.";
        }
        colaClientes.insertar(cliente);
        return "Cliente \"" + cliente.getNombre() + "\" agregado a la cola de atencion.";
    }

    // se devuelve la lista de clientes en espera como texto
    public String mostrarColaClientes() {
        return colaClientes.obtenerColaTexto();
    }

    // se atiende al siguiente cliente de la cola:
    // se genera su factura y se calcula la ruta de entrega con Dijkstra
    public String atenderSiguienteCliente() {
        Cliente cliente = colaClientes.remover();

        if (cliente == null) {
            return "La cola de clientes esta vacia. No hay clientes para atender.";
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append(generarFactura(cliente));
        resultado.append("\n");
        resultado.append(calcularRutaMasCorta(cliente));
        return resultado.toString();
    }

    // se genera el texto de la factura para un cliente
    public String generarFactura(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("                    FACTURA\n");
        sb.append("==================================================\n");
        sb.append("Tienda:   ").append(nombre).append("\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Cliente:  ").append(cliente.getNombre()).append("\n");
        sb.append("Correo:   ").append(cliente.getCorreo()).append("\n");
        sb.append("Tipo:     ").append(cliente.describePrioridad()).append("\n");
        sb.append("Ubicacion:").append(cliente.getUbicacion()).append("\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Productos adquiridos:\n");
        sb.append(cliente.getCarrito().obtenerReporteCostosTexto());
        sb.append("==================================================\n");
        return sb.toString();
    }

    // se calcula y devuelve la ruta mas corta desde la tienda hasta el cliente
    public String calcularRutaMasCorta(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append("RUTA DE ENTREGA (Dijkstra)\n");
        sb.append("Desde: ").append(ubicacion).append("  ->  Hacia: ").append(cliente.getUbicacion()).append("\n");
        sb.append(grafo.calcularRutaMasCorta(ubicacion, cliente.getUbicacion())).append("\n");
        sb.append("--------------------------------------------------\n");
        return sb.toString();
    }

    // operaciones del grafo

    public String agregarVertice(String nombreVertice) {
        boolean agregado = grafo.agregarVertice(nombreVertice);
        if (agregado) return "Vertice \"" + nombreVertice + "\" agregado al grafo.";
        return "El vertice \"" + nombreVertice + "\" ya existe en el grafo.";
    }

    public String agregarArista(String origen, String destino, int peso) {
        boolean agregada = grafo.agregarArista(origen, destino, peso);
        if (agregada) return "Arista agregada: " + origen + " <-> " + destino + " (distancia: " + peso + ")";
        return "No se pudo agregar la arista. Verifique que ambos vertices existan y que la conexion no este duplicada.";
    }

    public String mostrarGrafo() {
        return "--- GRAFO DE UBICACIONES ---\n" + grafo.mostrarGrafo();
    }
}