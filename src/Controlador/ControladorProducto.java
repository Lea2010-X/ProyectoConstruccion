package Controlador;

import DAO.ProductoDAO;
import Modelo.ModeloProducto;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la entidad Producto.
 * Su función es coordinar la Vista (FrmProducto) con la capa de acceso a datos (ProductoDAO).
 */
public final class ControladorProducto {

    private ProductoDAO productoDAO;

    /**
     * Constructor estándar para la aplicación.
     * Usa el DAO real.
     */
    public ControladorProducto() {
        this.productoDAO = new ProductoDAO();
    }

    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     */
    public ControladorProducto(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO; // Usa el DAO "inyectado"
    }
    public List<ModeloProducto> obtenerProductos() throws SQLException {
        return this.productoDAO.obtenerProductos();
    }

    public void agregarProducto(String nombre, double precio, int stock) throws SQLException {
        ModeloProducto producto = new ModeloProducto();
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);

        this.productoDAO.agregarProducto(producto);
    }

    public void modificarProducto(int id, String nombre, double precio, int stock) throws SQLException {
        ModeloProducto producto = new ModeloProducto();
        producto.setIdProducto(id);
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);

        this.productoDAO.modificarProducto(producto);
    }

    public void eliminarProducto(int id) throws SQLException {
        this.productoDAO.eliminarProducto(id);
    }
}