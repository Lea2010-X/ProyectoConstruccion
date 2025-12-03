package DAO;

import Modelo.ModeloProductoInventario;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Integración para ProductoDAO.
 * Se conecta a la base de datos de prueba dbpos_test.
 */
public class ProductoDAOTest extends BaseDAOTest {

    private ProductoDAO dao;
    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new ProductoDAO(conectorDePrueba);
        limpiarTablas("detalle", "producto");
    }

    @Test
    public void testAgregarYObtenerProducto() throws Exception {
        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("Lápiz");
        p.setPrecioProducto(12.50);
        p.setStockProducto(100);
        dao.agregarProducto(p);
        List<ModeloProductoInventario> productos = dao.obtenerProductos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Lápiz", productos.get(0).getNombreProducto());
        assertEquals(12.50, productos.get(0).getPrecioProducto());
    }

    @Test
    public void testModificarProducto() throws Exception {
        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("Borrador");
        p.setPrecioProducto(5.0);
        p.setStockProducto(50);
        dao.agregarProducto(p);
        int idCreado = dao.obtenerProductos().get(0).getIdProducto();
        ModeloProductoInventario pModificado = new ModeloProductoInventario();
        pModificado.setIdProducto(idCreado);
        pModificado.setNombreProducto("Borrador de Goma");
        pModificado.setPrecioProducto(5.25);
        pModificado.setStockProducto(40);
        dao.modificarProducto(pModificado);
        ModeloProductoInventario productoReleido = dao.obtenerProductos().get(0);
        assertEquals("Borrador de Goma", productoReleido.getNombreProducto());
        assertEquals(5.25, productoReleido.getPrecioProducto());
        assertEquals(40, productoReleido.getStockProducto());
    }
    @Test
    public void testBuscarPorNombre() throws Exception {
        ModeloProductoInventario p1 = new ModeloProductoInventario();
        p1.setNombreProducto("Refresco Coca-Cola");
        p1.setPrecioProducto(20.0);
        p1.setStockProducto(10);
        dao.agregarProducto(p1);
        
        ModeloProductoInventario p2 = new ModeloProductoInventario();
        p2.setNombreProducto("Refresco Pepsi");
        p2.setPrecioProducto(19.0);
        p2.setStockProducto(10);
        dao.agregarProducto(p2);
        List<ModeloProductoInventario> resultados = dao.buscarPorNombre("Coca");
        assertEquals(1, resultados.size());
        assertEquals("Refresco Coca-Cola", resultados.get(0).getNombreProducto());
    }

    @Test
    public void testActualizarStock() throws Exception {
        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("Sabritas");
        p.setPrecioProducto(15.0);
        p.setStockProducto(100);
        dao.agregarProducto(p);
        int idCreado = dao.obtenerProductos().get(0).getIdProducto();
        dao.actualizarStock(idCreado, 20);
        ModeloProductoInventario productoActualizado = dao.obtenerProductos().get(0);
        assertEquals(80, productoActualizado.getStockProducto());
    }
    
    @Test
    public void testEliminarProducto() throws Exception {
        ModeloProductoInventario p = new ModeloProductoInventario();
        p.setNombreProducto("Producto Borrar");
        p.setPrecioProducto(10.0);
        p.setStockProducto(10);
        dao.agregarProducto(p);
        
        int id = dao.obtenerProductos().get(0).getIdProducto();
        dao.eliminarProducto(id);
        
        List<ModeloProductoInventario> productos = dao.obtenerProductos();
        assertTrue(productos.isEmpty(), "La lista debe estar vacía tras eliminar");
    }
}