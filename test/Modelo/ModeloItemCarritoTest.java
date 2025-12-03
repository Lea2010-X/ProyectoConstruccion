package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloItemCarrito.
 * Verifica la inmutabilidad y validaciones.
 */
public class ModeloItemCarritoTest {

    @Test
    public void testCrearItemValido() {
        ModeloItemCarrito item = new ModeloItemCarrito(1, "Producto Test", 10.0, 2);
        assertEquals(1, item.getIdProducto());
        assertEquals("Producto Test", item.getNombreProducto());
        assertEquals(10.0, item.getPrecioVenta());
        assertEquals(2, item.getCantidad());
        assertEquals(20.0, item.getSubtotal());
    }
    @Test
    public void testCantidadCeroLanzaExcepcion() {
        System.out.println("probando validación de cantidad cero");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ModeloItemCarrito(1, "Producto", 10.0, 0);
        }, "Debe lanzar excepción cuando la cantidad es cero");
    }
    @Test
    public void testCantidadNegativaLanzaExcepcion() {
        System.out.println("probando validación de cantidad negativa");
        
        assertThrows(IllegalArgumentException.class, () -> {
            new ModeloItemCarrito(1, "Producto", 10.0, -5);
        }, "Debe lanzar excepción cuando la cantidad es negativa");
    }
    @Test
    public void testInmutabilidad() {
        System.out.println("probando inmutabilidad");
        ModeloItemCarrito item = new ModeloItemCarrito(1, "Producto", 10.0, 2);
        assertEquals(1, item.getIdProducto());
        assertEquals("Producto", item.getNombreProducto());
    }
    
    @Test
    public void testToString() {
        ModeloItemCarrito item = new ModeloItemCarrito(1, "Producto Test", 10.0, 2);
        String resultado = item.toString();
        assertTrue(resultado.contains("Producto Test"), "toString debe contener el nombre");
        assertTrue(resultado.contains("10.0"), "toString debe contener el precio");
        assertTrue(resultado.contains("2"), "toString debe contener la cantidad");
    }
    @Test
    public void testEquals() {
        ModeloItemCarrito item1 = new ModeloItemCarrito(1, "Producto", 10.0, 2);
        ModeloItemCarrito item2 = new ModeloItemCarrito(1, "Producto", 10.0, 2);
        assertEquals(item1, item2, "Items con mismos valores deben ser iguales");
    }
    @Test
    public void testHashCode() {
        ModeloItemCarrito item1 = new ModeloItemCarrito(1, "Producto", 10.0, 2);
        ModeloItemCarrito item2 = new ModeloItemCarrito(1, "Producto", 10.0, 2);
        assertEquals(item1.hashCode(), item2.hashCode(), 
            "Items iguales deben tener el mismo hashCode");
    }
    @Test
    public void testItemsDiferentesNoSonIguales() {
        ModeloItemCarrito item1 = new ModeloItemCarrito(1, "Producto A", 10.0, 2);
        ModeloItemCarrito item2 = new ModeloItemCarrito(2, "Producto B", 15.0, 3);
        assertNotEquals(item1, item2, "Items diferentes no deben ser iguales");
    }
}

