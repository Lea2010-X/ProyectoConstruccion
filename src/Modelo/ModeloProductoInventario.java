package Modelo;

/**
 * Representa un producto en el inventario/catálogo de la tienda.
 * Almacena y transporta los datos de un producto
 * (ID, nombre, precio, stock) entre las capas de la aplicación.
 */
public class ModeloProductoInventario {
    
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        if (nombreProducto == null || nombreProducto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        this.nombreProducto = nombreProducto.trim();
    }

    public void setPrecioProducto(double precioProducto) {
        if (precioProducto < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precioProducto = precioProducto;
    }

    public void setStockProducto(int stockProducto) {
        if (stockProducto < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stockProducto = stockProducto;
    }  
    
    /**
     * Calcula el valor total del inventario para este producto.
     * (Precio * Stock)
     * @return El monto total del pago (valor del inventario) como un double.
     */
    public double ObtenerMontoPago() {
        return precioProducto * stockProducto;
    }
    
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + idProducto +
                ", nombre='" + nombreProducto + '\'' +
                ", precio=" + precioProducto +
                ", stock=" + stockProducto +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        ModeloProductoInventario that = (ModeloProductoInventario) o;
        return idProducto == that.idProducto;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(idProducto);
    }
}

