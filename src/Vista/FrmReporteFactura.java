package Vista;

import Controlador.ControladorReportes;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloDetalleVenta;
import Util.GeneradorPDF;
import Util.Mensajes;
import Util.TemaModerno;
import com.itextpdf.text.DocumentException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        btnBuscar.addActionListener(e -> btnBuscarAction());

        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(btnBuscar);

        JPanel panelInfoFactura = crearPanelInfoFactura();

        panelSuperior.add(panelBusqueda, BorderLayout.NORTH);
        panelSuperior.add(panelInfoFactura, BorderLayout.CENTER);

        JPanel panelTabla = new JPanel(new BorderLayout());
        JLabel lbProductos = new JLabel("Productos");
        lbProductos.setFont(TemaModerno.FUENTE_SUBTITULO);
        lbProductos.setBorder(new EmptyBorder(5, 0, 5, 0));

        tbProductos = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Producto", "Cantidad", "Precio Venta", "Subtotal"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });

        panelTabla.add(lbProductos, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tbProductos), BorderLayout.CENTER);

        JPanel panelInferior = crearPanelInferior();

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JPanel crearPanelInfoFactura() {
        JPanel panelInfo = new JPanel(new BorderLayout(0, 5));

        JPanel panelFacturaFecha = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        lbMostrarFactura = crearLabelDato("Factura N°:", panelFacturaFecha);
        panelFacturaFecha.add(Box.createHorizontalStrut(50));
        lbMostrarFechaDeVenta = crearLabelDato("Fecha de Venta:", panelFacturaFecha);

        JPanel panelDatosCliente = new JPanel(new GridBagLayout());
        panelDatosCliente.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;

        lbMostrarNombres = crearLabelCliente("Nombres:", panelDatosCliente, gbc, 0);
        lbMostrarApellidoPaterno = crearLabelCliente("Ap. Paterno:", panelDatosCliente, gbc, 2);
        lbMostrarApellidoMaterno = crearLabelCliente("Ap. Materno:", panelDatosCliente, gbc, 4);

        panelInfo.add(panelFacturaFecha, BorderLayout.NORTH);
        panelInfo.add(panelDatosCliente, BorderLayout.CENTER);
        return panelInfo;
    }

    private JPanel crearPanelInferior() {
        JPanel panelInf = new JPanel(new BorderLayout());

        JPanel panelTotales = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        lbMostrarIVA = crearLabelDato("IVA:", panelTotales);
        panelTotales.add(Box.createHorizontalStrut(30));
        lbMostrarTotal = crearLabelDato("Total:", panelTotales);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnGenerarPDF = botonAccion("Generar PDF", new Color(55, 140, 90));
        btnGenerarPDF.setEnabled(false);
        btnGenerarPDF.addActionListener(e -> btnGenerarPDFAction());
        panelBoton.add(btnGenerarPDF);

        panelInf.add(panelTotales, BorderLayout.NORTH);
        panelInf.add(panelBoton, BorderLayout.SOUTH);
        return panelInf;
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

    private void agregarEfectoHover(JButton btn, Color normal, Color hover) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e) { btn.setBackground(normal); }
        });
    }

    private JButton botonAccion(String texto, Color c) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        agregarEfectoHover(btn, c, c.darker());
        return btn;
    }
    
    private JLabel crearLabelDato(String titulo, JPanel padre) {
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(TemaModerno.FUENTE_SUBTITULO);
        padre.add(lblTitulo);
        
        JLabel lblValor = new JLabel("...");
        lblValor.setFont(TemaModerno.FUENTE_SUBTITULO);
        padre.add(lblValor);
        return lblValor;
    }

    private JLabel crearLabelCliente(String titulo, JPanel padre, GridBagConstraints gbc, int x) {
        gbc.gridx = x; gbc.gridy = 0;
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(TemaModerno.FUENTE_SUBTITULO);
        padre.add(lbl, gbc);

        gbc.gridx = x + 1;
        JLabel lblValor = new JLabel("...");
        lblValor.setFont(TemaModerno.FUENTE_SUBTITULO);
        padre.add(lblValor, gbc);
        return lblValor;
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

    private void mostrarDetallesFactura(ModeloReporteFactura c) {
        lbMostrarFactura.setText(String.valueOf(c.getIdFactura()));
        lbMostrarFechaDeVenta.setText(c.getFechaFactura().toString());
        lbMostrarNombres.setText(c.getCliente().getNombre());
        lbMostrarApellidoPaterno.setText(c.getCliente().getApPaterno());
        lbMostrarApellidoMaterno.setText(c.getCliente().getApMaterno());

        modeloTablaProductos.setRowCount(0);
        for (ModeloDetalleVenta item : c.getItems()) {
            modeloTablaProductos.addRow(new Object[]{
                item.getNombreProducto(),
                item.getCantidad(),
                item.getPrecioVenta(),
                item.getSubtotal()
            });
        }

        lbMostrarIVA.setText(String.valueOf(c.getIva()));
        lbMostrarTotal.setText(String.format("%.2f", c.getTotal()));
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
                return;
            }

            ModeloReporteFactura comprobante = controlador.buscarComprobante(numFactura);

            if (comprobante != null) {
                reporteActual = comprobante;
                mostrarDetallesFactura(comprobante); 
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
                abrirPDF(rutaPDF);
            }

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_CREAR_PDF + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirPDF(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this, "El archivo no existe en la ruta: " + rutaArchivo, Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            } else {
                JOptionPane.showMessageDialog(this, Mensajes.MSG_ERROR_ABRIR_ARCHIVO + "\nNo soportado en este sistema.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage(), Mensajes.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}