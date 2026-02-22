import java.util.Scanner;

public class Main {

    // Clase principal que contiene el menú interactivo para gestionar el inventario.
    // Permite al usuario insertar, buscar, modificar y generar reportes de productos.

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ListaProductos lista = new ListaProductos();

        int opcion;

        do {
            // Menú principal
            System.out.println("==================================================");
            System.out.println("                 SUPERMERCADO");
            System.out.println("==================================================");
            System.out.println("");
            System.out.println("        _____________________________");
            System.out.println("       /                            /|");
            System.out.println("      /____________________________/ |");
            System.out.println("      |                            |  |");
            System.out.println("      |         INVENTARIO         |  |");
            System.out.println("      |____________________________|  /");
            System.out.println("       \\___________________________| /");
            System.out.println("                    O        O");
            System.out.println("");
            System.out.println("==================================================");
            System.out.println("        SISTEMA DE INVENTARIO - SOFT 10");
            System.out.println("==================================================");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Buscar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Reporte de costos");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Agregar imagen a producto");
            System.out.println("8. Mostrar todos los productos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                case 2:
                    // Solicito los datos del producto al usuario
                    System.out.println("\n--- INGRESO DE PRODUCTO ---");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();

                    System.out.print("Fecha de vencimiento: ");
                    String fecha = sc.nextLine();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    if (opcion == 1) {
                        lista.insertarNodoInicio(nombre, precio, categoria, fecha, cantidad);
                        System.out.println("\nProducto agregado al INICIO correctamente.");
                    } else {
                        lista.insertarNodoFinal(nombre, precio, categoria, fecha, cantidad);
                        System.out.println("\nProducto agregado al FINAL correctamente.");
                    }

                    pausar(sc);
                    break;

                case 3:
                    // Búsqueda de producto por nombre
                    System.out.println("\n--- BUSQUEDA DE PRODUCTO ---");
                    System.out.print("Ingrese el nombre a buscar: ");
                    Nodo encontrado = lista.buscar(sc.nextLine());

                    if (encontrado != null) {
                        System.out.println("\nProducto encontrado:");
                        System.out.println(encontrado);
                    } else {
                        System.out.println("\nNo se encontró el producto.");
                    }

                    pausar(sc);
                    break;

                case 4:
                    // Modificación de producto existente
                    System.out.println("\n--- MODIFICACION DE PRODUCTO ---");
                    System.out.print("Nombre del producto a modificar: ");
                    String nombreBuscar = sc.nextLine();

                    boolean modificado = lista.modificarProductoInteractivo(nombreBuscar, sc);

                    if (modificado) {
                        System.out.println("\nModificacion finalizada correctamente.");
                    } else {
                        System.out.println("\nNo se pudo modificar el producto.");
                    }

                    pausar(sc);
                    break;

                case 5:
                    // Generación de reporte de costos
                    System.out.println("\n--- REPORTE DE COSTOS ---");
                    lista.imprimirReporteCostos();
                    pausar(sc);
                    break;

                case 6:
                    System.out.println("\n--- ELIMINAR PRODUCTO ---");
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreEliminar = sc.nextLine();

                    boolean eliminado = lista.eliminar(nombreEliminar);

                    if (eliminado) {
                        System.out.println("\nProducto eliminado correctamente.");
                    } else {
                        System.out.println("\nNo se pudo eliminar el producto.");
                    }

                    pausar(sc);
                    break;

                case 7:
                    System.out.println("\n--- AGREGAR IMAGEN A PRODUCTO ---");
                    System.out.print("Nombre del producto: ");
                    String nombreImagen = sc.nextLine();

                    System.out.print("Ruta de la imagen: ");
                    String ruta = sc.nextLine();

                    boolean imagenAgregada = lista.agregarImagenAProducto(nombreImagen, ruta);

                    if (imagenAgregada) {
                        System.out.println("\nImagen agregada correctamente.");
                    } else {
                        System.out.println("\nNo se pudo agregar la imagen.");
                    }

                    pausar(sc);
                    break;

                case 8:
                    System.out.println("\n--- LISTA COMPLETA DE PRODUCTOS ---");
                    lista.imprimirListaCompleta();
                    pausar(sc);
                    break;

                case 9:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpcion invalida.");
                    pausar(sc);
            }

        } while (opcion != 9);

        sc.close();
    }

    // Metodo auxiliar para pausar la consola hasta que el usuario presione Enter
    public static void pausar(Scanner sc) {
        System.out.println("\nPresione ENTER para continuar...");
        sc.nextLine();
    }
}