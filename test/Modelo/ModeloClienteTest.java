package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloCliente.
 * Verifica las validaciones y métodos básicos.
 */
public class ModeloClienteTest {

    @Test
    public void testNombreVacioLanzaExcepcion() {
        ModeloCliente cliente = new ModeloCliente();
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setNombre("");
        }, "Debe lanzar excepción cuando el nombre está vacío");
    }
    
    @Test
    public void testNombreNullLanzaExcepcion() {
        ModeloCliente cliente = new ModeloCliente();
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setNombre(null);
        }, "Debe lanzar excepción cuando el nombre es null");
    }
    
    @Test
    public void testApPaternoVacioLanzaExcepcion() {
        ModeloCliente cliente = new ModeloCliente();
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setApPaterno("");
        }, "Debe lanzar excepción cuando el apellido paterno está vacío");
    }
    
    @Test
    public void testApMaternoVacioLanzaExcepcion() {
        ModeloCliente cliente = new ModeloCliente();
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setApMaterno("");
        }, "Debe lanzar excepción cuando el apellido materno está vacío");
    }
    
    @Test
    public void testTrimAutomatico() {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setNombre("  Juan  ");
        cliente.setApPaterno("  Perez  ");
        cliente.setApMaterno("  Lopez  ");
        
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApPaterno());
        assertEquals("Lopez", cliente.getApMaterno());
    }
    
    @Test
    public void testValoresValidosSeAceptan() {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setNombre("Ana");
        cliente.setApPaterno("Sosa");
        cliente.setApMaterno("Paz");
        
        assertEquals("Ana", cliente.getNombre());
        assertEquals("Sosa", cliente.getApPaterno());
        assertEquals("Paz", cliente.getApMaterno());
    }
    
    @Test
    public void testToString() { 
        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Juan");
        cliente.setApPaterno("Perez");
        cliente.setApMaterno("Lopez");
        
        String resultado = cliente.toString();
        
        assertTrue(resultado.contains("Juan"), "toString debe contener el nombre");
        assertTrue(resultado.contains("Perez"), "toString debe contener el apellido paterno");
    }
    
    @Test
    public void testEquals() {
        ModeloCliente c1 = new ModeloCliente();
        c1.setIdCliente(1);
        c1.setNombre("Juan");
        c1.setApPaterno("Perez");
        c1.setApMaterno("Lopez");
        
        ModeloCliente c2 = new ModeloCliente();
        c2.setIdCliente(1);
        c2.setNombre("Ana"); 
        c2.setApPaterno("Gomez");
        c2.setApMaterno("Martinez");
        assertEquals(c1, c2, "Clientes con mismo ID deben ser iguales");
    }
    
    @Test
    public void testHashCode() {
        ModeloCliente c1 = new ModeloCliente();
        c1.setIdCliente(1);
        
        ModeloCliente c2 = new ModeloCliente();
        c2.setIdCliente(1);
        assertEquals(c1.hashCode(), c2.hashCode(), "Clientes iguales deben tener el mismo hashCode");
    }
}

