public class NodoArbol {

    // se guarda un producto del inventario
    // el nombre del producto se usa como llave para ordenar en el arbol
    // si la llave es menor va a la izquierda, si es mayor a la derecha
    private String llave;       // nombre del producto
    private Producto producto;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Producto producto) {
        this.llave = producto.getNombre();
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }

    // getters
    public String getLlave() {
        return llave;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public Producto getProducto() {
        return producto;
    }

    // setters
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "Llave: " + this.llave + " - " + this.producto.toString();
    }
}