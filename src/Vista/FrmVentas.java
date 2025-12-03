package Vista;

import Controlador.ControladorVenta;
import Modelo.ModeloCliente;
import Modelo.ModeloItemCarrito;
import Modelo.ModeloProductoInventario;
import Util.Constantes;
import Util.TemaModerno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FrmVentas extends javax.swing.JInternalFrame {

    private final ControladorVenta controlador;
    private final DefaultTableModel modeloClientes;
    private final DefaultTableModel modeloProductos;
    private final DefaultTableModel modeloResumenVenta;

    public FrmVentas() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        this.controlador = new ControladorVenta();

        this.modeloClientes = (DefaultTableModel) tbClientes.getModel();
        this.modeloProductos = (DefaultTableModel) tbProductos.getModel();
        this.modeloResumenVenta = (DefaultTableModel) tbResumenVenta.getModel();

        cambiarEstadoCampos(false,
                txtSIDProducto, txtSNombreProducto, txtSPrecio, txtSStock, txtSPrecioVenta,
                txtSIDCliente, txtSNombresCliente, txtSApellidoP, txtSApellidoM);

        aplicarTemaModerno();
        actualizarUltimaFactura();
    }

    private void cambiarEstadoCampos(boolean estado, JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setEnabled(estado);
        }
    }

    private void limpiarCamposTexto(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private void estilizarListaCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            TemaModerno.estilizarCampoTexto(campo);
        }
    }

    private void aplicarTemaModerno() {
        estilizarListaCampos(
                txtBuscarCliente, txtBuscarProductos,
                txtSIDCliente, txtSNombresCliente, txtSApellidoP, txtSApellidoM,
                txtSIDProducto, txtSNombreProducto, txtSPrecio, txtSStock,
                txtSPrecioVenta, txtCantidadVenta
        );

        TemaModerno.estilizarBoton(btnHabilitar, "primario");
        TemaModerno.estilizarBoton(btnDeshabilitar, "advertencia");
        TemaModerno.estilizarBoton(btnAgregarProducto, "secundario");
        TemaModerno.estilizarBoton(btnEliminarResumenVenta, "peligro");
        TemaModerno.estilizarBoton(btnCobrar, "primario");

        TemaModerno.estilizarTabla(tbClientes);
        TemaModerno.estilizarTabla(tbProductos);
        TemaModerno.estilizarTabla(tbResumenVenta);
    }

    private void actualizarUltimaFactura() {
        try {
            int ultimoID = controlador.obtenerUltimaFactura();
            lblUltimaFactura.setText(String.valueOf(ultimoID));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la última factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            lblUltimaFactura.setText("Error");
        }
    }

    private void limpiarCamposLuegoVenta() {
        limpiarCamposTexto(
                txtBuscarCliente, txtBuscarProductos,
                txtSIDCliente, txtSNombresCliente, txtSApellidoP, txtSApellidoM,
                txtSIDProducto, txtSNombreProducto, txtSPrecio, txtSStock,
                txtSPrecioVenta, txtCantidadVenta
        );

        txtBuscarCliente.requestFocus();
        txtSPrecioVenta.setEnabled(false);

        modeloClientes.setRowCount(0);
        modeloProductos.setRowCount(0);
        modeloResumenVenta.setRowCount(0);

        lblIVA.setText("----");
        lblMostrarTotal.setText("----");
    }

    private void calcularTotalPagar() {
        double totalSubtotal = 0;
        for (int i = 0; i < modeloResumenVenta.getRowCount(); i++) {
            totalSubtotal += (double) modeloResumenVenta.getValueAt(i, 4);
        }
        double totalIva = (totalSubtotal * Constantes.TASA_IVA) ;
        System.out.println(totalIva);
        lblMostrarTotal.setText(String.valueOf(totalSubtotal + totalIva));
        lblIVA.setText(String.format("%.2f", totalIva));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscarProductos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSIDCliente = new javax.swing.JTextField();
        txtSNombresCliente = new javax.swing.JTextField();
        txtSApellidoP = new javax.swing.JTextField();
        txtSApellidoM = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSIDProducto = new javax.swing.JTextField();
        txtSNombreProducto = new javax.swing.JTextField();
        txtSPrecio = new javax.swing.JTextField();
        txtSStock = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSPrecioVenta = new javax.swing.JTextField();
        btnHabilitar = new javax.swing.JButton();
        btnDeshabilitar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblUltimaFactura = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnEliminarResumenVenta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbResumenVenta = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblMostrarTotal = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton3.setText("jButton3");

        setClosable(false);
        setIconifiable(false);
        setTitle("Ventas");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Buscador: ");

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        jLabel2.setText("Click para Seleccionar");

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},new String [] {"ID", "Nombre", "Ap. Paterno", "Ap. Materno"}) {
            boolean[] canEdit = new boolean [] {false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBuscarCliente))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Buscador: ");

        txtBuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductosKeyReleased(evt);
            }
        });

        jLabel4.setText("Click para Seleccionar");

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},new String [] {"ID", "Nombre", "Precio", "Stock"}) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbProductos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBuscarProductos))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtBuscarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de la Seleccion"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Cliente"));

        jLabel5.setText("ID del Cliente:");

        jLabel6.setText("Nombres:");

        jLabel7.setText("Apellido Paterno:");

        jLabel8.setText("Apellido Materno:");

        txtSIDCliente.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSNombresCliente.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSApellidoP.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSApellidoM.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSIDCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                        .addComponent(txtSNombresCliente)
                                        .addComponent(txtSApellidoP)
                                        .addComponent(txtSApellidoM))
                                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtSIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtSNombresCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(txtSApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(txtSApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Producto"));

        jLabel9.setText("ID del Producto:");

        jLabel10.setText("Nombre:");

        jLabel11.setText("Precio:");

        jLabel12.setText("Stock:");

        txtSIDProducto.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSNombreProducto.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSPrecio.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        txtSStock.setDisabledTextColor(new java.awt.Color(0, 153, 102));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio y Cantidad"));

        jLabel13.setText("Precio de Venta:");

        txtSPrecioVenta.setDisabledTextColor(new java.awt.Color(255, 51, 51));

        btnHabilitar.setText("Habilitar");
        btnHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarActionPerformed(evt);
            }
        });

        btnDeshabilitar.setText("Deshabilitar");
        btnDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshabilitarActionPerformed(evt);
            }
        });

        jLabel14.setText("Cantidad de Venta:");

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCantidadVenta))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtSPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDeshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(txtSPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHabilitar)
                                        .addComponent(btnDeshabilitar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSIDProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(txtSNombreProducto)
                                        .addComponent(txtSPrecio)
                                        .addComponent(txtSStock))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(txtSIDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(txtSNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(txtSPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(16, 16, 16)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12)
                                                        .addComponent(txtSStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 12, Short.MAX_VALUE))
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen de Venta"));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Última Factura Creada:");

        lblUltimaFactura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblUltimaFactura.setText("....");

        jLabel17.setText("Seleccione Para Eliminar");

        btnEliminarResumenVenta.setText("Eliminar");
        btnEliminarResumenVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarResumenVentaActionPerformed(evt);
            }
        });

        tbResumenVenta.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},new String [] {"ID Producto", "Nombre Producto", "Precio Producto", "Cantidad Producto", "SubTotal"}) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbResumenVenta);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane3))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblUltimaFactura)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEliminarResumenVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(lblUltimaFactura)
                                        .addComponent(jLabel17)
                                        .addComponent(btnEliminarResumenVenta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("IVA (18%):");

        lblIVA.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblIVA.setText("----");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setText("Total:");

        lblMostrarTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMostrarTotal.setText("----");

        btnCobrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jLabel18)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblIVA)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblTotal)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblMostrarTotal)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(lblIVA)
                                        .addComponent(lblTotal)
                                        .addComponent(lblMostrarTotal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductosKeyReleased
        String busqueda = txtBuscarProductos.getText();
        modeloProductos.setRowCount(0);

        try {
            List<ModeloProductoInventario> productos = controlador.buscarProductosPorNombre(busqueda);
            for (ModeloProductoInventario p : productos) {
                modeloProductos.addRow(new Object[]{
                        p.getIdProducto(),
                        p.getNombreProducto(),
                        p.getPrecioProducto(),
                        p.getStockProducto()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtBuscarProductosKeyReleased

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        int fila = tbProductos.getSelectedRow();
        if (fila < 0) return;

        txtSIDProducto.setText(modeloProductos.getValueAt(fila, 0).toString());
        txtSNombreProducto.setText(modeloProductos.getValueAt(fila, 1).toString());
        txtSPrecio.setText(modeloProductos.getValueAt(fila, 2).toString());
        txtSStock.setText(modeloProductos.getValueAt(fila, 3).toString());
        txtSPrecioVenta.setText(modeloProductos.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tbProductosMouseClicked

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        String busqueda = txtBuscarCliente.getText();
        modeloClientes.setRowCount(0);

        try {
            List<ModeloCliente> clientes = controlador.buscarClientesPorNombre(busqueda);
            for (ModeloCliente c : clientes) {
                modeloClientes.addRow(new Object[]{
                        c.getIdCliente(),
                        c.getNombre(),
                        c.getApPaterno(),
                        c.getApMaterno()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
        int fila = tbClientes.getSelectedRow();
        if (fila < 0) return;

        txtSIDCliente.setText(modeloClientes.getValueAt(fila, 0).toString());
        txtSNombresCliente.setText(modeloClientes.getValueAt(fila, 1).toString());
        txtSApellidoP.setText(modeloClientes.getValueAt(fila, 2).toString());
        txtSApellidoM.setText(modeloClientes.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_tbClientesMouseClicked

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed

        String idProductoStr = txtSIDProducto.getText();
        if (idProductoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cantidadStr = txtCantidadVenta.getText().trim();
        if (cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtCantidadVenta.requestFocus();
            return;
        }

        String precioVentaStr = txtSPrecioVenta.getText().trim();
        if (precioVentaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El precio de venta no puede estar vacío.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idProducto = Integer.parseInt(idProductoStr);
            int cantidad = Integer.parseInt(cantidadStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            int stockDisponible = Integer.parseInt(txtSStock.getText());
            String nombreProducto = txtSNombreProducto.getText();

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error de Lógica", JOptionPane.WARNING_MESSAGE);
                txtCantidadVenta.requestFocus();
                return;
            }

            if (precioVenta <= 0) {
                JOptionPane.showMessageDialog(this, "El precio de venta debe ser mayor a cero.", "Error de Lógica", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cantidad > stockDisponible) {
                JOptionPane.showMessageDialog(this, "La cantidad de venta no puede ser mayor al stock disponible (" + stockDisponible + ").", "Stock Insuficiente", JOptionPane.WARNING_MESSAGE);
                txtCantidadVenta.requestFocus();
                return;
            }

            for (int i = 0; i < modeloResumenVenta.getRowCount(); i++) {
                if (((Integer)modeloResumenVenta.getValueAt(i, 0)) == idProducto) {
                    JOptionPane.showMessageDialog(this, "El producto '" + nombreProducto + "' ya está en el resumen.", "Producto Duplicado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            double subtotal = precioVenta * cantidad;
            modeloResumenVenta.addRow(new Object[]{
                    idProducto,
                    nombreProducto,
                    precioVenta,
                    cantidad,
                    subtotal
            });

            calcularTotalPagar();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad y el precio deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnEliminarResumenVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarResumenVentaActionPerformed
        int filaSeleccionada = tbResumenVenta.getSelectedRow();
        if (filaSeleccionada != -1) {
            modeloResumenVenta.removeRow(filaSeleccionada);
            calcularTotalPagar();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarResumenVentaActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed

        if (txtSIDCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Venta Fallida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (modeloResumenVenta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Debe agregar productos a la venta.", "Venta Fallida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idCliente = Integer.parseInt(txtSIDCliente.getText());

            List<ModeloItemCarrito> itemsVenta = new ArrayList<>();
            for (int i = 0; i < modeloResumenVenta.getRowCount(); i++) {
                int idProducto = (int) modeloResumenVenta.getValueAt(i, 0);
                String nombre = (String) modeloResumenVenta.getValueAt(i, 1);
                double precio = (double) modeloResumenVenta.getValueAt(i, 2);
                int cantidad = (int) modeloResumenVenta.getValueAt(i, 3);
                double subtotal = (double) modeloResumenVenta.getValueAt(i, 4);

                itemsVenta.add(new ModeloItemCarrito(idProducto, nombre, precio, cantidad, subtotal));
            }

            controlador.procesarVenta(idCliente, itemsVenta);

            JOptionPane.showMessageDialog(this, "Venta Realizada Correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposLuegoVenta();
            actualizarUltimaFactura();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en los datos del cliente. ID no válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la venta: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void btnHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarActionPerformed
        txtSPrecioVenta.setEnabled(true);
    }//GEN-LAST:event_btnHabilitarActionPerformed

    private void btnDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshabilitarActionPerformed
        txtSPrecioVenta.setEnabled(false);
    }//GEN-LAST:event_btnDeshabilitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnDeshabilitar;
    private javax.swing.JButton btnEliminarResumenVenta;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblMostrarTotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUltimaFactura;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbResumenVenta;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarProductos;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtSApellidoM;
    private javax.swing.JTextField txtSApellidoP;
    private javax.swing.JTextField txtSIDCliente;
    private javax.swing.JTextField txtSIDProducto;
    private javax.swing.JTextField txtSNombreProducto;
    private javax.swing.JTextField txtSNombresCliente;
    private javax.swing.JTextField txtSPrecio;
    private javax.swing.JTextField txtSPrecioVenta;
    private javax.swing.JTextField txtSStock;
    // End of variables declaration//GEN-END:variables
}