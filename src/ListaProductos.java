public class ListaProductos {

    // Clase que administra la lista enlazada de productos.
    // Contiene operaciones básicas de inserción, búsqueda, modificación y reporte

    private Nodo primero;

    public ListaProductos() {
        primero = null; // Inicialmente la lista está vacía
    }

    private boolean estaVacia() {
        return primero == null; // Verificar si la lista no tiene elementos
    }

    // Inserta un nuevo producto al inicio de la lista.
    public void insertarNodoInicio(String nombre, double precio, String categoria, String fecha, int cantidad) {
        Nodo nodoInsertar = new Nodo(nombre, precio, categoria, fecha, cantidad);
        nodoInsertar.setSiguiente(primero);
        primero = nodoInsertar;
    }

    // Inserta un nuevo producto al final de la lista
    public void insertarNodoFinal(String nombre, double precio, String categoria, String fecha, int cantidad) {
        Nodo nodoInsertar = new Nodo(nombre, precio, categoria, fecha, cantidad);

        if (estaVacia()) {
            primero = nodoInsertar;
            return;
        }

        Nodo temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente(); // Recorrer hasta el último nodo
        }
        temp.setSiguiente(nodoInsertar);
    }

    // Busca un producto por nombre dentro de la lista.
    public Nodo buscar(String nombre) {
        if (estaVacia()) {
            return null;
        }

        Nodo temp = primero;
        while (temp != null && !temp.getNombre().equalsIgnoreCase(nombre)) {
            temp = temp.getSiguiente();
        }

        return temp;
    }

    // Modifica el precio y cantidad de un producto existente.
    public boolean modificar(String nombreBuscar, double nuevoPrecio, int nuevaCantidad) {
        if (estaVacia()) {
            System.out.println("Lista vacia");
            return false;
        }

        Nodo temp = primero;
        while (temp != null && !temp.getNombre().equalsIgnoreCase(nombreBuscar)) {
            temp = temp.getSiguiente();
        }

        if (temp == null) {
            System.out.println("Producto no encontrado");
            return false;
        }

        temp.setPrecio(nuevoPrecio);
        temp.setCantidad(nuevaCantidad);

        System.out.println("Producto modificado correctamente");
        System.out.println(temp);

        return true;
    }

    // Elimina un producto de la lista por nombre.
    public boolean eliminar(String nombre) {
        if (estaVacia()) {
            return false;
        }

        // Caso especial: el producto a eliminar es el primero
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

        if (actual == null) {
            return false;
        }

        anterior.setSiguiente(actual.getSiguiente());
        return true;
    }

    // Permite agregar una imagen a un producto existente.
    public boolean agregarImagenAProducto(String nombre, String rutaImagen) {
        Nodo producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.agregarImagen(rutaImagen);
        return true;
    }

    // Muestra todos los productos almacenados en la lista.
    public void imprimirListaCompleta() {
        if (estaVacia()) {
            System.out.println("Inventario vacio");
            return;
        }

        Nodo temp = primero;

        while (temp != null) {
            System.out.println(temp);
            System.out.println("---------------------------");
            temp = temp.getSiguiente();
        }
    }

    // Genera un reporte de costos mostrando subtotales y el total acumulado.
    public void imprimirReporteCostos() {
        if (estaVacia()) {
            System.out.println("Inventario vacio");
            return;
        }

        Nodo temp = primero;
        double totalGlobal = 0;

        while (temp != null) {
            double subtotal = temp.getPrecio() * temp.getCantidad();
            totalGlobal += subtotal;

            System.out.println("\nProducto: " + temp.getNombre());
            System.out.println("Subtotal: " + subtotal);

            temp = temp.getSiguiente();
        }

        System.out.println("\nTOTAL ACUMULADO: " + totalGlobal);
    }

    // Permite modificar cualquier atributo del producto de forma interactiva
    public boolean modificarProductoInteractivo(String nombreBuscar, java.util.Scanner sc) {

        if (estaVacia()) {
            System.out.println("Lista vacia");
            return false;
        }

        Nodo temp = primero;

        while (temp != null && !temp.getNombre().equalsIgnoreCase(nombreBuscar)) {
            temp = temp.getSiguiente();
        }

        if (temp == null) {
            System.out.println("Producto no encontrado");
            return false;
        }

        int opcion;
        do {
            System.out.println("\n¿Que desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Precio");
            System.out.println("3. Categoria");
            System.out.println("4. Fecha de vencimiento");
            System.out.println("5. Cantidad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Nuevo nombre: ");
                    temp.setNombre(sc.nextLine());
                    System.out.println("Nombre actualizado");
                    break;

                case 2:
                    System.out.print("Nuevo precio: ");
                    temp.setPrecio(sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Precio actualizado");
                    break;

                case 3:
                    System.out.print("Nueva categoria: ");
                    temp.setCategoria(sc.nextLine());
                    System.out.println("Categoria actualizada");
                    break;

                case 4:
                    System.out.print("Nueva fecha de vencimiento: ");
                    temp.setFechaVencimiento(sc.nextLine());
                    System.out.println("Fecha actualizada");
                    break;

                case 5:
                    System.out.print("Nueva cantidad: ");
                    temp.setCantidad(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Cantidad actualizada");
                    break;

                case 6:
                    System.out.println("Finalizando modificacion...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 6);

        return true;
    }
}