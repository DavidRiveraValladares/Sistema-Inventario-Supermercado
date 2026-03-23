public class ArbolProductos {

    //Atributos
    private NodoArbol raiz;

    //Constructor
    public ArbolProductos() { this.raiz = null; }

    //Getters
    public NodoArbol getRaiz() { return raiz; }

    //Setters
    public void setRaiz(NodoArbol raiz) { this.raiz = raiz; }

    //Operaciones
    private boolean estaVacio() { return raiz == null; }

    public boolean inventarioVacio() { return estaVacio(); }

    public NodoArbol buscar(String llave) {
        if (estaVacio()) {
            System.out.println("El arbol esta vacio.\n");
            return null;
        }
        NodoArbol temp = raiz;
        while (!temp.getLlave().equalsIgnoreCase(llave)) {
            if (temp.getLlave().compareToIgnoreCase(llave) > 0) temp = temp.getIzquierdo();
            else temp = temp.getDerecho();
            if (temp == null) {
                System.out.println("El producto no esta en el inventario.\n");
                return null;
            }
        }
        return temp;
    }

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
                System.out.println("Ya existe un producto con ese nombre en el inventario.\n");
                return false;
            }
        }
    }

    // Los recorridos reciben la raiz como parametro, igual que en clase
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