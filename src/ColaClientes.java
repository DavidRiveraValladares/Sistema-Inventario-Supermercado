import java.util.ArrayList;

public class ColaClientes {

    // Cola de prioridad de clientes.
    // Se atiende primero al de mayor prioridad (3 > 2 > 1).
    // Si hay empate, gana el que llego primero.

    //Atributos
    private ArrayList<Cliente> colaClientes;

    //Constructor
    public ColaClientes() {
        colaClientes = new ArrayList<>();
    }

    //Operaciones
    private boolean estaVacia() {
        return colaClientes.isEmpty();
    }

    public boolean colaVacia() { return estaVacia(); }

    public void insertar(Cliente cliente) {
        colaClientes.add(cliente);
        System.out.println("Cliente \"" + cliente.getNombre() + "\" agregado a la cola.");
    }

    // Busca al de mayor prioridad y lo remueve; en empate gana el de menor indice
    public Cliente remover() {
        if (estaVacia()) {
            System.out.println("Lo sentimos, la cola de clientes esta vacia.");
            return null;
        }

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.remove(indiceMayorPrioridad);
    }

    public Cliente verSiguiente() {
        if (estaVacia()) {
            System.out.println("Lo sentimos, la cola de clientes esta vacia.");
            return null;
        }

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.get(indiceMayorPrioridad);
    }

    public void imprimirCola() {
        if (estaVacia()) {
            System.out.println("La cola de clientes esta vacia.");
            return;
        }

        System.out.println("Clientes en espera:");
        for (int i = 0; i < colaClientes.size(); i++) {
            Cliente c = colaClientes.get(i);
            System.out.println("  " + (i + 1) + ". " + c.getNombre() +
                    " [" + c.describePrioridad() + " - prioridad " + c.getPrioridad() + "]");
        }
    }
}