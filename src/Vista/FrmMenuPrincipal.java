package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FrmMenuPrincipal extends JFrame {

    private JPanel panelLateral;
    private JPanel panelContenido;
    private JLabel titulo;

    public FrmMenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLayout(new BorderLayout());

        panelContenido = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(53, 73, 94),
                    0, h, new Color(28, 40, 51)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
            }
        };
        panelContenido.setLayout(new BorderLayout());

        panelLateral = new JPanel();
        panelLateral.setPreferredSize(new Dimension(260, 700));
        panelLateral.setBackground(new Color(24, 26, 32));
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel lblLogo = new JLabel("Lumina POS", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
        lblLogo.setForeground(new Color(240, 240, 240));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setBorder(new EmptyBorder(10, 0, 20, 0));

        panelLateral.add(lblLogo);

        panelLateral.add(crearSeparador());

        JButton btnVender      = crearBotonMenu("Ventas", "/iconos/ventas.png");
        JButton btnClientes    = crearBotonMenu("Clientes", "/iconos/clientes.png");
        JButton btnProductos   = crearBotonMenu("Productos", "/iconos/productos.png");
        JButton btnReporte1    = crearBotonMenu("Buscar Comprobante", "/iconos/busqueda.png");
        JButton btnReporte2    = crearBotonMenu("Consulta por Fecha", "/iconos/calendario.png");
        JButton btnSalir       = crearBotonMenu("Salir", "/iconos/salir.png");

        panelLateral.add(btnVender);
        panelLateral.add(btnClientes);
        panelLateral.add(btnProductos);

        panelLateral.add(crearSeparador());

        panelLateral.add(btnReporte1);
        panelLateral.add(btnReporte2);

        panelLateral.add(Box.createVerticalGlue());
        panelLateral.add(crearSeparador());
        panelLateral.add(btnSalir);

        titulo = new JLabel("Bienvenido a Lumina POS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Light", Font.BOLD, 38));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(new EmptyBorder(20, 10, 20, 10));

        panelContenido.add(titulo, BorderLayout.CENTER);

        btnVender.addActionListener(e -> abrirFormulario(new FrmVentas()));
        btnClientes.addActionListener(e -> abrirFormulario(new FrmClientes()));
        btnProductos.addActionListener(e -> abrirFormulario(new FrmProducto()));
        btnReporte1.addActionListener(e -> abrirFormulario(new FrmReporteFactura()));
        btnReporte2.addActionListener(e -> abrirFormulario(new FrmReporteVentasPeriodo()));
        btnSalir.addActionListener(e -> System.exit(0));

        add(panelLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
    }

    private JPanel crearSeparador() {
        JPanel sep = new JPanel();
        sep.setMaximumSize(new Dimension(260, 1));
        sep.setBackground(new Color(60, 60, 70));
        sep.setAlignmentX(Component.CENTER_ALIGNMENT);
        sep.setPreferredSize(new Dimension(240, 1));
        return sep;
    }

    private JButton crearBotonMenu(String texto, String rutaIcono) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(260, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estilo base
        btn.setFocusPainted(false);
        btn.setBackground(new Color(32, 34, 43));
        btn.setForeground(new Color(220, 220, 220));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(rutaIcono));
            Image img = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Icono no encontrado: " + rutaIcono);
        }

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            Color base = new Color(32, 34, 43);
            Color hover = new Color(55, 60, 80);

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(base);
            }
        });

        return btn;
    }

    private void abrirFormulario(JInternalFrame form) {
        form.setVisible(true);
        form.setClosable(true);
        form.setIconifiable(true);
        form.setMaximizable(true);
        form.setSize(900, 600);
        form.setLocation(50, 50);

        panelContenido.removeAll();
        panelContenido.add(form, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}
