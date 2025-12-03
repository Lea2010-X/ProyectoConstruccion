package Modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la clase ModeloReporteVentasPeriodo.
 */
public class ModeloReporteVentasPeriodoTest {

    @Test
    public void testGettersYSetters() {

        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        List<ModeloDetalleVenta> items = new ArrayList<>();
        
        reporte.setItems(items);
        reporte.setTotalGeneral(1500.0);
        
        assertEquals(items, reporte.getItems());
        assertEquals(1500.0, reporte.getTotalGeneral());
    }

    @Test
    public void testValoresNull() {
        
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        reporte.setItems(null);
        
        assertNull(reporte.getItems());
    }
    

    @Test
    public void testReporteCompletoConItems() {

        List<ModeloDetalleVenta> items = new ArrayList<>();
        
        ModeloDetalleVenta item1 = new ModeloDetalleVenta();
        item1.setNombreProducto("Producto A");
        item1.setCantidad(2);
        item1.setPrecioVenta(50.0);
        item1.setSubtotal(100.0);
        items.add(item1);
        
        ModeloDetalleVenta item2 = new ModeloDetalleVenta();
        item2.setNombreProducto("Producto B");
        item2.setCantidad(3);
        item2.setPrecioVenta(30.0);
        item2.setSubtotal(90.0);
        items.add(item2);
        
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        reporte.setItems(items);
        reporte.setTotalGeneral(190.0);
        
        assertNotNull(reporte.getItems());
        assertEquals(2, reporte.getItems().size());
        assertEquals(190.0, reporte.getTotalGeneral());
        assertEquals("Producto A", reporte.getItems().get(0).getNombreProducto());
    }
    
    @Test
    public void testReporteConListaVacia() {
        
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        reporte.setItems(new ArrayList<>());
        reporte.setTotalGeneral(0.0);
        
        assertNotNull(reporte.getItems());
        assertTrue(reporte.getItems().isEmpty());
        assertEquals(0.0, reporte.getTotalGeneral());
    }
    
    @Test
    public void testCalculoTotalGeneral() {
        
        List<ModeloDetalleVenta> items = new ArrayList<>();
        
        ModeloDetalleVenta item1 = new ModeloDetalleVenta();
        item1.setSubtotal(100.0);
        items.add(item1);
        
        ModeloDetalleVenta item2 = new ModeloDetalleVenta();
        item2.setSubtotal(50.5);
        items.add(item2);
        
        double totalCalculado = items.stream()
            .mapToDouble(ModeloDetalleVenta::getSubtotal)
            .sum();
        
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        reporte.setItems(items);
        reporte.setTotalGeneral(totalCalculado);
        
        assertEquals(150.5, reporte.getTotalGeneral());
    }
    
    @Test
    public void testValoresPorDefecto() {
        
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        
        assertNull(reporte.getItems());
        assertEquals(0.0, reporte.getTotalGeneral());
    }
}

