package Util;

import Modelo.ModeloCliente;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloReporteVentasPeriodo;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test para la clase GeneradorPDF.
 */
class GeneradorPDFTest {

    private static final Logger logger = Logger.getLogger(GeneradorPDFTest.class.getName());
    private String rutaPDFGenerado;

    @BeforeEach
    void setUp() {
        File directorio = new File("reportes_pdf");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }

    @AfterEach
    void tearDown() {
        if (rutaPDFGenerado != null) {
            Path archivo = Paths.get(rutaPDFGenerado);
            if (Files.exists(archivo)) {
                try {
                    Files.delete(archivo);
                } catch (IOException e) {
                    logger.log(Level.WARNING, e, () -> "No se pudo eliminar el archivo de prueba: " + rutaPDFGenerado);
                }
            }
        }
    }

    @Test
    void testGenerarPDFReporteFactura_DebeCrearArchivoPDF() throws DocumentException, IOException {
        ModeloReporteFactura reporte = crearReporteFacturaPrueba();
        
        rutaPDFGenerado = GeneradorPDF.generarPDFReporteFactura(reporte);

        assertNotNull(rutaPDFGenerado, "La ruta del PDF no debe ser nula");
        File archivoPDF = new File(rutaPDFGenerado);
        assertTrue(archivoPDF.exists(), "El archivo PDF debe existir");
        assertTrue(archivoPDF.length() > 0, "El archivo PDF no debe estar vacío");
        assertTrue(rutaPDFGenerado.endsWith(".pdf"), "El archivo debe tener extensión .pdf");
    }

    @Test
    void testGenerarPDFReporteVentasPeriodo_DebeCrearArchivoPDF() throws DocumentException, IOException {
        ModeloReporteVentasPeriodo reporte = crearReporteVentasPeriodoPrueba();

        rutaPDFGenerado = GeneradorPDF.generarPDFReporteVentasPeriodo(reporte);

        assertNotNull(rutaPDFGenerado, "La ruta del PDF no debe ser nula");
        File archivoPDF = new File(rutaPDFGenerado);
        assertTrue(archivoPDF.exists(), "El archivo PDF debe existir");
        assertTrue(archivoPDF.length() > 0, "El archivo PDF no debe estar vacío");
        assertTrue(rutaPDFGenerado.endsWith(".pdf"), "El archivo debe tener extensión .pdf");
    }

    @Test
    void testGenerarPDFReporteFactura_ConMultiplesProductos() throws DocumentException, IOException {
        ModeloReporteFactura reporte = crearReporteFacturaPrueba();

        ModeloDetalleVenta item2 = new ModeloDetalleVenta();
        item2.setNombreProducto("Producto 2");
        item2.setCantidad(3);
        item2.setPrecioVenta(25.00);
        item2.setSubtotal(75.00);
        reporte.getItems().add(item2);

        ModeloDetalleVenta item3 = new ModeloDetalleVenta();
        item3.setNombreProducto("Producto 3");
        item3.setCantidad(1);
        item3.setPrecioVenta(150.00);
        item3.setSubtotal(150.00);
        reporte.getItems().add(item3);

        rutaPDFGenerado = GeneradorPDF.generarPDFReporteFactura(reporte);

        File archivoPDF = new File(rutaPDFGenerado);
        assertTrue(archivoPDF.exists(), "El archivo PDF debe existir");
        assertTrue(archivoPDF.length() > 1000, "El PDF con múltiples productos debe tener contenido significativo");
    }

    @Test
    void testGenerarPDFReporteVentasPeriodo_ConMultiplesVentas() throws DocumentException, IOException {
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        List<ModeloDetalleVenta> items = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            ModeloDetalleVenta item = new ModeloDetalleVenta();
            item.setIdFactura(i);
            item.setFechaFactura(new Date());
            item.setNombreProducto("Producto " + i);
            item.setCantidad(i);
            item.setPrecioVenta(10.00 * i);
            item.setSubtotal(10.00 * i * i);
            items.add(item);
        }

        reporte.setItems(items);
        reporte.setTotalGeneral(550.00);

        rutaPDFGenerado = GeneradorPDF.generarPDFReporteVentasPeriodo(reporte);

        File archivoPDF = new File(rutaPDFGenerado);
        assertTrue(archivoPDF.exists(), "El archivo PDF debe existir");
        assertTrue(archivoPDF.length() > 1000, "El PDF con múltiples ventas debe tener contenido significativo");
    }

    @Test
    void testNombreArchivoPDF_DebeSerUnico() throws DocumentException, IOException, InterruptedException {

        ModeloReporteFactura reporte = crearReporteFacturaPrueba();

        String rutaPDF1 = GeneradorPDF.generarPDFReporteFactura(reporte);
        Thread.sleep(1100); // Esperar más de 1 segundo para asegurar timestamp diferente
        String rutaPDF2 = GeneradorPDF.generarPDFReporteFactura(reporte);

        assertNotEquals(rutaPDF1, rutaPDF2, "Los archivos PDF deben tener nombres únicos");

        Path archivoTemporal = Paths.get(rutaPDF2);
        try {
            Files.delete(archivoTemporal);
        } catch (IOException e) {
            fail("El archivo temporal debe poder eliminarse: " + e.getMessage());
        }
        rutaPDFGenerado = rutaPDF1; 
    }

    private ModeloReporteFactura crearReporteFacturaPrueba() {
        ModeloReporteFactura reporte = new ModeloReporteFactura();
        reporte.setIdFactura(123);
        reporte.setFechaFactura(new Date());

        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(1);
        cliente.setNombre("Juan");
        cliente.setApPaterno("Pérez");
        cliente.setApMaterno("García");
        reporte.setCliente(cliente);

        List<ModeloDetalleVenta> items = new ArrayList<>();
        ModeloDetalleVenta item = new ModeloDetalleVenta();
        item.setNombreProducto("Producto Test");
        item.setCantidad(2);
        item.setPrecioVenta(50.00);
        item.setSubtotal(100.00);
        items.add(item);
        reporte.setItems(items);

        reporte.setIva(18.00);
        reporte.setTotal(118.00);

        return reporte;
    }

    private ModeloReporteVentasPeriodo crearReporteVentasPeriodoPrueba() {
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        
        List<ModeloDetalleVenta> items = new ArrayList<>();
        ModeloDetalleVenta item = new ModeloDetalleVenta();
        item.setIdFactura(100);
        item.setFechaFactura(new Date());
        item.setNombreProducto("Producto Test Período");
        item.setCantidad(5);
        item.setPrecioVenta(20.00);
        item.setSubtotal(100.00);
        items.add(item);

        reporte.setItems(items);
        reporte.setTotalGeneral(100.00);

        return reporte;
    }
}

