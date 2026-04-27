# TicoMarket

Proyecto final del curso de Estructuras de Datos. Es un sistema de gestión de inventarios para una tienda ficticia llamada TicoMarket, con interfaz gráfica hecha en Swing.

---

## ¿Qué hace el sistema?

Básicamente simula la operación de una tienda pequeña:

- Podés agregar productos al inventario
- Registrar clientes con su carrito de compras
- Los clientes se atienden por prioridad (Premium primero, luego Afiliado, luego Básico)
- Al atender un cliente se genera su factura y se calcula la ruta más corta desde la tienda hasta donde vive, usando Dijkstra

---

## Estructuras de datos usadas

Cada estructura la implementamos nosotros, sin librerías externas:

| Estructura | Dónde se usa |
|---|---|
| Lista enlazada | Carrito de cada cliente |
| Árbol BST | Inventario de productos (ordenado por nombre) |
| Cola de prioridad | Cola de clientes (ArrayList con búsqueda manual) |
| Grafo con lista de adyacencia | Mapa de ubicaciones |
| Algoritmo de Dijkstra | Ruta más corta tienda → cliente |

---

## Clases del proyecto

```
Producto.java         → datos de un producto
Nodo.java             → nodo de la lista enlazada
ListaProductos.java   → lista enlazada (carrito)
NodoArbol.java        → nodo del árbol BST
ArbolProductos.java   → árbol BST del inventario
Cliente.java          → datos del cliente + su carrito
ColaClientes.java     → cola de prioridad
Arista.java           → arista del grafo
Vertice.java          → vértice auxiliar para Dijkstra
Grafo.java            → grafo + algoritmo de Dijkstra
Tienda.java           → clase central, conecta todo
InterfazGrafica.java  → ventana Swing
Main.java             → punto de entrada
```

---

## Cómo ejecutarlo

Necesitás tener Java instalado (JDK 8 o superior).

```bash
# Compilar
javac *.java

# Ejecutar (abre la interfaz gráfica)
java Main
```

Si querés probarlo desde consola, en `Main.java` podés cambiar el `main` para que llame a `ejecutarConsola()` en lugar de abrir la ventana.

---

## Datos que vienen cargados por defecto

Al abrir la app ya hay un grafo y productos de ejemplo para no empezar desde cero:

**Productos:**
- Arroz — ¢750 — 50 unidades
- Frijoles — ¢900 — 30 unidades
- Leche — ¢1200 — 20 unidades

**Grafo de ubicaciones:**
```
Tienda_Central → Barrio_Amon (dist: 3)
Tienda_Central → Escazu (dist: 12)
Tienda_Central → Desamparados (dist: 8)
Barrio_Amon    → Barrio_Otoya (dist: 2)
Barrio_Amon    → Desamparados (dist: 6)
Barrio_Otoya   → Escazu (dist: 5)
```

La tienda siempre sale desde `Tienda_Central`.

---

## Flujo normal de uso

1. Abrís la app
2. En la pestaña **Grafo** agregás los barrios/zonas que necesitás (o usás los que vienen)
3. En **Productos** agregás lo que tiene la tienda
4. En **Clientes** creás un cliente, le ponés su ubicación (tiene que ser un vértice que ya exista en el grafo), agregás productos al carrito y lo encolás
5. Con el botón **Atender Siguiente Cliente** se genera la factura y aparece la ruta más corta calculada con Dijkstra

---

## Validaciones importantes

- No se puede encolar un cliente si su ubicación no existe en el grafo
- No se puede finalizar el carrito si está vacío
- No se puede agregar más cantidad de un producto de la que hay en inventario
- No se pueden agregar productos con nombre duplicado al inventario

---

## Notas

- El grafo es **no dirigido y ponderado**: si A conecta con B, B también conecta con A
- Dijkstra encuentra el camino más corto aunque no sea el más obvio (por ejemplo, a veces conviene pasar por un barrio intermedio aunque haya ruta directa)
- La interfaz gráfica y la lógica están completamente separadas: `Tienda.java` no sabe nada de Swing

---

*Proyecto desarrollado para el curso de Estructuras de Datos — 2025*  
*David Rivera Valladares y Jimena Montero Segura*
