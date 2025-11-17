package DAO;

import Configuracion.CConexion;
import Configuracion.CConexionTest;
import Modelo.ModeloProducto;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Integración para ProductoDAO.
 * Se conecta a la base de datos de prueba dbpos_test.
 */
public class ProductoDAOTest {

    private ProductoDAO dao;
    private CConexion conectorDePrueba;

    /**
     * ANTES de CADA prueba, limpia la tabla 'producto'.
     */
    @BeforeEach
    public void setUp() throws Exception {
        conectorDePrueba = new CConexionTest();
        dao = new ProductoDAO(conectorDePrueba); // Inyecta la conexión de prueba

        // Limpia las tablas 'detalle' y 'producto' (en ese orden por las FK)
        try (Connection conn = conectorDePrueba.estableceConexion();
             Statement st = conn.createStatement()) {
            st.execute("SET FOREIGN_KEY_CHECKS=0");
            st.execute("TRUNCATE TABLE detalle"); // Limpia detalle primero
            st.execute("TRUNCATE TABLE producto"); // Luego producto
            st.execute("SET FOREIGN_KEY_CHECKS=1");
        }
    }

    /**
     * Prueba si podemos agregar un producto y luego obtenerlo.
     */
    @Test
    public void testAgregarYObtenerProducto() throws Exception {
        System.out.println("probando agregarProducto y obtenerProductos");

        // 1. Arrange
        ModeloProducto p = new ModeloProducto();
        p.setNombreProducto("Lápiz");
        p.setPrecioProducto(12.50);
        p.setStockProducto(100);

        // 2. Act
        dao.agregarProducto(p);
        List<ModeloProducto> productos = dao.obtenerProductos();

        // 3. Assert
        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals("Lápiz", productos.get(0).getNombreProducto());
        assertEquals(12.50, productos.get(0).getPrecioProducto());
    }

    /**
     * Prueba si podemos modificar un producto.
     */
    @Test
    public void testModificarProducto() throws Exception {
        System.out.println("probando modificarProducto");

        // 1. Arrange (Agregar un producto)
        ModeloProducto p = new ModeloProducto();
        p.setNombreProducto("Borrador");
        p.setPrecioProducto(5.0);
        p.setStockProducto(50);
        dao.agregarProducto(p);

        // Obtener el ID que se le asignó
        int idCreado = dao.obtenerProductos().get(0).getIdProducto();

        // 2. Act (Modificarlo)
        ModeloProducto pModificado = new ModeloProducto();
        pModificado.setIdProducto(idCreado);
        pModificado.setNombreProducto("Borrador de Goma");
        pModificado.setPrecioProducto(5.25);
        pModificado.setStockProducto(40);
        dao.modificarProducto(pModificado);

        // 3. Assert (Obtenerlo de nuevo y verificar)
        ModeloProducto productoReleido = dao.obtenerProductos().get(0);
        assertEquals("Borrador de Goma", productoReleido.getNombreProducto());
        assertEquals(5.25, productoReleido.getPrecioProducto());
        assertEquals(40, productoReleido.getStockProducto());
    }

    /**
     * Prueba la búsqueda de productos por nombre.
     */
    @Test
    public void testBuscarPorNombre() throws Exception {
        System.out.println("probando buscarPorNombre");

        // 1. Arrange (Agregar varios productos)
        ModeloProducto p1 = new ModeloProducto();
        p1.setNombreProducto("Refresco Coca-Cola");
        p1.setPrecioProducto(20.0);
        p1.setStockProducto(10);
        dao.agregarProducto(p1);

        ModeloProducto p2 = new ModeloProducto();
        p2.setNombreProducto("Refresco Pepsi");
        p2.setPrecioProducto(19.0);
        p2.setStockProducto(10);
        dao.agregarProducto(p2);

        // 2. Act
        List<ModeloProducto> resultados = dao.buscarPorNombre("Coca");

        // 3. Assert
        assertEquals(1, resultados.size());
        assertEquals("Refresco Coca-Cola", resultados.get(0).getNombreProducto());
    }

    /**
     * Prueba la actualización de stock.
     */
    @Test
    public void testActualizarStock() throws Exception {
        System.out.println("probando actualizarStock");

        // 1. Arrange
        ModeloProducto p = new ModeloProducto();
        p.setNombreProducto("Sabritas");
        p.setPrecioProducto(15.0);
        p.setStockProducto(100);
        dao.agregarProducto(p);

        int idCreado = dao.obtenerProductos().get(0).getIdProducto();

        // 2. Act (Vender 20)
        dao.actualizarStock(idCreado, 20);

        // 3. Assert
        ModeloProducto productoActualizado = dao.obtenerProductos().get(0);
        assertEquals(80, productoActualizado.getStockProducto());
    }
}