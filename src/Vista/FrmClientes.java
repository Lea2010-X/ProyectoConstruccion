package Vista;

import Controlador.ControladorCliente;
import Modelo.ModeloCliente;
import Util.Mensajes;
import Util.TemaModerno;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FrmClientes extends JInternalFrame {

    private ControladorCliente controlador;
    private DefaultTableModel tableModel;

    private JPanel cardDatos;
    private JTextField txtIdCliente, txtNombres, txtApellidoPaterno, txtApellidoMaterno;
    private JButton btnLimpiarCampos, btnGuardarCliente, btnModificarCliente, btnEliminarCliente;
    private JTable tbClientes;

    public FrmClientes() {
        super("Clientes", true, true, true, true);
        initUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        controlador = new ControladorCliente();
        tableModel = (DefaultTableModel) tbClientes.getModel();
        txtIdCliente.setEnabled(false);
        actualizarTabla();
        aplicarTemaModerno();
    }
    
    private void aplicarTemaModerno() {
        TemaModerno.estilizarCampoTexto(txtIdCliente);
        TemaModerno.estilizarCampoTexto(txtNombres);
        TemaModerno.estilizarCampoTexto(txtApellidoPaterno);
        TemaModerno.estilizarCampoTexto(txtApellidoMaterno);
        TemaModerno.estilizarBoton(btnGuardarCliente, "primario");
        TemaModerno.estilizarBoton(btnModificarCliente, "secundario");
        TemaModerno.estilizarBoton(btnEliminarCliente, "peligro");
        TemaModerno.estilizarBoton(btnLimpiarCampos, "advertencia");
        TemaModerno.estilizarTabla(tbClientes);
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

        JLabel lblTitulo = new JLabel("Datos del Cliente");
        TemaModerno.estilizarEtiqueta(lblTitulo, "titulo");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6; 
        gbc.anchor = GridBagConstraints.CENTER;
        cardDatos.add(lblTitulo, gbc);

        txtIdCliente         = campoTexto();
        txtNombres           = campoTexto();
        txtApellidoPaterno   = campoTexto();
        txtApellidoMaterno   = campoTexto();

        agregarCampo(cardDatos, gbc, "Nombres:", txtNombres, 0, 1);
        agregarCampo(cardDatos, gbc, "Apellido Paterno:", txtApellidoPaterno, 2, 1);

        agregarCampo(cardDatos, gbc, "Apellido Materno:", txtApellidoMaterno, 4, 1);

        btnLimpiarCampos = botonClaro("Limpiar Campos");
        btnLimpiarCampos.addActionListener(e -> limpiarCampos());

        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        cardDatos.add(btnLimpiarCampos, gbc);

        tbClientes = new JTable(new DefaultTableModel(
                new Object[][] {},
                new String[]{"ID", "Nombre", "Ap. Paterno", "Ap. Materno"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });

        JScrollPane scrollTabla = new JScrollPane(tbClientes);
        scrollTabla.setBorder(new EmptyBorder(10, 0, 10, 0));

        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRowClicked();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnGuardarCliente   = botonAccion("Guardar",   new Color(55, 140, 90));
        btnModificarCliente = botonAccion("Modificar", new Color(52, 104, 180));
        btnEliminarCliente  = botonAccion("Eliminar",  new Color(160, 60, 60));

        panelBotones.add(btnGuardarCliente);
        panelBotones.add(btnModificarCliente);
        panelBotones.add(btnEliminarCliente);

        btnGuardarCliente.addActionListener(e -> btnGuardarClienteAction());
        btnModificarCliente.addActionListener(e -> btnModificarClienteAction());
        btnEliminarCliente.addActionListener(e -> btnEliminarClienteAction());

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
        // CORRECCIÓN CLAVE: Usar estilo del tema (texto oscuro) en lugar de Color.WHITE fijo
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
            List<ModeloCliente> clientes = controlador.obtenerClientes();
            for (ModeloCliente cliente : clientes) {
                tableModel.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getApPaterno(),
                    cliente.getApMaterno()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los clientes: " + e.getMessage());
        }
    }

    private void tableRowClicked() {
        int fila = tbClientes.getSelectedRow();
        if (fila == -1) return;

        txtIdCliente.setText(tableModel.getValueAt(fila, 0).toString());
        txtNombres.setText(tableModel.getValueAt(fila, 1).toString());
        txtApellidoPaterno.setText(tableModel.getValueAt(fila, 2).toString());
        txtApellidoMaterno.setText(tableModel.getValueAt(fila, 3).toString());
    }

    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtNombres.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
    }

    
    /**
     * Valida que todos los campos del cliente estén completos.
     * @param validarApellidos true para validar apellidos también (para guardar), 
     *                         false para validación parcial (para modificar)
     * @return true si la validación pasa, false en caso contrario
     */
    private boolean validarCamposCliente(boolean validarApellidos) {
        String nombre = txtNombres.getText().trim();
        String apPaterno = txtApellidoPaterno.getText().trim();
        String apMaterno = txtApellidoMaterno.getText().trim();
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es requerido.", 
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtNombres.requestFocus();
            return false;
        }
        
        if (validarApellidos) {
            if (apPaterno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El apellido paterno es requerido.", 
                    "Error de Validación", JOptionPane.WARNING_MESSAGE);
                txtApellidoPaterno.requestFocus();
                return false;
            }
            if (apMaterno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El apellido materno es requerido.", 
                    "Error de Validación", JOptionPane.WARNING_MESSAGE);
                txtApellidoMaterno.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Valida que se haya seleccionado un cliente de la tabla.
     * @return true si hay un cliente seleccionado, false en caso contrario
     */
    private boolean validarClienteSeleccionado() {
        if (txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_SELECCIONE_REGISTRO, 
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Obtiene los datos del cliente del formulario.
     * @return Array con [nombre, apPaterno, apMaterno]
     */
    private String[] obtenerDatosCliente() {
        return new String[]{
            txtNombres.getText().trim(),
            txtApellidoPaterno.getText().trim(),
            txtApellidoMaterno.getText().trim()
        };
    }

    private void btnGuardarClienteAction() {
        if (!validarCamposCliente(true)) return;
        
        String[] datos = obtenerDatosCliente();

        try {
            controlador.agregarCliente(datos[0], datos[1], datos[2]);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnModificarClienteAction() {
        if (!validarClienteSeleccionado()) return;
        if (!validarCamposCliente(false)) return;
        
        String[] datos = obtenerDatosCliente();

        try {
            int id = Integer.parseInt(txtIdCliente.getText());
            controlador.modificarCliente(id, datos[0], datos[1], datos[2]);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarClienteAction() {
        if (!validarClienteSeleccionado()) return;

        if (JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de eliminar este cliente?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION)
            return;

        try {
            int id = Integer.parseInt(txtIdCliente.getText());
            controlador.eliminarCliente(id);
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
