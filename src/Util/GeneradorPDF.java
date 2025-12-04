package Util;

import Modelo.ModeloDetalleVenta;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloReporteVentasPeriodo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase utilitaria para generar documentos PDF de reportes.
 * Utiliza la librería iText 5 para la generación de PDFs.
 */
public class GeneradorPDF {
    
    private static final String DIRECTORIO_PDF = "reportes_pdf";
    private static final Font FONT_TITULO = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
    private static final Font FONT_SUBTITULO = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    private static final Font FONT_NORMAL = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
    private static final Font FONT_TABLA_HEADER = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
    private static final Font FONT_TABLA_CONTENT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
    
    /**
     * Genera un PDF con el reporte de una factura específica.
     * 
     * @param reporte El modelo con los datos del reporte de la factura
     * @return La ruta del archivo PDF generado
     * @throws DocumentException Si hay un error al crear el documento
     * @throws IOException Si hay un error al escribir el archivo
     */
    public static String generarPDFReporteFactura(ModeloReporteFactura reporte) throws DocumentException, IOException {
        crearDirectorioSiNoExiste();
        
        String nombreArchivo = generarNombreArchivo("Factura_" + reporte.getIdFactura());
        String rutaCompleta = DIRECTORIO_PDF + File.separator + nombreArchivo;
        
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
        
        document.open();
        
        Paragraph titulo = new Paragraph("REPORTE DE FACTURA", FONT_TITULO);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);
        
