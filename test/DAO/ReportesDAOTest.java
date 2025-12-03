package DAO;
 
import Modelo.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Integración para ReportesDAO.
 * Esta es la prueba más completa, ya que requiere datos en las 4 tablas.
 */
public class ReportesDAOTest extends BaseDAOTest {
    
    private ReportesDAO dao;
    private int idClientePrueba;
    private int idProductoPrueba;
    private int idFacturaPrueba;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new ReportesDAO(conectorDePrueba);
        ClienteDAO clienteDAO = new ClienteDAO(conectorDePrueba);
        ProductoDAO productoDAO = new ProductoDAO(conectorDePrueba);
        FacturaDAO facturaDAO = new FacturaDAO(conectorDePrueba);
        DetalleDAO detalleDAO = new DetalleDAO(conectorDePrueba);
        limpiarTablas("detalle", "factura", "cliente", "producto");
        ModeloCliente c = new ModeloCliente();
        c.setNombre("ClienteReporte");
        c.setApPaterno("Paterno");
        c.setApMaterno("Materno");
        clienteDAO.agregarCliente(c);
        idClientePrueba = clienteDAO.obtenerClientes().get(0).getIdCliente();

        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("ProductoReporte");
        p.setPrecioProducto(10.0); // Precio base 10
        p.setStockProducto(100);
        productoDAO.agregarProducto(p);
        idProductoPrueba = productoDAO.obtenerProductos().get(0).getIdProducto();
        idFacturaPrueba = facturaDAO.crearFactura(idClientePrueba);
        detalleDAO.agregarDetalle(idFacturaPrueba, idProductoPrueba, 5, 12.0);
    }

    @Test
    public void testBuscarComprobante() throws Exception {
        ModeloReporteFactura comprobante = dao.buscarComprobante(idFacturaPrueba);
        assertNotNull(comprobante, "El comprobante no debe ser nulo.");
        assertEquals(idClientePrueba, comprobante.getCliente().getIdCliente());
        assertEquals("ClienteReporte", comprobante.getCliente().getNombre());
        assertEquals(1, comprobante.getItems().size(), "Debe haber 1 item en el detalle.");
        ModeloDetalleVenta item = comprobante.getItems().get(0);
        assertEquals("ProductoReporte", item.getNombreProducto());
        assertEquals(5, item.getCantidad());
        assertEquals(12.0, item.getPrecioVenta());
        assertEquals(60.0, item.getSubtotal()); 
        assertEquals(60.0, comprobante.getTotal());
        
    }

    @Test
    public void testGenerarReportePorFecha() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date fechaDesde = cal.getTime();   
        cal.add(Calendar.DAY_OF_YEAR, 2);
        Date fechaHasta = cal.getTime();
        List<ModeloDetalleVenta> items = dao.generarReportePorFecha(fechaDesde, fechaHasta);
        assertNotNull(items);
        assertEquals(1, items.size(), "Debe encontrar 1 item vendido hoy.");
        ModeloDetalleVenta item = items.get(0);
        assertEquals(idFacturaPrueba, item.getIdFactura());
        assertEquals("ProductoReporte", item.getNombreProducto());
        assertEquals(5, item.getCantidad());
        assertEquals(60.0, item.getSubtotal());
    }
}