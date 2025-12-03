package Controlador;

import DAO.ProductoDAO;
import Modelo.ModeloProductoInventario;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la entidad Producto.
 * Su función es coordinar la Vista (FrmProducto) con la capa de acceso a datos (ProductoDAO).
 */
public final class ControladorProducto extends ControladorBase {

    private ProductoDAO productoDAO;

    /**
     * Constructor estándar para la aplicación.
     * Usa el DAO real.
     */
    public ControladorProducto() {
        this.productoDAO = new ProductoDAO();
        validarDependencias();
    }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param productoDAO Un DAO (puede ser uno real o un mock).
     */
    public ControladorProducto(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
        validarDependencias();
    }

    @Override
    protected void validarDependencias() {
        if (this.productoDAO == null) {
            throw new IllegalStateException("ProductoDAO no puede ser nulo");
        }
    }

    /**
     * Pide al DAO la lista de todos los productos.
     * @return Lista de ModeloProductoInventario
     * @throws SQLException si el DAO falla.
     */
    public List<ModeloProductoInventario> obtenerProductos() throws SQLException {
        return this.productoDAO.obtenerProductos();
    }

    /**
     * Procesa la solicitud de agregar un nuevo producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param stock Stock del producto.
     * @throws SQLException si el DAO falla.
     */
    public void agregarProducto(String nombre, double precio, int stock) throws SQLException {
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);

        this.productoDAO.agregarProducto(producto);
    }

    /**
     * Procesa la solicitud de modificar un producto.
     * @param id El ID del producto a modificar.
     * @param nombre El nuevo nombre.
     * @param precio El nuevo precio.
     * @param stock El nuevo stock.
     * @throws SQLException si el DAO falla.
     */
    public void modificarProducto(int id, String nombre, double precio, int stock) throws SQLException {
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setIdProducto(id);
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setStockProducto(stock);

        this.productoDAO.modificarProducto(producto);
    }

    /**
     * Procesa la solicitud de eliminar un producto.
     * @param id El ID del producto a eliminar.
     * @throws SQLException si el DAO falla.
     */
    public void eliminarProducto(int id) throws SQLException {
        this.productoDAO.eliminarProducto(id);
    }
}