import java.util.Scanner;

public class Main {

    // punto de entrada del programa
    // por defecto se inicia la interfaz grafica swing
    // si se quiere probar en consola, se puede llamar a ejecutarConsola()
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new InterfazGrafica().setVisible(true);
        });
    }


    // version para consola
    public static void ejecutarConsola() {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda("TicoMarket", "Tienda_Central");

        // se cargan algunos datos del grafo para que no empiece vacio
        tienda.agregarVertice("Barrio_Amon");
        tienda.agregarVertice("Barrio_Otoya");
        tienda.agregarVertice("Escazu");
        tienda.agregarArista("Tienda_Central", "Barrio_Amon", 3);
        tienda.agregarArista("Barrio_Amon", "Barrio_Otoya", 2);
        tienda.agregarArista("Barrio_Otoya", "Escazu", 5);
        tienda.agregarArista("Tienda_Central", "Escazu", 12);

        int opcion;
        do {
            System.out.println("\n==================================================");
            System.out.println("         TICOMARKET - SISTEMA DE GESTION");
            System.out.println("==================================================");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Registrar cliente y llenar carrito");
            System.out.println("4. Mostrar cola de clientes");
            System.out.println("5. Atender siguiente cliente (factura + ruta)");
            System.out.println("6. Agregar vertice al grafo");
            System.out.println("7. Agregar arista al grafo");
            System.out.println("8. Visualizar grafo");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1: menuAgregarProducto(sc, tienda); break;
                case 2: System.out.println(tienda.mostrarInventario()); pausar(sc); break;
                case 3: menuRegistrarCliente(sc, tienda); break;
                case 4: System.out.println(tienda.mostrarColaClientes()); pausar(sc); break;
                case 5: System.out.println(tienda.atenderSiguienteCliente()); pausar(sc); break;
                case 6:
                    System.out.print("Nombre del vertice: ");
                    System.out.println(tienda.agregarVertice(sc.nextLine()));
                    pausar(sc);
                    break;
                case 7:
                    System.out.print("Vertice origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Vertice destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Distancia: ");
                    int dist = leerEntero(sc);
                    System.out.println(tienda.agregarArista(origen, destino, dist));
                    pausar(sc);
                    break;
                case 8: System.out.println(tienda.mostrarGrafo()); pausar(sc); break;
                case 9: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion invalida.");
            }

        } while (opcion != 9);

        sc.close();
    }

    // menu para agregar un producto al inventario
    private static void menuAgregarProducto(Scanner sc, Tienda tienda) {
        System.out.println("\n--- AGREGAR PRODUCTO ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: $");
        double precio = leerDouble(sc);

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Fecha de vencimiento: ");
        String fecha = sc.nextLine();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero(sc);

        System.out.println(tienda.agregarProducto(new Producto(nombre, precio, categoria, fecha, cantidad)));
        pausar(sc);
    }

    // menu para registrar un nuevo cliente
    private static void menuRegistrarCliente(Scanner sc, Tienda tienda) {
        if (tienda.getInventario().inventarioVacio()) {
            System.out.println("No hay productos en el inventario. Agregue productos primero.");
            pausar(sc);
            return;
        }

        System.out.println("\n--- REGISTRAR CLIENTE ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        int prioridad = 0;
        do {
            System.out.println("Tipo: 1.Basico  2.Afiliado  3.Premium");
            System.out.print("Seleccione (1-3): ");
            prioridad = leerEntero(sc);
        } while (prioridad < 1 || prioridad > 3);

        // se muestra el grafo para que el usuario sepa que vertices existen
        System.out.println(tienda.mostrarGrafo());
        System.out.print("Ubicacion del cliente (vertice del grafo): ");
        String ubicacion = sc.nextLine();

        Cliente cliente = new Cliente(nombre, correo, prioridad, ubicacion);
        menuLlenarCarrito(sc, tienda, cliente);

        System.out.println(tienda.agregarCliente(cliente));
        pausar(sc);
    }

    // menu para agregar productos al carrito de un cliente
    private static void menuLlenarCarrito(Scanner sc, Tienda tienda, Cliente cliente) {
        System.out.println("\n--- CARRITO ---");
        System.out.println(tienda.mostrarInventario());

        String respuesta;
        do {
            System.out.print("Producto a agregar: ");
            String nombreProducto = sc.nextLine();

            Producto encontrado = tienda.buscarProducto(nombreProducto);

            if (encontrado == null) {
                System.out.println("Producto no encontrado.");
            } else if (encontrado.getCantidad() == 0) {
                System.out.println("Producto agotado.");
            } else {
                System.out.print("Cantidad (disponible: " + encontrado.getCantidad() + "): ");
                int cant = leerEntero(sc);

                if (cant <= 0 || cant > encontrado.getCantidad()) {
                    System.out.println("Cantidad invalida.");
                } else {
                    cliente.agregarAlCarrito(
                            encontrado.getNombre(),
                            encontrado.getPrecio(),
                            encontrado.getCategoria(),
                            encontrado.getFechaVencimiento(),
                            cant
                    );
                    encontrado.setCantidad(encontrado.getCantidad() - cant);
                    System.out.println("Producto agregado al carrito.");
                }
            }

            System.out.print("Agregar otro? (s/n): ");
            respuesta = sc.nextLine();

        } while (respuesta.equalsIgnoreCase("s"));
    }

    // se pausa la consola hasta que el usuario presione enter
    public static void pausar(Scanner sc) {
        System.out.println("\nPresione ENTER para continuar...");
        sc.nextLine();
    }

    // se lee un entero con manejo de errores
    public static int leerEntero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero: ");
            }
        }
    }

    // se lee un double con manejo de errores
    public static double leerDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero: ");
            }
        }
    }
}