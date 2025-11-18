package Modelo;

/**
 * Representa la entidad 'Producto' de la base de datos.
 * Almacena y transporta los datos de un producto
 * (ID, nombre, precio, stock) entre las capas de la aplicación.
 * Implementa la interfaz PorPagar para definir un método
 * que calcula el valor total del inventario de este producto.
 */
public class ModeloProducto implements PorPagar {
    
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;

    //getters y setters
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
        this.nombreProducto = nombreProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }  

    @Override
    public double ObtenerMontoPago() {
        return precioProducto * stockProducto;
    }
    
    
}
