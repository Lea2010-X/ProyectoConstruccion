package Modelo;

import java.util.Date;
import java.util.List;

/**
 * DTO para el formulario "Buscar Comprobante".
 * Representa el reporte completo de una factura específica.
 * Contiene los datos del cliente y la lista de productos de esa factura.
 */
public class ModeloReporteFactura {

    private int idFactura;
    private Date fechaFactura;
    private ModeloCliente cliente;
    private List<ModeloDetalleVenta> items;
    private double iva;
    private double subtotal;

    public int getIdFactura() { 
        return idFactura; 
    }

    public void setIdFactura(int idFactura) { 
        this.idFactura = idFactura; 
    }

    public Date getFechaFactura() { 
        return fechaFactura; 
    }

    public void setFechaFactura(Date fechaFactura) { 
        this.fechaFactura = fechaFactura; 
    }

    public ModeloCliente getCliente() { 
        return cliente; 
    }
    
    public void setCliente(ModeloCliente cliente) { 
        this.cliente = cliente; 
    }

    public List<ModeloDetalleVenta> getItems() { 
        return items; 
    }
    
    public void setItems(List<ModeloDetalleVenta> items) { 
        this.items = items; 
    }

    public double getIva() { 
        return iva; 
    }
    
    public void setIva(double iva) { 
        this.iva = iva; 
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        double ivaCalculado = this.subtotal * Util.Constantes.TASA_IVA;
        return this.subtotal + ivaCalculado;
    }
}
