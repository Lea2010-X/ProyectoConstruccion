package DAO;


import Configuracion.CConexion;
import Modelo.ModeloCliente;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloDetalleVenta;
import Util.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportesDAO extends BaseDAO {

    private final DecimalFormat formato = Constantes.FORMATEADOR_DECIMAL;
    
    /**
     * Constructor estándar para la aplicación.
     * Usa la conexión de producción.
     */
    public ReportesDAO() { 
    	super(); 
    	}
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param conector Un conector de base de datos (ej. uno de prueba).
     */
    public ReportesDAO(CConexion conector) {
    	super(conector); 
    	}
    
    /**
     * Busca toda la información de un comprobante (Factura, Cliente, Detalles).
     * @param numeroFactura El ID de la factura a buscar.
     * @return Un objeto ModeloReporteFactura lleno, o null si no se encuentra.
     * @throws SQLException si ocurre un error de SQL.
     */
    public ModeloReporteFactura buscarComprobante(int numeroFactura) throws SQLException {
        ModeloReporteFactura comprobante = null;
        String consultaCliente = "SELECT f.idfactura, f.fechaFactura, c.idcliente, c.nombre, c.appaterno, c.apmaterno " +
                                 "FROM factura f " +
                                 "INNER JOIN cliente c ON c.idcliente = f.fkcliente " +
                                 "WHERE f.idfactura = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consultaCliente)) {
            
            ps.setInt(1, numeroFactura);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si se encontró la factura, creamos el objeto
                    comprobante = new ModeloReporteFactura();
                    comprobante.setIdFactura(rs.getInt("idfactura"));
                    comprobante.setFechaFactura(rs.getDate("fechaFactura"));
                    
                    ModeloCliente cliente = new ModeloCliente();
                    cliente.setIdCliente(rs.getInt("idcliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApPaterno(rs.getString("appaterno"));
                    cliente.setApMaterno(rs.getString("apmaterno"));
                    comprobante.setCliente(cliente);
                    
                    // Ahora buscamos los detalles (productos) de esa factura
                    comprobante.setItems(buscarDetallesDeFactura(conn, numeroFactura));

                    // Calculamos totales
                    double totalFactura = 0;
                    for (ModeloDetalleVenta item : comprobante.getItems()) {
                        totalFactura += item.getSubtotal();
                    }
                    
                    double iva = Double.parseDouble(formato.format(totalFactura * Constantes.TASA_IVA));
                    
                    comprobante.setSubtotal(Double.parseDouble(formato.format(totalFactura)));
                    comprobante.setIva(iva);
                }
            }
        }
        return comprobante;
    }

    /**
     * Método auxiliar para obtener los productos de una factura.
     */
    private List<ModeloDetalleVenta> buscarDetallesDeFactura(Connection conn, int numeroFactura) throws SQLException {
        List<ModeloDetalleVenta> items = new ArrayList<>();
        String consultaDetalles = "SELECT p.nombre, d.cantidad, d.precioVenta " +
                                  "FROM detalle d " +
                                  "INNER JOIN producto p ON p.idproducto = d.fkproducto " +
                                  "WHERE d.fkfactura = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(consultaDetalles)) {
            ps.setInt(1, numeroFactura);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ModeloDetalleVenta item = new ModeloDetalleVenta();
                    item.setNombreProducto(rs.getString("nombre"));
                    item.setCantidad(rs.getInt("cantidad"));
                    item.setPrecioVenta(rs.getDouble("precioVenta"));
                    
                    double subtotal = item.getCantidad() * item.getPrecioVenta();
                    item.setSubtotal(Double.parseDouble(formato.format(subtotal)));
                    
                    items.add(item);
                }
            }
        }
        return items;
    }

    /**
     * Busca todos los items de detalle vendidos entre dos fechas.
     * @param desde Fecha de inicio.
     * @param hasta Fecha de fin.
     * @return Una lista de ModeloDetalleVenta.
     * @throws SQLException si ocurre un error de SQL.
     */
    public List<ModeloDetalleVenta> generarReportePorFecha(Date desde, Date hasta) throws SQLException {
        List<ModeloDetalleVenta> items = new ArrayList<>();
        String consulta = "SELECT f.idfactura, f.fechaFactura, p.nombre, d.cantidad, d.precioVenta " +
                          "FROM detalle d " +
                          "INNER JOIN factura f ON f.idfactura = d.fkfactura " +
                          "INNER JOIN producto p ON p.idproducto = d.fkproducto " +
                          "WHERE f.fechaFactura BETWEEN ? AND ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            
            // Convertimos java.util.Date a java.sql.Date
            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ModeloDetalleVenta item = new ModeloDetalleVenta();
                    item.setIdFactura(rs.getInt("idfactura"));
                    item.setFechaFactura(rs.getDate("fechaFactura"));
                    item.setNombreProducto(rs.getString("nombre"));
                    item.setCantidad(rs.getInt("cantidad"));
                    item.setPrecioVenta(rs.getDouble("precioVenta"));
                    
                    double subtotal = item.getCantidad() * item.getPrecioVenta();
                    item.setSubtotal(Double.parseDouble(formato.format(subtotal)));
                    
                    items.add(item);
                }
            }
        }
        return items;
    }
}