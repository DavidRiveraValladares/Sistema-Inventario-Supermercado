public class NodoArbol {

    //Atributos
    private String llave;        // El nombre del producto sirve como llave
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    private Producto producto;

    //Constructor
    public NodoArbol(Producto producto) {
        this.llave = producto.getNombre();
        this.producto = producto;
        this.izquierdo = this.derecho = null;
    }

    //Getters
    public String getLlave() { return llave; }
    public NodoArbol getIzquierdo() { return izquierdo; }
    public NodoArbol getDerecho() { return derecho; }
    public Producto getProducto() { return producto; }

    //Setters
    public void setProducto(Producto producto) { this.producto = producto; }
    public void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }
    public void setDerecho(NodoArbol derecho) { this.derecho = derecho; }

    //toString()
    public String toString() {
        return "Llave: " + this.llave + " - " + this.producto.toString();
    }
}