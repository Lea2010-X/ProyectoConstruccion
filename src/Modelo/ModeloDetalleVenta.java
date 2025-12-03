package Modelo;

import java.util.Date;

/**
 * DTO para representar un detalle de venta (una línea de producto vendido).
 * Se utiliza como unidad básica reutilizable en los reportes.
 */
public class ModeloDetalleVenta {
    
    private int idFactura;
    private Date fechaFactura;
    private String nombreProducto;
    private int cantidad;
    private double precioVenta;
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

    public String getNombreProducto() { 
        return nombreProducto; 
    }

    public void setNombreProducto(String nombreProducto) { 
        this.nombreProducto = nombreProducto; 
    }

    public int getCantidad() { 
        return cantidad;
    }

    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public double getPrecioVenta() { 
        return precioVenta; 
    }

    public void setPrecioVenta(double precioVenta) { 
        this.precioVenta = precioVenta; 
    }

    public double getSubtotal() { 
        return subtotal; 
    }

    public void setSubtotal(double subtotal) { 
        this.subtotal = subtotal;
    }
}
