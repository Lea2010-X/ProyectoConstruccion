package Modelo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Pruebas unitarias para ModeloDetalleVenta.
 */
public class ModeloDetalleVentaTest {

    @Test
    public void testGettersAndSetters() {
        ModeloDetalleVenta detalle = new ModeloDetalleVenta();
        Date fecha = new Date();
        
        detalle.setIdFactura(1);
        detalle.setFechaFactura(fecha);
        detalle.setNombreProducto("Producto Test");
        detalle.setCantidad(5);
        detalle.setPrecioVenta(10.0);
        detalle.setSubtotal(50.0);
        
        assertEquals(1, detalle.getIdFactura());
        assertEquals(fecha, detalle.getFechaFactura());
        assertEquals("Producto Test", detalle.getNombreProducto());
        assertEquals(5, detalle.getCantidad());
        assertEquals(10.0, detalle.getPrecioVenta(), 0.001);
        assertEquals(50.0, detalle.getSubtotal(), 0.001);
    }

    @Test
    public void testCalculoSubtotalManual() {
        ModeloDetalleVenta detalle = new ModeloDetalleVenta();
        detalle.setCantidad(3);
        detalle.setPrecioVenta(15.5);
        double subtotalEsperado = 3 * 15.5;
        detalle.setSubtotal(subtotalEsperado);
        assertEquals(46.5, detalle.getSubtotal(), 0.001);
    }

    @Test
    public void testValoresIniciales() {
        ModeloDetalleVenta detalle = new ModeloDetalleVenta();
        assertEquals(0, detalle.getIdFactura());
        assertNull(detalle.getFechaFactura());
        assertNull(detalle.getNombreProducto());
        assertEquals(0, detalle.getCantidad());
        assertEquals(0.0, detalle.getPrecioVenta(), 0.001);
        assertEquals(0.0, detalle.getSubtotal(), 0.001);
    }
}

