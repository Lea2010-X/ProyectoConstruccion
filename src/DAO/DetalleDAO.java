package DAO;


import Configuracion.CConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleDAO extends BaseDAO {
    
	/**
     * Constructor estándar para la aplicación.
     * Usa la conexión de producción.
     */
    public DetalleDAO() { 
    	super(); 
    	}
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param conector Un conector de base de datos (ej. uno de prueba).
     */
    public DetalleDAO(CConexion conector) {
    	super(conector); 
    	}

    /**
     * Agrega un item de detalle a una factura.
     * @param idFactura El ID de la factura a la que pertenece.
     * @param idProducto El ID del producto vendido.
     * @param cantidad La cantidad vendida.
     * @param precioVenta El precio al que se vendió.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void agregarDetalle(int idFactura, int idProducto, int cantidad, double precioVenta) throws SQLException {
        String consulta = "INSERT INTO detalle (fkfactura, fkproducto, cantidad, precioVenta) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioVenta);
            ps.executeUpdate();
        }
    }
}