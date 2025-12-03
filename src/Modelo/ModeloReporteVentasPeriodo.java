package Modelo;

import java.util.List;

import Util.Constantes;

/**
 * DTO para el formulario "Reporte por Fechas".
 * Representa el reporte agregado de ventas de múltiples facturas en un periodo.
 * Contiene la lista de todos los items vendidos y el total general.
 */
public class ModeloReporteVentasPeriodo {

    private List<ModeloDetalleVenta> items;
    private double totalGeneral;

    public List<ModeloDetalleVenta> getItems() { 
        return items; 
    }
    
    public void setItems(List<ModeloDetalleVenta> items) { 
        this.items = items; 
    }

    public double getTotalGeneral() { 
        return totalGeneral; 
    }
    
    public void setTotalGeneral(double totalGeneral) { 
        this.totalGeneral = totalGeneral; 
    }

    public double getTotalIvaGeneral(){
        return (this.totalGeneral*Constantes.TASA_IVA) + this.totalGeneral;
    }
}
