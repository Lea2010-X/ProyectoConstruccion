package Modelo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloProducto (con JUnit 5).
 */
public class ModeloProductoTest {
    
    public ModeloProductoTest() {
    }

    /**
     * Prueba el método ObtenerMontoPago().
     * Verifica que el cálculo (precio * stock) sea correcto.
     */
    @Test // Anotación de JUnit 5
    public void testObtenerMontoPago() {
        System.out.println("probando ObtenerMontoPago");
        
        // 1. Arrange (Preparar)
        ModeloProducto producto = new ModeloProducto();
        producto.setPrecioProducto(10.0);
        producto.setStockProducto(5);
        
        // 2. Act (Actuar)
        double resultado = producto.ObtenerMontoPago();
        
        // 3. Assert (Verificar)
        assertEquals(50.0, resultado, "El cálculo del monto de pago debe ser precio * stock");
    }
}