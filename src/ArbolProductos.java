public class ArbolProductos {

    // El arbol guarda los productos ordenados por nombre (alfabeticamente)
    // Asi es mas rapido buscar que tener una lista simple
    private NodoArbol raiz;

    public ArbolProductos() {
        raiz = null; // arbol vacio al empezar
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    // revisa si no hay nada en el arbol
    private boolean estaVacio() {
        return raiz == null;
    }

    public boolean inventarioVacio() {
        return estaVacio();
    }

    // busca un producto por su nombre (sin importar mayusculas/minusculas)
    public NodoArbol buscar(String llave) {
        if (estaVacio()) return null;

        NodoArbol temp = raiz;

        // voy bajando por el arbol comparando nombres
        // si el nombre es menor voy a la izquierda, si es mayor a la derecha
        while (temp != null && !temp.getLlave().equalsIgnoreCase(llave)) {
            if (temp.getLlave().compareToIgnoreCase(llave) > 0) {
                temp = temp.getIzquierdo(); // el que busco es menor, voy izquierda
            } else {
                temp = temp.getDerecho();   // el que busco es mayor, voy derecha
            }
        }

        return temp; // si es null significa que no lo encontre
    }

    // agrega un producto nuevo al arbol
    // devuelve false si ya existe un producto con ese nombre
    public boolean insertar(Producto producto) {
        NodoArbol nodo = new NodoArbol(producto);

        if (estaVacio()) {
            raiz = nodo;
            return true;
        }

        NodoArbol temp = raiz;
        NodoArbol padreTemp;

        while (true) {
            padreTemp = temp;

            // comparo para saber si va a la izquierda o derecha
            if (temp.getLlave().compareToIgnoreCase(producto.getNombre()) > 0) {
                temp = temp.getIzquierdo();
                if (temp == null) {
                    padreTemp.setIzquierdo(nodo);
                    return true;
                }
            } else if (temp.getLlave().compareToIgnoreCase(producto.getNombre()) < 0) {
                temp = temp.getDerecho();
                if (temp == null) {
                    padreTemp.setDerecho(nodo);
                    return true;
                }
            } else {
                // ya hay un producto con ese nombre
                System.out.println("Ya existe un producto con ese nombre en el inventario.\n");
                return false;
            }
        }
    }

    // Recorre el arbol en orden (izquierda - raiz - derecha)
    // Devuelve el inventario como texto, ordenado alfabeticamente
    public String obtenerInventarioTexto(NodoArbol raizTemp) {
        if (raizTemp == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(obtenerInventarioTexto(raizTemp.getIzquierdo()));
        sb.append(raizTemp.getProducto().toString()).append("\n---------------------------\n");
        sb.append(obtenerInventarioTexto(raizTemp.getDerecho()));
        return sb.toString();
    }

    // Los tres recorridos del arbol, utiles para mostrar en consola

    public void enOrden(NodoArbol raizTemp) {
        if (raizTemp != null) {
            enOrden(raizTemp.getIzquierdo());
            System.out.println(raizTemp.getProducto());
            System.out.println("---------------------------");
            enOrden(raizTemp.getDerecho());
        }
    }

    public void preOrden(NodoArbol raizTemp) {
        if (raizTemp != null) {
            System.out.println(raizTemp.getProducto());
            System.out.println("---------------------------");
            preOrden(raizTemp.getIzquierdo());
            preOrden(raizTemp.getDerecho());
        }
    }

    public void postOrden(NodoArbol raizTemp) {
        if (raizTemp != null) {
            postOrden(raizTemp.getIzquierdo());
            postOrden(raizTemp.getDerecho());
            System.out.println(raizTemp.getProducto());
            System.out.println("---------------------------");
        }
    }
}