        document.add(new Paragraph("Factura N°: " + reporte.getIdFactura(), FONT_SUBTITULO));
        document.add(new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(reporte.getFechaFactura()), FONT_NORMAL));
        document.add(Chunk.NEWLINE);
        
        Paragraph clienteTitulo = new Paragraph("Datos del Cliente", FONT_SUBTITULO);
        clienteTitulo.setSpacingBefore(10);
        document.add(clienteTitulo);
        
        document.add(new Paragraph("Nombre: " + reporte.getCliente().getNombre() + " " + 
                reporte.getCliente().getApPaterno() + " " + 
                reporte.getCliente().getApMaterno(), FONT_NORMAL));
        document.add(Chunk.NEWLINE);
        
        Paragraph productosTitulo = new Paragraph("Detalle de Productos", FONT_SUBTITULO);
        productosTitulo.setSpacingBefore(10);
        productosTitulo.setSpacingAfter(10);
        document.add(productosTitulo);
        
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{3, 1, 2, 2,3});
        
        agregarCeldaHeader(tabla, "Producto");
        agregarCeldaHeader(tabla, "Cantidad");
        agregarCeldaHeader(tabla, "Precio");
        agregarCeldaHeader(tabla, "Subtotal");
        agregarCeldaHeader(tabla, "total");
        
        for (ModeloDetalleVenta item : reporte.getItems()) {
            agregarCeldaContenido(tabla, item.getNombreProducto());
            agregarCeldaContenido(tabla, String.valueOf(item.getCantidad()));
            agregarCeldaContenido(tabla, String.format("$%.2f", item.getPrecioVenta()));
            agregarCeldaContenido(tabla, String.format("$%.2f", item.getSubtotal()));
            agregarCeldaContenido(tabla, String.format("$%.2f", (item.getSubtotal() * Constantes.TASA_IVA)+item.getSubtotal()));
        }
        
        document.add(tabla);
        
        Paragraph totales = new Paragraph();
        totales.setSpacingBefore(20);
        totales.setAlignment(Element.ALIGN_RIGHT);
        totales.add(new Chunk("IVA: $" + String.format("%.2f", reporte.getIva()) + "\n", FONT_NORMAL));
        totales.add(new Chunk("TOTAL: $" + String.format("%.2f", 
        (reporte.getSubtotal()*Constantes.TASA_IVA)+reporte.getSubtotal()), FONT_SUBTITULO));
        document.add(totales);
        
        Paragraph piePagina = new Paragraph();
        piePagina.setSpacingBefore(30);
        piePagina.setAlignment(Element.ALIGN_CENTER);
        piePagina.add(new Chunk("Generado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), 
            new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY)));
        document.add(piePagina);
        
        document.close();
        
        return rutaCompleta;
    }
    
    /**
     * Genera un PDF con el reporte de ventas de un periodo.
     * 
     * @param reporte El modelo con los datos del reporte de ventas
     * @return La ruta del archivo PDF generado
     * @throws DocumentException Si hay un error al crear el documento
     * @throws IOException Si hay un error al escribir el archivo
     */
    public static String generarPDFReporteVentasPeriodo(ModeloReporteVentasPeriodo reporte) throws DocumentException, IOException {
        crearDirectorioSiNoExiste();
        
        String nombreArchivo = generarNombreArchivo("Reporte_Ventas");
        String rutaCompleta = DIRECTORIO_PDF + File.separator + nombreArchivo;
        
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        PdfWriter.getInstance(document, new FileOutputStream(rutaCompleta));
        
        document.open();
        
        Paragraph titulo = new Paragraph("REPORTE DE VENTAS POR PERIODO", FONT_TITULO);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);
        
        document.add(new Paragraph("Fecha de generación: " + 
            new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()), FONT_NORMAL));
        document.add(Chunk.NEWLINE);
        
        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1, 2, 3, 1.5f, 1.5f, 1.5f,1.5f});
        
        agregarCeldaHeader(tabla, "ID Factura");
        agregarCeldaHeader(tabla, "Fecha");
        agregarCeldaHeader(tabla, "Producto");
        agregarCeldaHeader(tabla, "Cantidad");
        agregarCeldaHeader(tabla, "Precio");
        agregarCeldaHeader(tabla, "Subtotal");
        agregarCeldaHeader(tabla, "Total");
        
        for (ModeloDetalleVenta item : reporte.getItems()) {
            agregarCeldaContenido(tabla, String.valueOf(item.getIdFactura()));
            agregarCeldaContenido(tabla, new SimpleDateFormat("dd/MM/yyyy").format(item.getFechaFactura()));
            agregarCeldaContenido(tabla, item.getNombreProducto());
            agregarCeldaContenido(tabla, String.valueOf(item.getCantidad()));
            agregarCeldaContenido(tabla, String.format("$%.2f", item.getPrecioVenta()));
            agregarCeldaContenido(tabla, String.format("$%.2f", item.getSubtotal()));
            //se calcula el subtotal
            agregarCeldaContenido(tabla, String.format("$%.2f", (item.getSubtotal()*Constantes.TASA_IVA)+item.getSubtotal()));
        }
        
        document.add(tabla);
        
        Paragraph totalGeneral = new Paragraph();
        totalGeneral.setSpacingBefore(20);
        totalGeneral.setAlignment(Element.ALIGN_RIGHT);
        totalGeneral.add(new Chunk("INGRESOS TOTALES: $" + String.format("%.2f", (
            reporte.getTotalGeneral()*Constantes.TASA_IVA)+reporte.getTotalGeneral()), 
            new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK)));
        document.add(totalGeneral);
        
        Paragraph piePagina = new Paragraph();
        piePagina.setSpacingBefore(30);
        piePagina.setAlignment(Element.ALIGN_CENTER);
        piePagina.add(new Chunk("Generado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), 
            new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY)));
        document.add(piePagina);
        
        document.close();
        
        return rutaCompleta;
    }
    
    private static void crearDirectorioSiNoExiste() {
        File directorio = new File(DIRECTORIO_PDF);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
    
    /**
     * Genera un nombre de archivo único basado en el prefijo y la fecha actual.
     * 
     * @param prefijo El prefijo del nombre del archivo
     * @return El nombre del archivo generado
     */
    private static String generarNombreArchivo(String prefijo) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return prefijo + "_" + timestamp + ".pdf";
    }
    
    private static void agregarCeldaHeader(PdfPTable tabla, String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, FONT_TABLA_HEADER));
        celda.setBackgroundColor(new BaseColor(52, 73, 94));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(8);
        tabla.addCell(celda);
    }
    
    private static void agregarCeldaContenido(PdfPTable tabla, String texto) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, FONT_TABLA_CONTENT));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(5);
        tabla.addCell(celda);
    }
}

