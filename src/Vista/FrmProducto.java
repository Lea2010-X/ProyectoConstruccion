package Vista;

import Controlador.ControladorProducto;
import Modelo.ModeloProductoInventario;
import Util.Mensajes;
import Util.TemaModerno;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class FrmProducto extends JInternalFrame {

    private ControladorProducto controlador;
    private DefaultTableModel tableModel;

    private JPanel cardDatos;
    private JTextField txtIdProducto, txtNombreProducto, txtPrecioProducto, txtStockProducto;
    private JButton btnLimpiarCampos, btnGuardar, btnModificar, btnEliminar;
    private JTable tbProductos;

    public FrmProducto() {
        super("Productos", true, true, true, true);
        initUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        controlador = new ControladorProducto();
        tableModel = (DefaultTableModel) tbProductos.getModel();
        txtIdProducto.setEnabled(false);
        actualizarTabla();
        aplicarTemaModerno();
    }
    
    /**
     * Aplica el tema moderno a todos los componentes de la interfaz.
     */
    private void aplicarTemaModerno() {
        TemaModerno.estilizarCampoTexto(txtIdProducto);
        TemaModerno.estilizarCampoTexto(txtNombreProducto);
        TemaModerno.estilizarCampoTexto(txtPrecioProducto);
        TemaModerno.estilizarCampoTexto(txtStockProducto);
        
        TemaModerno.estilizarBoton(btnGuardar, "primario");
        TemaModerno.estilizarBoton(btnModificar, "secundario");
        TemaModerno.estilizarBoton(btnEliminar, "peligro");
        TemaModerno.estilizarBoton(btnLimpiarCampos, "advertencia");
        
        TemaModerno.estilizarTabla(tbProductos);
        
        TemaModerno.estilizarPanel(cardDatos, true);
    }

    private void initUI() {
        setSize(900, 600);
        setLayout(new BorderLayout());
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        cardDatos = new JPanel();
        cardDatos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Datos de Producto");
        TemaModerno.estilizarEtiqueta(lblTitulo, "titulo");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        cardDatos.add(lblTitulo, gbc);

        txtIdProducto = campoTexto();
        txtNombreProducto = campoTexto();
        txtPrecioProducto = campoTexto();
        txtStockProducto = campoTexto();

        agregarCampo(cardDatos, gbc, "Nombre:", txtNombreProducto, 0, 1);
        agregarCampo(cardDatos, gbc, "Precio:", txtPrecioProducto, 2, 1);
        agregarCampo(cardDatos, gbc, "Stock:", txtStockProducto, 4, 1);

        btnLimpiarCampos = botonClaro("Limpiar Campos");
        btnLimpiarCampos.addActionListener(e -> limpiarCampos());

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        cardDatos.add(btnLimpiarCampos, gbc);

        tbProductos = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[]{"ID", "Nombre", "Precio", "Stock"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });

        JScrollPane scrollTabla = new JScrollPane(tbProductos);
        scrollTabla.setBorder(new EmptyBorder(10, 0, 10, 0));

        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRowClicked();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnGuardar = botonAccion("Guardar", new Color(55, 140, 90));
        btnModificar = botonAccion("Modificar", new Color(52, 104, 180));
        btnEliminar = botonAccion("Eliminar", new Color(160, 60, 60));

        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        btnGuardar.addActionListener(e -> btnGuardarAction());
        btnModificar.addActionListener(e -> btnModificarAction());
        btnEliminar.addActionListener(e -> btnEliminarAction());

        panelPrincipal.add(cardDatos, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JTextField campoTexto() {
        JTextField t = new JTextField();
        t.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        t.setBackground(new Color(58, 60, 68));
        t.setForeground(Color.WHITE);
        t.setCaretColor(Color.WHITE);
        t.setBorder(new EmptyBorder(6, 6, 6, 6));
        return t;
    }

    private JButton botonClaro(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setBackground(new Color(90, 90, 100));
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(8, 12, 8, 12));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(new Color(110, 110, 120)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(new Color(90, 90, 100)); }
        });

        return btn;
    }

    private JButton botonAccion(String texto, Color c) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(c.darker()); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(c); }
        });

        return btn;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String labelTexto, JComponent campo, int gridx, int gridy) {
        JLabel lbl = new JLabel(labelTexto);
        TemaModerno.estilizarEtiqueta(lbl, "normal");

        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lbl, gbc);

        gbc.gridx = gridx + 1;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, gbc);
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);

        try {
            List<ModeloProductoInventario> productos = controlador.obtenerProductos();

            for (ModeloProductoInventario producto : productos) {
                tableModel.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getPrecioProducto(),
                    producto.getStockProducto()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tableRowClicked() {
        int fila = tbProductos.getSelectedRow();
        if (fila == -1) return;

        txtIdProducto.setText(tableModel.getValueAt(fila, 0).toString());
        txtNombreProducto.setText(tableModel.getValueAt(fila, 1).toString());
        txtPrecioProducto.setText(tableModel.getValueAt(fila, 2).toString());
        txtStockProducto.setText(tableModel.getValueAt(fila, 3).toString());
    }

    private void limpiarCampos() {
        txtIdProducto.setText("");
        txtNombreProducto.setText("");
        txtPrecioProducto.setText("");
        txtStockProducto.setText("");
    }

    /**
     * Valida que los campos del formulario no estén vacíos.
     * @return true si todos los campos tienen valor, false en caso contrario
     */
    private boolean validarCamposNoVacios() {
        String nombre = txtNombreProducto.getText().trim();
        String precioStr = txtPrecioProducto.getText().trim();
        String stockStr = txtStockProducto.getText().trim();

        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_CAMPOS_OBLIGATORIOS,
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Valida que el precio sea un número positivo.
     * @param precio El precio a validar
     * @return true si el precio es válido, false en caso contrario
     */
    private boolean validarPrecio(double precio) {
        if (precio <= 0) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_PRECIO_POSITIVO,
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtPrecioProducto.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Valida que el stock no sea negativo.
     * @param stock El stock a validar
     * @return true si el stock es válido, false en caso contrario
     */
    private boolean validarStock(int stock) {
        if (stock < 0) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_STOCK_NO_NEGATIVO,
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtStockProducto.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Valida que se haya seleccionado un producto de la tabla.
     * @return true si hay un producto seleccionado, false en caso contrario
     */
    private boolean validarProductoSeleccionado() {
        if (txtIdProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_SELECCIONE_REGISTRO,
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Obtiene los datos del producto del formulario.
     * @return Array con [nombre, precio, stock] o null si hay error de formato
     */
    private Object[] obtenerDatosProducto() {
        try {
            String nombre = txtNombreProducto.getText().trim();
            double precio = Double.parseDouble(txtPrecioProducto.getText().trim());
            int stock = Integer.parseInt(txtStockProducto.getText().trim());
            return new Object[]{nombre, precio, stock};
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "El precio y el stock deben ser números válidos.",
                Mensajes.MSG_ERROR_FORMATO, JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void btnGuardarAction() {
        if (!validarCamposNoVacios()) return;

        Object[] datos = obtenerDatosProducto();
        if (datos == null) return;

        String nombre = (String) datos[0];
        double precio = (double) datos[1];
        int stock = (int) datos[2];

        if (!validarPrecio(precio)) return;
        if (!validarStock(stock)) return;

        try {
            controlador.agregarProducto(nombre, precio, stock);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, Mensajes.MSG_GUARDADO_EXITO, Mensajes.TITULO_EXITO, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_BD + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnModificarAction() {
        if (!validarProductoSeleccionado()) return;
        if (!validarCamposNoVacios()) return;

        Object[] datos = obtenerDatosProducto();
        if (datos == null) return;

        String nombre = (String) datos[0];
        double precio = (double) datos[1];
        int stock = (int) datos[2];

        if (!validarPrecio(precio)) return;
        if (!validarStock(stock)) return;

        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            controlador.modificarProducto(id, nombre, precio, stock);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, Mensajes.MSG_MODIFICADO_EXITO, Mensajes.TITULO_EXITO, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_BD + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarAction() {
        if (!validarProductoSeleccionado()) return;

        if (JOptionPane.showConfirmDialog(this,
                Mensajes.MSG_CONFIRMAR_ELIMINACION,
                Mensajes.TITULO_CONFIRMAR,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION)
            return;

        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            controlador.eliminarProducto(id);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ELIMINADO_EXITO, Mensajes.TITULO_EXITO, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_BD + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}