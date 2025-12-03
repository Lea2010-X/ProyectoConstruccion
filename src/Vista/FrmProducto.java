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

public class FrmProducto extends javax.swing.JInternalFrame {

    private ControladorProducto controlador;
    private DefaultTableModel tableModel;

    public FrmProducto() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.controlador = new ControladorProducto();
        this.tableModel = (DefaultTableModel) tbProductos.getModel();
        
        txtIdProducto.setEnabled(false);
        txtIdProducto.setVisible(false);
        jLabel1.setVisible(false);
        aplicarTemaModerno();
        
        actualizarTabla();
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
        
        TemaModerno.estilizarPanel(jPanel1, true);
        jPanel1.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(70, 70, 70), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
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
            JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        btnLimpiarCampos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Producto");
        setPreferredSize(new java.awt.Dimension(900, 700));

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Datos de Producto");
        TemaModerno.estilizarEtiqueta(lblTitulo, "titulo");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        jPanel1.add(lblTitulo, gbc);

        jLabel1.setText("Id:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Stock:");

        btnLimpiarCampos.setText("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCamposActionPerformed(evt);
            }
        });

        txtNombreProducto = campoTexto();
        txtPrecioProducto = campoTexto();
        txtStockProducto = campoTexto();
        
        agregarCampo(jPanel1, gbc, "Nombre:", txtNombreProducto, 0, 1);
        agregarCampo(jPanel1, gbc, "Precio:", txtPrecioProducto, 2, 1);
        agregarCampo(jPanel1, gbc, "Stock:", txtStockProducto, 4, 1);
        
        btnLimpiarCampos = botonClaro("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCamposActionPerformed(evt);
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        jPanel1.add(btnLimpiarCampos, gbc);

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},new String [] {"ID", "Nombre", "Precio", "Stock"}) {
            boolean[] canEdit = new boolean [] {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        jLabel5.setText("Click para seleccionar");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
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
            JOptionPane.showMessageDialog(this, "Producto guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el producto: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        int filaSeleccionada = tbProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;
        }

        String id = tableModel.getValueAt(filaSeleccionada, 0).toString();
        String nombre = tableModel.getValueAt(filaSeleccionada, 1).toString();
        String precio = tableModel.getValueAt(filaSeleccionada, 2).toString();
        String stock = tableModel.getValueAt(filaSeleccionada, 3).toString();

        txtIdProducto.setText(id);
        txtNombreProducto.setText(nombre);
        txtPrecioProducto.setText(precio);
        txtStockProducto.setText(stock);
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtIdProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_SELECCIONE_REGISTRO, "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
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
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCamposActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarCamposActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtIdProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            controlador.eliminarProducto(id);

            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del producto no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiarCampos;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtStockProducto;
    // End of variables declaration//GEN-END:variables
}