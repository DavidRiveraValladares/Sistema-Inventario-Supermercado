import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda("TicoMarket");

        int opcion;

        do {
            System.out.println("\n==================================================");
            System.out.println("             TICOMARKET");
            System.out.println("==================================================");
            System.out.println("");
            System.out.println("        _____________________________");
            System.out.println("       /                            /|");
            System.out.println("      /____________________________/ |");
            System.out.println("      |                            |  |");
            System.out.println("      |    SISTEMA DE VENTAS       |  |");
            System.out.println("      |____________________________|  /");
            System.out.println("       \\___________________________| /");
            System.out.println("                    O        O");
            System.out.println("");
            System.out.println("==================================================");
            System.out.println("        SISTEMA DE GESTION - SOFT 10");
            System.out.println("==================================================");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Registrar cliente y llenar carrito");
            System.out.println("4. Mostrar cola de clientes");
            System.out.println("5. Atender siguiente cliente (generar factura)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero(sc);

            switch (opcion) {

                case 1:
                    menu_agregarProducto(sc, tienda);
                    break;

                case 2:
                    tienda.mostrarInventario();
                    pausar(sc);
                    break;

                case 3:
                    menu_registrarCliente(sc, tienda);
                    break;

                case 4:
                    tienda.mostrarColaClientes();
                    pausar(sc);
                    break;

                case 5:
                    tienda.atenderSiguienteCliente();
                    pausar(sc);
                    break;

                case 6:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpcion invalida. Intente de nuevo.");
                    pausar(sc);
            }

        } while (opcion != 6);

        sc.close();
    }

    private static void menu_agregarProducto(Scanner sc, Tienda tienda) {

        System.out.println("\n--- AGREGAR PRODUCTO AL INVENTARIO ---");

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: $");
        double precio = leerDouble(sc);

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Fecha de vencimiento: ");
        String fecha = sc.nextLine();

        System.out.print("Cantidad disponible: ");
        int cantidad = leerEntero(sc);

        Producto producto = new Producto(nombre, precio, categoria, fecha, cantidad);
        tienda.agregarProducto(producto);

        pausar(sc);
    }

    private static void menu_registrarCliente(Scanner sc, Tienda tienda) {

        if (tienda.getInventario().inventarioVacio()) {
            System.out.println("\nNo hay productos en el inventario. Agregue productos primero.");
            pausar(sc);
            return;
        }

        System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");

        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.print("Correo electronico: ");
        String correo = sc.nextLine();

        int prioridad = 0;
        do {
            System.out.println("Tipo de cliente:");
            System.out.println("  1. Basico");
            System.out.println("  2. Afiliado");
            System.out.println("  3. Premium");
            System.out.print("Seleccione (1-3): ");
            prioridad = leerEntero(sc);

            if (prioridad < 1 || prioridad > 3) {
                System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
            }
        } while (prioridad < 1 || prioridad > 3);

        Cliente cliente = new Cliente(nombre, correo, prioridad);

        menu_llenarCarrito(sc, tienda, cliente);

        tienda.agregarCliente(cliente);
        System.out.println("\nCliente registrado y en cola de atencion.");

        pausar(sc);
    }

    private static void menu_llenarCarrito(Scanner sc, Tienda tienda, Cliente cliente) {

        System.out.println("\n--- LLENADO DE CARRITO ---");
        System.out.println("Inventario disponible:");
        tienda.mostrarInventario();

        String respuesta;
        do {
            System.out.print("\nIngrese el nombre del producto a agregar al carrito: ");
            String nombreProducto = sc.nextLine();

            Producto encontrado = tienda.buscarProducto(nombreProducto);

            if (encontrado == null) {
                System.out.println("Producto no encontrado en el inventario. Intente con otro nombre.");
            } else if (encontrado.getCantidad() == 0) {
                System.out.println("Lo sentimos, ese producto esta agotado en el inventario.");
            } else {
                System.out.print("Cantidad a comprar (disponible: " + encontrado.getCantidad() + "): ");
                int cantidadComprar = leerEntero(sc);

                if (cantidadComprar <= 0 || cantidadComprar > encontrado.getCantidad()) {
                    System.out.println("Cantidad invalida. No se agrego el producto.");
                } else {
                    cliente.agregarAlCarrito(
                            encontrado.getNombre(),
                            encontrado.getPrecio(),
                            encontrado.getCategoria(),
                            encontrado.getFechaVencimiento(),
                            cantidadComprar
                    );
                    // Se descuenta del inventario al momento de agregar al carrito
                    encontrado.setCantidad(encontrado.getCantidad() - cantidadComprar);
                    System.out.println("Producto \"" + encontrado.getNombre() + "\" agregado al carrito.");
                }
            }

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            respuesta = sc.nextLine();

        } while (respuesta.equalsIgnoreCase("s"));

        System.out.println("\nCarrito finalizado para el cliente \"" + cliente.getNombre() + "\".");
    }

    // Metodo auxiliar para pausar la consola hasta que el usuario presione Enter
    public static void pausar(Scanner sc) {
        System.out.println("\nPresione ENTER para continuar...");
        sc.nextLine();
    }

    // Lee un entero usando nextLine() para no dejar saltos de linea en el buffer
    public static int leerEntero(Scanner sc) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                valor = Integer.parseInt(sc.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida, ingrese un numero entero: ");
            }
        }
        return valor;
    }

    // Lee un double usando nextLine() para no dejar saltos de linea en el buffer
    public static double leerDouble(Scanner sc) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida, ingrese un numero: ");
            }
        }
        return valor;
    }
}