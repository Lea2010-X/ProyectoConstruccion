package DAO;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List; 
import Configuracion.CConexion;
import Modelo.*; 

/**
 * Maneja el acceso a datos para la entidad Producto.
 * Habla directamente con la BD.
 */
public class ProductoDAO extends BaseDAO {
	
	/**
     * Constructor estándar para la aplicación.
     * Usa la conexión de producción.
     */
    public ProductoDAO() { 
        super(); 
     }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param conector Un conector de base de datos (ej. uno de prueba).
     */
    public ProductoDAO(CConexion conector){ 
         super(conector); 
     }

    /**
     * Obtiene todos los productos de la base de datos.
     * @return una lista de objetos ModeloProductoInventario.
     * @throws SQLException si ocurre un error de SQL.
     */
    public List<ModeloProductoInventario> obtenerProductos() throws SQLException {
        List<ModeloProductoInventario> productos = new ArrayList<>();
        String sql = "SELECT idproducto, nombre, precioProducto, stock FROM producto";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ModeloProductoInventario producto = new ModeloProductoInventario();
                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombreProducto(rs.getString("nombre"));
                producto.setPrecioProducto(rs.getDouble("precioProducto"));
                producto.setStockProducto(rs.getInt("stock"));
                productos.add(producto);
            }
        }
        return productos;
    }

    /**
     * Agrega un nuevo producto a la base de datos.
     * @param producto El objeto ModeloProductoInventario con los datos a guardar.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void agregarProducto(ModeloProductoInventario producto) throws SQLException {
        String consulta = "INSERT INTO producto(nombre, precioProducto, stock) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setString(1, producto.getNombreProducto());
            cs.setDouble(2, producto.getPrecioProducto());
            cs.setInt(3, producto.getStockProducto());
            cs.execute();
        }
    }

    /**
     * Modifica un producto existente en la base de datos.
     * @param producto El objeto ModeloProductoInventario con los datos actualizados.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void modificarProducto(ModeloProductoInventario producto) throws SQLException {
        String consulta = "UPDATE producto SET nombre = ?, precioProducto = ?, stock = ? WHERE idproducto = ?";

        try (Connection conn = getConnection();
            CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setString(1, producto.getNombreProducto());
            cs.setDouble(2, producto.getPrecioProducto());
            cs.setInt(3, producto.getStockProducto());
            cs.setInt(4, producto.getIdProducto());
            cs.execute();
        }
    }

    /**
     * Elimina un producto de la base de datos usando su ID.
     * @param idProducto El ID del producto a eliminar.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void eliminarProducto(int idProducto) throws SQLException {
        String consulta = "DELETE FROM producto WHERE idproducto = ?";

        try (Connection conn = getConnection();
             CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setInt(1, idProducto);
            cs.execute();
        }
    }
    

    /**
     * Busca productos por nombre (coincidencia parcial).
     * @param nombre El término de búsqueda.
     * @return una lista de objetos ModeloProductoInventario.
     * @throws SQLException si ocurre un error de SQL.
     */
    public List<ModeloProductoInventario> buscarPorNombre(String nombre) throws SQLException {
        List<ModeloProductoInventario> productos = new ArrayList<>();
        String consulta = "SELECT idproducto, nombre, precioProducto, stock FROM producto WHERE nombre LIKE CONCAT('%', ?, '%')";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ModeloProductoInventario producto = new ModeloProductoInventario();
                    producto.setIdProducto(rs.getInt("idproducto"));
                    producto.setNombreProducto(rs.getString("nombre"));
                    producto.setPrecioProducto(rs.getDouble("precioProducto"));
                    producto.setStockProducto(rs.getInt("stock"));
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

    /**
     * Actualiza el stock de un producto (reduce la cantidad).
     * @param idProducto El ID del producto.
     * @param cantidadVendida La cantidad a restar del stock.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void actualizarStock(int idProducto, int cantidadVendida) throws SQLException {
        String consulta = "UPDATE producto SET stock = stock - ? WHERE idproducto = ?";
        
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(consulta)) {
            
            ps.setInt(1, cantidadVendida);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        }
    }
}