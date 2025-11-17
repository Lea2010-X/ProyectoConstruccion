package Modelo;


import java.util.List;

/**
 * DTO para el formulario "Reporte por Fechas".
 * Contiene la lista de todos los items vendidos y el total general.
 */
public class ModeloReporteFechas {

    private List<ModeloDetalleReporte> items;
    private double totalGeneral;

    // Getters y Setters
    public List<ModeloDetalleReporte> getItems() { return items; }
    public void setItems(List<ModeloDetalleReporte> items) { this.items = items; }

    public double getTotalGeneral() { return totalGeneral; }
    public void setTotalGeneral(double totalGeneral) { this.totalGeneral = totalGeneral; }
}