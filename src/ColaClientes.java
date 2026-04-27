import java.util.ArrayList;

public class ColaClientes {

    // aca guardamos todos los clientes que estan esperando
    // no es una cola normal porque se atiende por prioridad, no por llegada
    // los premium (3) pasan primero, luego afiliados (2) y al final basicos (1)
    // si dos tienen la misma prioridad, se respeta el orden de llegada
    private ArrayList<Cliente> colaClientes;

    public ColaClientes() {
        colaClientes = new ArrayList<>();
    }

    private boolean estaVacia() {
        return colaClientes.isEmpty();
    }

    public boolean colaVacia() {
        return estaVacia();
    }

    // agrega un cliente al final (no importa la prioridad aca)
    public void insertar(Cliente cliente) {
        colaClientes.add(cliente);
    }

    // saca y devuelve al cliente con mayor prioridad
    // si hay empate, se queda con el que lleva mas tiempo esperando
    public Cliente remover() {
        if (estaVacia()) return null;

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.remove(indiceMayorPrioridad);
    }

    // mira quien es el siguiente en ser atendido, pero no lo saca
    public Cliente verSiguiente() {
        if (estaVacia()) return null;

        int indiceMayorPrioridad = 0;

        for (int i = 1; i < colaClientes.size(); i++) {
            if (colaClientes.get(i).getPrioridad() > colaClientes.get(indiceMayorPrioridad).getPrioridad()) {
                indiceMayorPrioridad = i;
            }
        }

        return colaClientes.get(indiceMayorPrioridad);
    }

    // genera el texto con todos los clientes en espera (para mostrar)
    public String obtenerColaTexto() {
        if (estaVacia()) {
            return "La cola de clientes esta vacia.";
        }

        StringBuilder sb = new StringBuilder("Clientes en espera:\n");

        for (int i = 0; i < colaClientes.size(); i++) {
            Cliente c = colaClientes.get(i);
            sb.append("  ").append(i + 1).append(". ").append(c.getNombre())
                    .append(" [").append(c.describePrioridad())
                    .append(" - prioridad ").append(c.getPrioridad()).append("]")
                    .append(" | Ubicacion: ").append(c.getUbicacion())
                    .append("\n");
        }

        return sb.toString();
    }
}