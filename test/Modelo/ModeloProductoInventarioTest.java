package Modelo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloProductoInventario
 */
public class ModeloProductoInventarioTest {
    
    public ModeloProductoInventarioTest() {
    }

    @Test
    public void testObtenerMontoPago() {

        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setNombreProducto("Producto Test"); // Ahora requerido por validación
        producto.setPrecioProducto(10.0);
        producto.setStockProducto(5);
    
        double resultado = producto.ObtenerMontoPago();
        
        assertEquals(50.0, resultado, "El cálculo del monto de pago debe ser precio * stock");
    }
    
    @Test
    public void testNombreVacioLanzaExcepcion() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombreProducto("");
        }, "Debe lanzar excepción cuando el nombre está vacío");
    }
    
    @Test
    public void testNombreNullLanzaExcepcion() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombreProducto(null);
        }, "Debe lanzar excepción cuando el nombre es null");
    }
    
    @Test
    public void testPrecioNegativoLanzaExcepcion() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setNombreProducto("Producto Test");
        
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setPrecioProducto(-10.0);
        }, "Debe lanzar excepción cuando el precio es negativo");
    }
    
    @Test
    public void testStockNegativoLanzaExcepcion() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setNombreProducto("Producto Test");
        
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setStockProducto(-5);
        }, "Debe lanzar excepción cuando el stock es negativo");
    }
    
    @Test
    public void testPrecioCeroEsValido() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setNombreProducto("Producto Gratis");
        producto.setPrecioProducto(0.0);
        
        assertEquals(0.0, producto.getPrecioProducto());
    }
    
    @Test
    public void testToString() {
        
        ModeloProductoInventario producto = new ModeloProductoInventario();
        producto.setIdProducto(1);
        producto.setNombreProducto("Producto Test");
        producto.setPrecioProducto(10.0);
        producto.setStockProducto(5);
        
        String resultado = producto.toString();
        
        assertTrue(resultado.contains("Producto Test"), "toString debe contener el nombre");
        assertTrue(resultado.contains("10.0"), "toString debe contener el precio");
    }
    
    @Test
    public void testEquals() {
        
        ModeloProductoInventario p1 = new ModeloProductoInventario();
        p1.setIdProducto(1);
        p1.setNombreProducto("Producto A");
        
        ModeloProductoInventario p2 = new ModeloProductoInventario();
        p2.setIdProducto(1);
        p2.setNombreProducto("Producto B");
        
        assertEquals(p1, p2, "Productos con mismo ID deben ser iguales");
    }
}

