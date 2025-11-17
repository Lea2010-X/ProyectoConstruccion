package Modelo;
/**
 * DTO para representar un item en el resumen de venta (carrito de compras).
 */
public class ModeloItemVenta {
    
    private int idProducto;
    private String nombreProducto;
    private double precioVenta;
    private int cantidad;
    private double subtotal;

    // Constructor
    public ModeloItemVenta(int idProducto, String nombreProducto, double precioVenta, int cantidad, double subtotal) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters
    public int getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecioVenta() { return precioVenta; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}