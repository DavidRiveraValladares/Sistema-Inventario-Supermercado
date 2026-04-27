public class ListaProductos {

    // lista enlazada para el carrito de compras de cada cliente
    // tambien se usa para mostrar los costos en la factura
    private Nodo primero;

    public ListaProductos() {
        primero = null;
    }

    private boolean estaVacia() {
        return primero == null;
    }

    // se agrega un producto al inicio de la lista
    public void insertarNodoInicio(String nombre, double precio, String categoria, String fecha, int cantidad) {
        Nodo nodoInsertar = new Nodo(nombre, precio, categoria, fecha, cantidad);
        nodoInsertar.setSiguiente(primero);
        primero = nodoInsertar;
    }

    // se agrega un producto al final de la lista
    public void insertarNodoFinal(String nombre, double precio, String categoria, String fecha, int cantidad) {
        Nodo nodoInsertar = new Nodo(nombre, precio, categoria, fecha, cantidad);

        if (estaVacia()) {
            primero = nodoInsertar;
            return;
        }

        // se recorre hasta el ultimo nodo
        Nodo temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        temp.setSiguiente(nodoInsertar);
    }

    // se busca un producto por nombre (sin diferenciar mayusculas/minusculas)
    public Nodo buscar(String nombre) {
        if (estaVacia()) return null;

        Nodo temp = primero;
        while (temp != null && !temp.getNombre().equalsIgnoreCase(nombre)) {
            temp = temp.getSiguiente();
        }
        return temp;
    }

    // se elimina un producto de la lista por su nombre
    public boolean eliminar(String nombre) {
        if (estaVacia()) return false;

        // caso especial: el primero es el que se quiere eliminar
        if (primero.getNombre().equalsIgnoreCase(nombre)) {
            primero = primero.getSiguiente();
            return true;
        }

        Nodo actual = primero;
        Nodo anterior = null;

        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return false; // no se encontro

        anterior.setSiguiente(actual.getSiguiente());
        return true;
    }

    // se asigna una imagen a un producto que ya esta en la lista
    public boolean agregarImagenAProducto(String nombre, String rutaImagen) {
        Nodo producto = buscar(nombre);
        if (producto == null) return false;

        producto.agregarImagen(rutaImagen);
        return true;
    }

    // verifica si el carrito esta vacio
    public boolean carritoVacio() {
        return estaVacia();
    }

    // devuelve todos los productos de la lista como texto
    public String obtenerListaTexto() {
        if (estaVacia()) return "Inventario vacio.\n";

        StringBuilder sb = new StringBuilder();
        Nodo temp = primero;

        while (temp != null) {
            sb.append(temp).append("\n---------------------------\n");
            temp = temp.getSiguiente();
        }
        return sb.toString();
    }

    // genera el reporte de costos: cada producto con su subtotal y el total final
    public String obtenerReporteCostosTexto() {
        if (estaVacia()) return "Carrito vacio.\n";

        StringBuilder sb = new StringBuilder();
        Nodo temp = primero;
        double totalGlobal = 0;

        while (temp != null) {
            double subtotal = temp.getPrecio() * temp.getCantidad();
            totalGlobal += subtotal;

            sb.append("  - ").append(temp.getNombre())
                    .append(" x").append(temp.getCantidad())
                    .append(" @ $").append(String.format("%.2f", temp.getPrecio()))
                    .append(" = $").append(String.format("%.2f", subtotal))
                    .append("\n");

            temp = temp.getSiguiente();
        }

        sb.append("--------------------------------------------------\n");
        sb.append("TOTAL: $").append(String.format("%.2f", totalGlobal)).append("\n");
        return sb.toString();
    }

    // metodos que imprimen directamente en consola (se mantienen por compatibilidad)
    public void imprimirListaCompleta() {
        System.out.println(obtenerListaTexto());
    }

    public void imprimirReporteCostos() {
        System.out.println(obtenerReporteCostosTexto());
    }
}