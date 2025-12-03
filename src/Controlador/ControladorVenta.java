package Controlador;

import DAO.ClienteDAO;
import DAO.DetalleDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import Modelo.ModeloCliente;
import Modelo.ModeloItemCarrito;
import Modelo.ModeloProductoInventario;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para el proceso de Venta.
 * Coordina la Vista (FrmVentas) con múltiples DAOs (Cliente, Producto, Factura, Detalle)
 * para orquestar la lógica de negocio de una venta.
 */
public final class ControladorVenta extends ControladorBase {

    private final ClienteDAO clienteDAO;
    private final ProductoDAO productoDAO;
    private final FacturaDAO facturaDAO;
    private final DetalleDAO detalleDAO;

    /**
     * Constructor estándar para la aplicación.
     * Usa los DAOs reales.
     */
    public ControladorVenta() {
        this.clienteDAO = new ClienteDAO();
        this.productoDAO = new ProductoDAO();
        this.facturaDAO = new FacturaDAO();
        this.detalleDAO = new DetalleDAO();
        validarDependencias();
    }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param clienteDAO Un DAO de Cliente (real o mock).
     * @param productoDAO Un DAO de Producto (real o mock).
     * @param facturaDAO Un DAO de Factura (real o mock).
     * @param detalleDAO Un DAO de Detalle (real o mock).
     */
    public ControladorVenta(ClienteDAO clienteDAO, ProductoDAO productoDAO, FacturaDAO facturaDAO, DetalleDAO detalleDAO) {
        this.clienteDAO = clienteDAO;
        this.productoDAO = productoDAO;
        this.facturaDAO = facturaDAO;
        this.detalleDAO = detalleDAO;
        validarDependencias();
    }

    @Override
    protected void validarDependencias() {
        if (this.clienteDAO == null || this.productoDAO == null || 
            this.facturaDAO == null || this.detalleDAO == null) {
            throw new IllegalStateException("Todos los DAOs deben estar inicializados");
        }
    }

    /**
     * Busca clientes por nombre.
     * @param nombre Término de búsqueda.
     * @return Lista de ModeloCliente
     * @throws SQLException si el DAO falla.
     */
    public List<ModeloCliente> buscarClientesPorNombre(String nombre) throws SQLException {
        return clienteDAO.buscarPorNombre(nombre);
    }

    /**
     * Busca productos por nombre.
     * @param nombre Término de búsqueda.
     * @return Lista de ModeloProductoInventario
     * @throws SQLException si el DAO falla.
     */
    public List<ModeloProductoInventario> buscarProductosPorNombre(String nombre) throws SQLException {
        return productoDAO.buscarPorNombre(nombre);
    }

    /**
     * Obtiene el ID de la última factura registrada.
     * @return ID de la última factura.
     * @throws SQLException si el DAO falla.
     */
    public int obtenerUltimaFactura() throws SQLException {
        return facturaDAO.obtenerUltimaFacturaID();
    }

    /**
     * Procesa la lógica de negocio para registrar una venta completa.
     * @param idCliente El ID del cliente que compra.
     * @param itemsVenta La lista de productos en el carrito (DTOs).
     * @throws SQLException si algo falla durante la transacción.
     */
    public void procesarVenta(int idCliente, List<ModeloItemCarrito> itemsVenta) throws SQLException {
        
        // Lógica de negocio:
        // 1. Crear la Factura
        int idFacturaCreada = facturaDAO.crearFactura(idCliente);
        
        // 2. Guardar cada detalle y actualizar stock
        for (ModeloItemCarrito item : itemsVenta) {
            detalleDAO.agregarDetalle(
                    idFacturaCreada,
                    item.getIdProducto(),
                    item.getCantidad(),
                    item.getPrecioVenta()
            );
            productoDAO.actualizarStock(
                    item.getIdProducto(),
                    item.getCantidad()
            );
        }
    }
}