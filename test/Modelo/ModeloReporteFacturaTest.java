package Modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloReporteFactura.
 */
public class ModeloReporteFacturaTest {

    @Test
    public void testGettersYSetters() {
        ModeloReporteFactura comprobante = new ModeloReporteFactura();
        Date fecha = new Date();
        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Juan");
        cliente.setApPaterno("Perez");
        cliente.setApMaterno("Lopez");
        
        List<ModeloDetalleVenta> items = new ArrayList<>();
        
        comprobante.setIdFactura(100);
        comprobante.setFechaFactura(fecha);
        comprobante.setCliente(cliente);
        comprobante.setItems(items);
        comprobante.setIva(18.0);
        comprobante.setTotal(118.0);
        
        assertEquals(100, comprobante.getIdFactura());
        assertEquals(fecha, comprobante.getFechaFactura());
        assertEquals(cliente, comprobante.getCliente());
        assertEquals(items, comprobante.getItems());
        assertEquals(18.0, comprobante.getIva());
        assertEquals(118.0, comprobante.getTotal());
    }
    
    @Test
    public void testValoresNull() {
        
        ModeloReporteFactura comprobante = new ModeloReporteFactura();
        comprobante.setFechaFactura(null);
        comprobante.setCliente(null);
        comprobante.setItems(null);
        
        assertNull(comprobante.getFechaFactura());
        assertNull(comprobante.getCliente());
        assertNull(comprobante.getItems());
    }
    
    @Test
    public void testComprobanteCompleto() {
        
        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Ana");
        cliente.setApPaterno("Gomez");
        cliente.setApMaterno("Martinez");
        
        List<ModeloDetalleVenta> items = new ArrayList<>();
        ModeloDetalleVenta item1 = new ModeloDetalleVenta();
        item1.setNombreProducto("Producto 1");
        item1.setCantidad(2);
        item1.setPrecioVenta(50.0);
        item1.setSubtotal(100.0);
        items.add(item1);
        
        ModeloReporteFactura comprobante = new ModeloReporteFactura();
        comprobante.setIdFactura(1);
        comprobante.setFechaFactura(new Date());
        comprobante.setCliente(cliente);
        comprobante.setItems(items);
        comprobante.setTotal(100.0);
        comprobante.setIva(18.0);
        
        assertNotNull(comprobante.getCliente());
        assertEquals("Ana", comprobante.getCliente().getNombre());
        assertEquals(1, comprobante.getItems().size());
        assertEquals(100.0, comprobante.getTotal());
    }
    
    @Test
    public void testValoresPorDefecto() {
        
        ModeloReporteFactura comprobante = new ModeloReporteFactura();
        
        assertEquals(0, comprobante.getIdFactura());
        assertNull(comprobante.getFechaFactura());
        assertNull(comprobante.getCliente());
        assertNull(comprobante.getItems());
        assertEquals(0.0, comprobante.getIva());
        assertEquals(0.0, comprobante.getTotal());
    }
}

