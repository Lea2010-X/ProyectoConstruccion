package DAO;

import Modelo.ModeloCliente;
import Modelo.ModeloProductoInventario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Integración para DetalleDAO.
 */
public class DetalleDAOTest extends BaseDAOTest {

    private DetalleDAO dao;
    
    private int idClientePrueba;
    private int idProductoPrueba;
    private int idFacturaPrueba;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new DetalleDAO(conectorDePrueba);
        ClienteDAO clienteDAO = new ClienteDAO(conectorDePrueba);
        ProductoDAO productoDAO = new ProductoDAO(conectorDePrueba);
        FacturaDAO facturaDAO = new FacturaDAO(conectorDePrueba);

        limpiarTablas("detalle", "factura", "cliente", "producto");
        
        ModeloCliente c = new ModeloCliente();
        c.setNombre("ClienteDetalle");
        c.setApPaterno("Test");
        c.setApMaterno("Detalle");
        clienteDAO.agregarCliente(c);
        idClientePrueba = clienteDAO.obtenerClientes().get(0).getIdCliente();

        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("ProductoDetalle");
        productoDAO.agregarProducto(p);
        idProductoPrueba = productoDAO.obtenerProductos().get(0).getIdProducto();

        idFacturaPrueba = facturaDAO.crearFactura(idClientePrueba);
    }

    @Test
    public void testAgregarDetalle() throws Exception {
        int cantidad = 5;
        double precioVenta = 20.0;
        assertDoesNotThrow(() -> {
            dao.agregarDetalle(idFacturaPrueba, idProductoPrueba, cantidad, precioVenta);
        }, "agregarDetalle no debería lanzar una excepción SQL.");
    }
}