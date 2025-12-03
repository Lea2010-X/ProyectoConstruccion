package Modelo;

/**
 * Representa un item inmutable en el carrito de compras durante una venta.
 * Una vez creado, sus valores no pueden ser modificados (patrón de inmutabilidad).
 * 
 * Esta clase es inmutable por seguridad: los items del carrito no deberían
 * cambiar después de ser agregados.
 */
public final class ModeloItemCarrito {
    
    private final int idProducto;
    private final String nombreProducto;
    private final double precioVenta;
    private final int cantidad;
    private final double subtotal;

    /**
     * Constructor que crea un item de carrito inmutable.
     * Solo valida la cantidad porque es lo que ingresa el usuario.
     * Los demás datos vienen de la BD y ya están validados.
     * 
     * @param idProducto ID del producto (de la BD)
     * @param nombreProducto Nombre del producto (de la BD)
     * @param precioVenta Precio de venta (del producto en BD)
     * @param cantidad Cantidad seleccionada (ingresada por usuario)
     * @throws IllegalArgumentException si la cantidad es inválida
     */
    public ModeloItemCarrito(int idProducto, String nombreProducto, double precioVenta, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.subtotal = precioVenta * cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    @Override
    public String toString() {
        return "ItemCarrito{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombreProducto + '\'' +
                ", precioVenta=" + precioVenta +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        ModeloItemCarrito that = (ModeloItemCarrito) o;
        
        if (idProducto != that.idProducto) return false;
        if (Double.compare(that.precioVenta, precioVenta) != 0) return false;
        if (cantidad != that.cantidad) return false;
        return Double.compare(that.subtotal, subtotal) == 0;
    }
    
    @Override
    public int hashCode() {
        int codigoHash = idProducto;
        long bitsDecimalPrecio = Double.doubleToLongBits(precioVenta);
        codigoHash = 31 * codigoHash + (int) (bitsDecimalPrecio ^ (bitsDecimalPrecio >>> 32));
        
        codigoHash = 31 * codigoHash + cantidad;
        
        long bitsDecimalSubtotal = Double.doubleToLongBits(subtotal);
        codigoHash = 31 * codigoHash + (int) (bitsDecimalSubtotal ^ (bitsDecimalSubtotal >>> 32));
        
        return codigoHash;
    }
}
