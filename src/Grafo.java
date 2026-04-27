import java.util.*;

public class Grafo {

    // se guardan todos los lugares y las conexiones entre ellos
    // es un grafo no dirigido (se puede ir y venir) y ponderado (cada conexion tiene una distancia)
    // se usa un mapa donde cada lugar tiene una lista de a donde puede ir
    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    // se agrega un nuevo lugar al grafo
    public boolean agregarVertice(String nombreVertice) {
        if (listaAdyacencia.containsKey(nombreVertice)) {
            return false; // ya existe ese lugar
        }
        listaAdyacencia.put(nombreVertice, new ArrayList<>());
        return true;
    }

    // se conectan dos lugares con una distancia
    // como es no dirigido, se agrega en ambos sentidos
    public boolean agregarArista(String origen, String destino, int peso) {
        if (!listaAdyacencia.containsKey(origen) || !listaAdyacencia.containsKey(destino)) {
            return false; // alguno de los dos lugares no existe
        }

        // se verifica que no exista ya la misma conexion
        for (Arista a : listaAdyacencia.get(origen)) {
            if (a.getDestino().equalsIgnoreCase(destino)) {
                return false; // ya estan conectados
            }
        }

        listaAdyacencia.get(origen).add(new Arista(destino, peso));
        listaAdyacencia.get(destino).add(new Arista(origen, peso));
        return true;
    }

    // se verifica si un lugar existe en el grafo
    public boolean existeVertice(String nombre) {
        return listaAdyacencia.containsKey(nombre);
    }

    // se muestran todas las conexiones del grafo en formato texto
    public String mostrarGrafo() {
        if (listaAdyacencia.isEmpty()) {
            return "El grafo esta vacio.";
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, List<Arista>> entrada : listaAdyacencia.entrySet()) {
            sb.append(entrada.getKey()).append(" -> ");
            for (Arista arista : entrada.getValue()) {
                sb.append("(").append(arista.getDestino())
                        .append(", dist:").append(arista.getPeso()).append(") ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }



    // ALGORITMO DE DIJKSTRA (implementado manualmente)

    // recibe el punto de inicio y dos mapas donde se guardan los resultados
    // distancias: distancia mas corta desde inicio hasta cada lugar
    // predecesores: por donde se llega a cada lugar (para reconstruir la ruta luego)
    public void algoritmoDijkstra(String inicio,
                                  Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {

        // cola que siempre saca el lugar con menor distancia acumulada
        PriorityQueue<Vertice> cola = new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        // primero se inicializan todas las distancias como "infinito" (un numero grande)
        // y los predecesores como nulos
        for (String vertice : listaAdyacencia.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            predecesores.put(vertice, null);
        }

        // la distancia al punto de inicio es cero
        distancias.put(inicio, 0);
        cola.add(new Vertice(inicio, 0));

        // mientras haya lugares por revisar
        while (!cola.isEmpty()) {

            // se extrae el vertice con la distancia mas corta actual
            Vertice actual = cola.poll();
            String nombreActual = actual.getNombre();

            // se recorren todos los vecinos del vertice actual
            for (Arista arista : listaAdyacencia.get(nombreActual)) {
                String vecino = arista.getDestino();
                int nuevaDistancia = distancias.get(nombreActual) + arista.getPeso();

                // si se encuentra un camino mas corto hacia el vecino, se actualiza
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, nombreActual);
                    cola.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    // se reconstruye el camino desde inicio hasta destino usando el mapa de predecesores
    public List<String> reconstruirCamino(String inicio, String destino, Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();

        // se parte desde el destino y se retrocede por los predecesores
        for (String actual = destino; actual != null; actual = predecesores.get(actual)) {
            camino.add(actual);
        }

        // se invierte para que quede de inicio a destino
        Collections.reverse(camino);

        // si no comienza con el inicio, no hay conexion
        if (camino.isEmpty() || !camino.get(0).equals(inicio)) {
            return new ArrayList<>();
        }

        return camino;
    }

    // metodo que junta todo y devuelve la ruta mas corta como texto
    // es el que llama la tienda cuando atiende a un cliente
    public String calcularRutaMasCorta(String origen, String destino) {
        if (!existeVertice(origen)) {
            return "ERROR: El vertice de origen \"" + origen + "\" no existe en el grafo.";
        }
        if (!existeVertice(destino)) {
            return "ERROR: El vertice de destino \"" + destino + "\" no existe en el grafo.";
        }

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();

        algoritmoDijkstra(origen, distancias, predecesores);

        List<String> camino = reconstruirCamino(origen, destino, predecesores);

        if (camino.isEmpty()) {
            return "No existe una ruta conectada entre \"" + origen + "\" y \"" + destino + "\".";
        }

        int distanciaTotal = distancias.get(destino);

        StringBuilder resultado = new StringBuilder();
        resultado.append("Ruta: ").append(String.join(" -> ", camino)).append("\n");
        resultado.append("Distancia total: ").append(distanciaTotal).append(" unidades");
        return resultado.toString();
    }
}