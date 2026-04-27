public class Vertice {

    // se usa internamente en el algoritmo de Dijkstra
    // se guarda el nombre del lugar y la distancia acumulada para llegar a el
    // la cola de prioridad lo utiliza para decidir cual vertice explorar primero
    private final String nombre;
    private final int distancia;

    public Vertice(String nombre, int distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistancia() {
        return distancia;
    }
}