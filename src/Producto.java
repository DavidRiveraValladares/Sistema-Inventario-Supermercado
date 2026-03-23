public class Producto {

    // Dato principal del sistema, se usa en el inventario y en el carrito de cada cliente

    //Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;

    //Constructor
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }

    //Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }

    //Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    //toString()
    public String toString() {
        return "\nNombre: " + nombre +
                "\nPrecio: $" + String.format("%.2f", precio) +
                "\nCategoria: " + categoria +
                "\nVencimiento: " + fechaVencimiento +
                "\nCantidad disponible: " + cantidad;
    }
}