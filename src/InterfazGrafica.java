import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazGrafica extends JFrame {

    // colores usados en la interfaz
    private static final Color COLOR_FONDO       = new Color(248, 250, 252);
    private static final Color COLOR_PANEL       = Color.WHITE;
    private static final Color COLOR_ACENTO      = new Color(37, 99, 235);
    private static final Color COLOR_ACENTO_HOVER = new Color(29, 78, 216);
    private static final Color COLOR_EXITO       = new Color(22, 163, 74);
    private static final Color COLOR_ADVERTENCIA = new Color(220, 38, 38);
    private static final Color COLOR_TEXTO       = new Color(30, 41, 59);
    private static final Color COLOR_TEXTO_TENUE = new Color(100, 116, 139);
    private static final Color COLOR_BORDE       = new Color(203, 213, 225);
    private static final Color COLOR_BOTON_COLA  = new Color(59, 130, 246);

    // fuentes
    private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font FUENTE_SUBTIT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FUENTE_MONO   = new Font("Consolas", Font.PLAIN, 12);

    // componentes principales
    private Tienda tienda;
    private JTextArea areaResultados;
    private JTabbedPane pestanas;

    // campos de productos
    private JTextField campoNombreProducto, campoPrecio, campoCategoria, campoFecha, campoCantidad;

    // campos de clientes
    private JTextField campoNombreCliente, campoCorreo, campoUbicacionCliente;
    private JComboBox<String> comboPrioridad;

    // campos del carrito
    private JTextField campoProductoCarrito, campoCantidadCarrito;

    // campos del grafo
    private JTextField campoVertice, campoOrigenArista, campoDestinoArista, campoPesoArista;

    // cliente que se esta armando antes de encolarlo
    private Cliente clienteEnConstruccion;

    public InterfazGrafica() {
        tienda = new Tienda("TicoMarket", "Tienda_Central");
        cargarDatosEjemplo();
        configurarVentana();
        construirInterfaz();
    }

    // se cargan algunos datos de ejemplo al iniciar
    private void cargarDatosEjemplo() {
        tienda.agregarVertice("Barrio_Amon");
        tienda.agregarVertice("Barrio_Otoya");
        tienda.agregarVertice("Escazu");
        tienda.agregarVertice("Desamparados");

        tienda.agregarArista("Tienda_Central", "Barrio_Amon", 3);
        tienda.agregarArista("Barrio_Amon", "Barrio_Otoya", 2);
        tienda.agregarArista("Barrio_Otoya", "Escazu", 5);
        tienda.agregarArista("Tienda_Central", "Escazu", 12);
        tienda.agregarArista("Tienda_Central", "Desamparados", 8);
        tienda.agregarArista("Barrio_Amon", "Desamparados", 6);

        tienda.agregarProducto(new Producto("Arroz", 750.0, "Granos", "2025-12-31", 50));
        tienda.agregarProducto(new Producto("Frijoles", 900.0, "Granos", "2025-11-30", 30));
        tienda.agregarProducto(new Producto("Leche", 1200.0, "Lacteos", "2025-06-15", 20));
    }

    // configuracion basica de la ventana
    private void configurarVentana() {
        setTitle("TicoMarket - Sistema de Gestion");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(950, 650));
        getContentPane().setBackground(COLOR_FONDO);
    }

    // se arma toda la interfaz
    private void construirInterfaz() {
        setLayout(new BorderLayout(0, 0));
        add(construirEncabezado(), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                construirPanelIzquierdo(),
                construirPanelResultados());
        splitPane.setDividerLocation(480);
        splitPane.setDividerSize(6);
        splitPane.setBorder(null);
        splitPane.setBackground(COLOR_FONDO);
        add(splitPane, BorderLayout.CENTER);
    }

    // encabezado con el nombre y ubicacion de la tienda
    private JPanel construirEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, COLOR_BORDE),
                new EmptyBorder(15, 25, 15, 25)));

        JLabel lblTitulo = new JLabel("TicoMarket");
        lblTitulo.setFont(FUENTE_TITULO);
        lblTitulo.setForeground(COLOR_TEXTO);

        JLabel lblSub = new JLabel("Sistema de Gestion de Inventarios");
        lblSub.setFont(FUENTE_NORMAL);
        lblSub.setForeground(COLOR_TEXTO_TENUE);

        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
        panelTexto.setOpaque(false);
        panelTexto.add(lblTitulo);
        panelTexto.add(lblSub);

        JLabel lblUbicacion = new JLabel("Tienda: " + tienda.getUbicacion());
        lblUbicacion.setFont(FUENTE_NORMAL);
        lblUbicacion.setForeground(COLOR_TEXTO_TENUE);
        lblUbicacion.setIcon(UIManager.getIcon("FileView.computerIcon"));

        panel.add(panelTexto, BorderLayout.WEST);
        panel.add(lblUbicacion, BorderLayout.EAST);
        return panel;
    }

    // panel izquierdo con las pestañas
    private JPanel construirPanelIzquierdo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 5));

        pestanas = new JTabbedPane();
        pestanas.setBackground(COLOR_PANEL);
        pestanas.setForeground(COLOR_TEXTO);
        pestanas.setFont(FUENTE_SUBTIT);
        pestanas.setBorder(new EmptyBorder(5, 5, 5, 5));

        pestanas.addTab("Productos", construirPestanaProductos());
        pestanas.addTab("Clientes", construirPestanaClientes());
        pestanas.addTab("Grafo", construirPestanaGrafo());

        panel.add(pestanas, BorderLayout.CENTER);
        return panel;
    }

    // formulario para agregar productos
    private JPanel construirPestanaProductos() {
        JPanel panel = crearPanelFormulario();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        JLabel lblTitulo = new JLabel("GESTION DE PRODUCTOS");
        lblTitulo.setFont(FUENTE_SUBTIT);
        lblTitulo.setForeground(COLOR_ACENTO);
        panel.add(lblTitulo, gbc);
        gbc.gridwidth = 1;

        campoNombreProducto = crearCampo();
        campoPrecio = crearCampo();
        campoCategoria = crearCampo();
        campoFecha = crearCampo();
        campoCantidad = crearCampo();

        agregarPar(panel, gbc, row++, "Nombre:", campoNombreProducto);
        agregarPar(panel, gbc, row++, "Precio (CRC):", campoPrecio);
        agregarPar(panel, gbc, row++, "Categoria:", campoCategoria);
        agregarPar(panel, gbc, row++, "Fecha vencimiento (AAAA-MM-DD):", campoFecha);
        agregarPar(panel, gbc, row++, "Cantidad:", campoCantidad);

        JButton btnAgregar = crearBoton("Agregar Producto", COLOR_ACENTO);
        btnAgregar.addActionListener(e -> accionAgregarProducto());

        JButton btnMostrarInventario = crearBoton("Mostrar Inventario", COLOR_EXITO);
        btnMostrarInventario.addActionListener(e -> mostrarEnResultados(tienda.mostrarInventario()));

        gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(btnAgregar, gbc);
        gbc.gridy = row++;
        panel.add(btnMostrarInventario, gbc);

        return panel;
    }

    // formulario para clientes, carrito y atencion
    private JPanel construirPestanaClientes() {
        JPanel panel = crearPanelFormulario();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        JLabel lblTitulo = new JLabel("REGISTRO DE CLIENTES");
        lblTitulo.setFont(FUENTE_SUBTIT);
        lblTitulo.setForeground(COLOR_ACENTO);
        panel.add(lblTitulo, gbc);
        gbc.gridwidth = 1;

        campoNombreCliente = crearCampo();
        campoCorreo = crearCampo();
        campoUbicacionCliente = crearCampo();
        comboPrioridad = new JComboBox<>(new String[]{"1 - Basico", "2 - Afiliado", "3 - Premium"});
        estilizarCombo(comboPrioridad);

        agregarPar(panel, gbc, row++, "Nombre:", campoNombreCliente);
        agregarPar(panel, gbc, row++, "Correo:", campoCorreo);
        agregarPar(panel, gbc, row++, "Tipo cliente:", comboPrioridad);
        agregarPar(panel, gbc, row++, "Ubicacion (vertice):", campoUbicacionCliente);

        JButton btnCrearCliente = crearBoton("Crear Cliente y Agregar Productos", COLOR_ACENTO);
        btnCrearCliente.addActionListener(e -> accionCrearCliente());

        gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(btnCrearCliente, gbc);

        // separador para el carrito
        row++;
        JLabel lblSeparador = new JLabel("--- Carrito del cliente en construccion ---");
        lblSeparador.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSeparador.setForeground(COLOR_TEXTO_TENUE);
        gbc.gridy = row++;
        panel.add(lblSeparador, gbc);

        campoProductoCarrito = crearCampo();
        campoCantidadCarrito = crearCampo();
        agregarPar(panel, gbc, row++, "Producto a comprar:", campoProductoCarrito);
        agregarPar(panel, gbc, row++, "Cantidad:", campoCantidadCarrito);

        JPanel panelBotonesCarrito = new JPanel(new GridBagLayout());
        panelBotonesCarrito.setOpaque(false);
        GridBagConstraints gbcBotones = new GridBagConstraints();
        gbcBotones.insets = new Insets(5, 5, 5, 5);
        gbcBotones.fill = GridBagConstraints.HORIZONTAL;
        gbcBotones.weightx = 0.5;

        JButton btnAgregarCarrito = crearBoton("+ Al Carrito", COLOR_EXITO);
        btnAgregarCarrito.addActionListener(e -> accionAgregarAlCarrito());

        JButton btnFinalizarCliente = crearBoton("Finalizar y Encolar", COLOR_ADVERTENCIA);
        btnFinalizarCliente.setPreferredSize(new Dimension(180, 40));
        btnFinalizarCliente.addActionListener(e -> accionFinalizarCliente());

        gbcBotones.gridx = 0;
        panelBotonesCarrito.add(btnAgregarCarrito, gbcBotones);
        gbcBotones.gridx = 1;
        panelBotonesCarrito.add(btnFinalizarCliente, gbcBotones);

        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(panelBotonesCarrito, gbc);

        JButton btnAtender = crearBoton("Atender Siguiente Cliente", new Color(139, 92, 246));
        btnAtender.addActionListener(e -> accionAtenderCliente());

        JButton btnVerCola = crearBoton("Ver Cola de Clientes", COLOR_BOTON_COLA);
        btnVerCola.addActionListener(e -> mostrarEnResultados(tienda.mostrarColaClientes()));

        gbc.gridy = row++;
        panel.add(btnAtender, gbc);
        gbc.gridy = row++;
        panel.add(btnVerCola, gbc);

        return panel;
    }

    // formulario para el grafo (vertices y aristas)
    private JPanel construirPestanaGrafo() {
        JPanel panel = crearPanelFormulario();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        JLabel lblTitulo = new JLabel("GESTION DEL GRAFO DE UBICACIONES");
        lblTitulo.setFont(FUENTE_SUBTIT);
        lblTitulo.setForeground(COLOR_ACENTO);
        panel.add(lblTitulo, gbc);
        gbc.gridwidth = 1;

        campoVertice = crearCampo();
        agregarPar(panel, gbc, row++, "Nombre del vertice:", campoVertice);

        JButton btnAgregarVertice = crearBoton("Agregar Vertice", COLOR_ACENTO);
        btnAgregarVertice.addActionListener(e -> {
            String v = campoVertice.getText().trim();
            if (v.isEmpty()) { mostrarError("Ingrese el nombre del vertice."); return; }
            mostrarEnResultados(tienda.agregarVertice(v));
            campoVertice.setText("");
        });

        gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(btnAgregarVertice, gbc);

        row++;
        JLabel lblSeparador = new JLabel("--- Nueva arista ---");
        lblSeparador.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSeparador.setForeground(COLOR_TEXTO_TENUE);
        gbc.gridy = row++;
        panel.add(lblSeparador, gbc);
        gbc.gridwidth = 1;

        campoOrigenArista = crearCampo();
        campoDestinoArista = crearCampo();
        campoPesoArista = crearCampo();

        agregarPar(panel, gbc, row++, "Origen:", campoOrigenArista);
        agregarPar(panel, gbc, row++, "Destino:", campoDestinoArista);
        agregarPar(panel, gbc, row++, "Distancia (peso):", campoPesoArista);

        JButton btnAgregarArista = crearBoton("Agregar Arista", COLOR_ACENTO);
        btnAgregarArista.addActionListener(e -> accionAgregarArista());

        // Boton Visualizar Grafo con color visible (azul como boton de cola)
        JButton btnVerGrafo = crearBoton("Visualizar Grafo", COLOR_BOTON_COLA);
        btnVerGrafo.addActionListener(e -> mostrarEnResultados(tienda.mostrarGrafo()));

        gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(btnAgregarArista, gbc);
        gbc.gridy = row++;
        panel.add(btnVerGrafo, gbc);

        return panel;
    }

    // panel derecho donde se muestran los resultados
    private JPanel construirPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(10, 5, 10, 10));

        JLabel lblResultados = new JLabel("  Salida del sistema");
        lblResultados.setFont(FUENTE_SUBTIT);
        lblResultados.setForeground(COLOR_TEXTO_TENUE);
        lblResultados.setOpaque(true);
        lblResultados.setBackground(COLOR_PANEL);
        lblResultados.setBorder(new EmptyBorder(8, 12, 8, 12));

        areaResultados = new JTextArea();
        areaResultados.setFont(FUENTE_MONO);
        areaResultados.setForeground(COLOR_TEXTO);
        areaResultados.setBackground(Color.WHITE);
        areaResultados.setCaretColor(COLOR_ACENTO);
        areaResultados.setEditable(false);
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);
        areaResultados.setBorder(new EmptyBorder(10, 12, 10, 12));

        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(new LineBorder(COLOR_BORDE, 1));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setBackground(COLOR_PANEL);

        JButton btnLimpiar = crearBoton("Limpiar pantalla", COLOR_EXITO);
        btnLimpiar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnLimpiar.addActionListener(e -> areaResultados.setText(""));

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setOpaque(false);
        panelSur.add(btnLimpiar, BorderLayout.CENTER);

        panel.add(lblResultados, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelSur, BorderLayout.SOUTH);

        mostrarEnResultados("Sistema iniciado.\nTienda: " + tienda.getUbicacion() +
                "\n\nGrafo de ejemplo cargado:\n" + tienda.mostrarGrafo() +
                "\nInventario de ejemplo cargado:\n" + tienda.mostrarInventario());

        return panel;
    }

    // -------------------------------------------------------
    // acciones de los botones
    // -------------------------------------------------------

    private void accionAgregarProducto() {
        try {
            String nombre    = campoNombreProducto.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            String categoria = campoCategoria.getText().trim();
            String fecha     = campoFecha.getText().trim();
            String cantStr   = campoCantidad.getText().trim();

            if (nombre.isEmpty() || precioStr.isEmpty() || categoria.isEmpty() || fecha.isEmpty() || cantStr.isEmpty()) {
                mostrarError("Complete todos los campos del producto.");
                return;
            }

            double precio   = Double.parseDouble(precioStr);
            int    cantidad = Integer.parseInt(cantStr);

            if (precio <= 0 || cantidad < 0) {
                mostrarError("Precio y cantidad deben ser valores positivos.");
                return;
            }

            mostrarEnResultados(tienda.agregarProducto(new Producto(nombre, precio, categoria, fecha, cantidad)));

            campoNombreProducto.setText("");
            campoPrecio.setText("");
            campoCategoria.setText("");
            campoFecha.setText("");
            campoCantidad.setText("");

        } catch (NumberFormatException ex) {
            mostrarError("Precio y cantidad deben ser numeros validos.");
        }
    }

    private void accionCrearCliente() {
        String nombre    = campoNombreCliente.getText().trim();
        String correo    = campoCorreo.getText().trim();
        String ubicacion = campoUbicacionCliente.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty() || ubicacion.isEmpty()) {
            mostrarError("Complete todos los campos del cliente.");
            return;
        }

        int prioridad = comboPrioridad.getSelectedIndex() + 1;
        clienteEnConstruccion = new Cliente(nombre, correo, prioridad, ubicacion);

        mostrarEnResultados("Cliente en construccion: " + nombre +
                "\nAhora agregue productos al carrito y luego presione 'Finalizar y Encolar'.\n\n" +
                tienda.mostrarInventario());
    }

    private void accionAgregarAlCarrito() {
        if (clienteEnConstruccion == null) {
            mostrarError("Primero cree un cliente con el boton 'Crear Cliente y Agregar Productos'.");
            return;
        }

        String nombreProducto = campoProductoCarrito.getText().trim();
        String cantidadStr    = campoCantidadCarrito.getText().trim();

        if (nombreProducto.isEmpty() || cantidadStr.isEmpty()) {
            mostrarError("Ingrese nombre del producto y cantidad.");
            return;
        }

        try {
            int      cantidad = Integer.parseInt(cantidadStr);
            Producto prod     = tienda.buscarProducto(nombreProducto);

            if (prod == null) {
                mostrarError("Producto \"" + nombreProducto + "\" no encontrado en el inventario.");
                return;
            }
            if (prod.getCantidad() == 0) {
                mostrarError("El producto \"" + nombreProducto + "\" esta agotado.");
                return;
            }
            if (cantidad <= 0 || cantidad > prod.getCantidad()) {
                mostrarError("Cantidad invalida. Disponible: " + prod.getCantidad());
                return;
            }

            clienteEnConstruccion.agregarAlCarrito(prod.getNombre(), prod.getPrecio(),
                    prod.getCategoria(), prod.getFechaVencimiento(), cantidad);

            prod.setCantidad(prod.getCantidad() - cantidad);

            mostrarEnResultados("Producto \"" + prod.getNombre() + "\" agregado al carrito de " +
                    clienteEnConstruccion.getNombre() + " (x" + cantidad + ").");

            campoProductoCarrito.setText("");
            campoCantidadCarrito.setText("");

        } catch (NumberFormatException ex) {
            mostrarError("La cantidad debe ser un numero entero.");
        }
    }

    private void accionFinalizarCliente() {
        if (clienteEnConstruccion == null) {
            mostrarError("No hay ningun cliente en construccion.");
            return;
        }
        if (clienteEnConstruccion.getCarrito().carritoVacio()) {
            mostrarError("El carrito esta vacio. Agregue al menos un producto antes de encolar.");
            return;
        }

        mostrarEnResultados(tienda.agregarCliente(clienteEnConstruccion));

        clienteEnConstruccion = null;
        campoNombreCliente.setText("");
        campoCorreo.setText("");
        campoUbicacionCliente.setText("");
        campoProductoCarrito.setText("");
        campoCantidadCarrito.setText("");
    }

    private void accionAtenderCliente() {
        mostrarEnResultados(tienda.atenderSiguienteCliente());
    }

    private void accionAgregarArista() {
        String origen  = campoOrigenArista.getText().trim();
        String destino = campoDestinoArista.getText().trim();
        String pesoStr = campoPesoArista.getText().trim();

        if (origen.isEmpty() || destino.isEmpty() || pesoStr.isEmpty()) {
            mostrarError("Complete origen, destino y peso de la arista.");
            return;
        }

        try {
            int peso = Integer.parseInt(pesoStr);
            if (peso <= 0) { mostrarError("El peso debe ser un numero positivo."); return; }

            mostrarEnResultados(tienda.agregarArista(origen, destino, peso));

            campoOrigenArista.setText("");
            campoDestinoArista.setText("");
            campoPesoArista.setText("");

        } catch (NumberFormatException ex) {
            mostrarError("El peso debe ser un numero entero.");
        }
    }

    // -------------------------------------------------------
    // metodos auxiliares para la interfaz
    // -------------------------------------------------------

    private void mostrarEnResultados(String texto) {
        areaResultados.append("\n" + texto + "\n");
        areaResultados.setCaretPosition(areaResultados.getDocument().getLength());
    }

    private void mostrarError(String mensaje) {
        mostrarEnResultados("[ERROR] " + mensaje);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(15, 15, 15, 15)));
        return panel;
    }

    private JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_NORMAL);
        campo.setForeground(COLOR_TEXTO);
        campo.setBackground(Color.WHITE);
        campo.setCaretColor(COLOR_ACENTO);
        campo.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(6, 8, 6, 8)));
        return campo;
    }

    private void agregarPar(JPanel panel, GridBagConstraints gbc, int row, String etiqueta, JComponent componente) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.weightx = 0.0;
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(FUENTE_NORMAL);
        lbl.setForeground(COLOR_TEXTO);
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(componente, gbc);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_SUBTIT);
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorFondo);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 12, 8, 12));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        Color hover = colorFondo.darker();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(colorFondo); }
        });
        return btn;
    }

    private void estilizarCombo(JComboBox<String> combo) {
        combo.setFont(FUENTE_NORMAL);
        combo.setForeground(COLOR_TEXTO);
        combo.setBackground(Color.WHITE);
        combo.setBorder(new LineBorder(COLOR_BORDE, 1, true));
    }
}