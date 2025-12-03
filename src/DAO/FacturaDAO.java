package DAO;


import Configuracion.CConexion;

import java.sql.*;

public class FacturaDAO extends BaseDAO {

	/**
     * Constructor estándar para la aplicación.
     * Usa la conexión de producción.
     */
    public FacturaDAO() {
    	super(); 
    	}
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param conector Un conector de base de datos (ej. uno de prueba).
     */
    public FacturaDAO(CConexion conector) { 
    	super(conector); 
    	}

    /**
     * Crea una nueva factura para un cliente y devuelve el ID de la factura creada.
     * @param idCliente El ID del cliente.
     * @return El ID (idfactura) de la factura recién creada.
     * @throws SQLException si ocurre un error de SQL.
     */
    public int crearFactura(int idCliente) throws SQLException {
        String consulta = "INSERT INTO factura (fechaFactura, fkcliente) VALUES (CURDATE(), ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, idCliente);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la factura creada.");
                }
            }
        }
    }

    /**
     * Obtiene el ID de la última factura registrada.
     * @return El ID de la última factura, o 0 si no hay facturas.
     * @throws SQLException si ocurre un error de SQL.
     */
    public int obtenerUltimaFacturaID() throws SQLException {
        String consulta = "SELECT MAX(idfactura) AS UltimaFactura FROM factura";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consulta);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("UltimaFactura");
            }
        }
        return 0;
    }
}