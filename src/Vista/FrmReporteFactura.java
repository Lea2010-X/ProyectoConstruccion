package Vista;

import Controlador.ControladorReportes;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloDetalleVenta;
import Util.GeneradorPDF;
import Util.Mensajes;
import Util.TemaModerno;
import com.itextpdf.text.DocumentException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class FrmReporteFactura extends JInternalFrame {

    private ControladorReportes controlador;
    private DefaultTableModel modeloTablaProductos;
    private ModeloReporteFactura reporteActual;

    private JTextField txtBuscar;
    private JButton btnBuscar, btnGenerarPDF;
    private JLabel lbMostrarFactura, lbMostrarFechaDeVenta;
    private JLabel lbMostrarNombres, lbMostrarApellidoPaterno, lbMostrarApellidoMaterno;
    private JLabel lbMostrarIVA, lbMostrarTotal;
    private JTable tbProductos;

    public FrmReporteFactura() {
        super("Buscar Comprobante", true, true, true, true);
        initUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        controlador = new ControladorReportes();
        modeloTablaProductos = (DefaultTableModel) tbProductos.getModel();
        aplicarTemaModerno();
    }

    private void aplicarTemaModerno() {
        TemaModerno.estilizarCampoTexto(txtBuscar);
        TemaModerno.estilizarBoton(btnBuscar, "primario");
        TemaModerno.estilizarBoton(btnGenerarPDF, "secundario");
        TemaModerno.estilizarTabla(tbProductos);
    }

    private void initUI() {
        setSize(900, 700);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout(0, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelSuperior = new JPanel(new BorderLayout(0, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Ingresar Número de Factura"));

        txtBuscar = campoTexto();
        txtBuscar.setPreferredSize(new Dimension(200, 30));
        btnBuscar = botonAccion("Buscar", new Color(52, 104, 180));

        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        btnBuscar.addActionListener(e -> btnBuscarAction());

        JPanel panelInfoFactura = new JPanel(new BorderLayout(0, 5));

        JPanel panelFacturaFecha = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JLabel lbFactura = new JLabel("Factura N°:");
        lbFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarFactura = new JLabel("...");
        lbMostrarFactura.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lbFechaDeVenta = new JLabel("Fecha de Venta:");
        lbFechaDeVenta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarFechaDeVenta = new JLabel("...");
        lbMostrarFechaDeVenta.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelFacturaFecha.add(lbFactura);
        panelFacturaFecha.add(lbMostrarFactura);
        panelFacturaFecha.add(Box.createHorizontalStrut(50));
        panelFacturaFecha.add(lbFechaDeVenta);
        panelFacturaFecha.add(lbMostrarFechaDeVenta);

        JPanel panelDatosCliente = new JPanel(new GridBagLayout());
        panelDatosCliente.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lbNombres = new JLabel("Nombres:");
        lbNombres.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarNombres = new JLabel("...");
        lbMostrarNombres.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lbApellidoPaterno = new JLabel("Ap. Paterno:");
        lbApellidoPaterno.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarApellidoPaterno = new JLabel("...");
        lbMostrarApellidoPaterno.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lbApellidoMaterno = new JLabel("Ap. Materno:");
        lbApellidoMaterno.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarApellidoMaterno = new JLabel("...");
        lbMostrarApellidoMaterno.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        gbc.gridx = 0; gbc.gridy = 0;
        panelDatosCliente.add(lbNombres, gbc);
        gbc.gridx = 1;
        panelDatosCliente.add(lbMostrarNombres, gbc);
        gbc.gridx = 2;
        panelDatosCliente.add(lbApellidoPaterno, gbc);
        gbc.gridx = 3;
        panelDatosCliente.add(lbMostrarApellidoPaterno, gbc);
        gbc.gridx = 4;
        panelDatosCliente.add(lbApellidoMaterno, gbc);
        gbc.gridx = 5;
        panelDatosCliente.add(lbMostrarApellidoMaterno, gbc);

        panelInfoFactura.add(panelFacturaFecha, BorderLayout.NORTH);
        panelInfoFactura.add(panelDatosCliente, BorderLayout.CENTER);

        panelSuperior.add(panelBusqueda, BorderLayout.NORTH);
        panelSuperior.add(panelInfoFactura, BorderLayout.CENTER);

        JPanel panelTabla = new JPanel(new BorderLayout());
        JLabel lbProductos = new JLabel("Productos");
        lbProductos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbProductos.setBorder(new EmptyBorder(5, 0, 5, 0));

        tbProductos = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Producto", "Cantidad", "Precio Venta", "Subtotal"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });

        JScrollPane scrollTabla = new JScrollPane(tbProductos);

        panelTabla.add(lbProductos, BorderLayout.NORTH);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JPanel panelTotales = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        JLabel lbIVA = new JLabel("IVA:");
        lbIVA.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarIVA = new JLabel("...");
        lbMostrarIVA.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lbTotal = new JLabel("Total:");
        lbTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMostrarTotal = new JLabel("...");
        lbMostrarTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelTotales.add(lbIVA);
        panelTotales.add(lbMostrarIVA);
        panelTotales.add(Box.createHorizontalStrut(30));
        panelTotales.add(lbTotal);
        panelTotales.add(lbMostrarTotal);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnGenerarPDF = botonAccion("Generar PDF", new Color(55, 140, 90));
        btnGenerarPDF.setEnabled(false);
        btnGenerarPDF.addActionListener(e -> btnGenerarPDFAction());
        panelBoton.add(btnGenerarPDF);

        panelInferior.add(panelTotales, BorderLayout.NORTH);
        panelInferior.add(panelBoton, BorderLayout.SOUTH);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

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

    private JButton botonAccion(String texto, Color c) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(c.darker()); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(c); }
        });

        return btn;
    }

    private void limpiarCampos() {
        lbMostrarFactura.setText("...");
        lbMostrarFechaDeVenta.setText("...");
        lbMostrarNombres.setText("...");
        lbMostrarApellidoPaterno.setText("...");
        lbMostrarApellidoMaterno.setText("...");
        lbMostrarIVA.setText("...");
        lbMostrarTotal.setText("...");
        modeloTablaProductos.setRowCount(0);
        reporteActual = null;
        btnGenerarPDF.setEnabled(false);
    }

    private void btnBuscarAction() {
        String numFacturaStr = txtBuscar.getText().trim();
        if (numFacturaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_INGRESE_NUM_FACTURA, Mensajes.TITULO_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
            txtBuscar.requestFocus();
            return;
        }

        try {
            int numFactura = Integer.parseInt(numFacturaStr);

            if (numFactura <= 0) {
                JOptionPane.showMessageDialog(this, Mensajes.MSG_NUMERO_POSITIVO, Mensajes.TITULO_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
                txtBuscar.requestFocus();
                return;
            }

            ModeloReporteFactura comprobante = controlador.buscarComprobante(numFactura);

            if (comprobante != null) {
                reporteActual = comprobante;

                lbMostrarFactura.setText(String.valueOf(comprobante.getIdFactura()));
                lbMostrarFechaDeVenta.setText(comprobante.getFechaFactura().toString());
                lbMostrarNombres.setText(comprobante.getCliente().getNombre());
                lbMostrarApellidoPaterno.setText(comprobante.getCliente().getApPaterno());
                lbMostrarApellidoMaterno.setText(comprobante.getCliente().getApMaterno());

                modeloTablaProductos.setRowCount(0);
                for (ModeloDetalleVenta item : comprobante.getItems()) {
                    modeloTablaProductos.addRow(new Object[]{
                            item.getNombreProducto(),
                            item.getCantidad(),
                            item.getPrecioVenta(),
                            item.getSubtotal()
                    });
                }

                lbMostrarIVA.setText(String.valueOf(comprobante.getIva()));
                lbMostrarTotal.setText(String.format("%.2f", comprobante.getTotal()));
                btnGenerarPDF.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(this, Mensajes.MSG_FACTURA_NO_ENCONTRADA + numFactura, Mensajes.TITULO_ADVERTENCIA, JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_NUMERO_INVALIDO, Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_BD + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }

    private void btnGenerarPDFAction() {
        if (reporteActual == null) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_REPORTE_REQUERIDO_PDF, Mensajes.TITULO_ADVERTENCIA, JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String rutaPDF = GeneradorPDF.generarPDFReporteFactura(reporteActual);

            int respuesta = JOptionPane.showConfirmDialog(this,
                    String.format(Mensajes.MSG_PDF_GENERADO_EXITO, rutaPDF),
                    Mensajes.TITULO_PDF_GENERADO,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                abrirArchivo(rutaPDF);
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_CREAR_PDF + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirArchivo(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            } else {
                JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_ABRIR_ARCHIVO + "\nUbicación: " + rutaArchivo, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}
