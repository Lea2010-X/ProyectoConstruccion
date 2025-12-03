package Controlador;

import DAO.ClienteDAO;
import DAO.DetalleDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import Modelo.ModeloItemCarrito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Prueba unitaria para ControladorVenta.
 * Verifica la lógica de orquestación al procesar una venta.
 */
@ExtendWith(MockitoExtension.class)
public class ControladorVentaTest {

    @Mock
    private ClienteDAO clienteDAO;
    @Mock
    private ProductoDAO productoDAO;
    @Mock
    private FacturaDAO facturaDAO;
    @Mock
    private DetalleDAO detalleDAO;

    @InjectMocks 
    private ControladorVenta controlador; 

    @Test
    public void testProcesarVenta_DebeOrquestarDAOsCorrectamente() throws SQLException {
        int idCliente = 1;
        int idFacturaNueva = 99; 
        List<ModeloItemCarrito> carrito = new ArrayList<>();
        carrito.add(new ModeloItemCarrito(10, "Lápiz", 15.0, 2, 30.0));
        carrito.add(new ModeloItemCarrito(20, "Goma", 5.0, 3, 15.0));
        when(facturaDAO.crearFactura(1)).thenReturn(idFacturaNueva); 
        controlador.procesarVenta(idCliente, carrito);
        verify(facturaDAO, times(1)).crearFactura(1); 
        verify(detalleDAO, times(1)).agregarDetalle(99, 10, 2, 15.0); 
        verify(productoDAO, times(1)).actualizarStock(10, 2); 
        verify(detalleDAO, times(1)).agregarDetalle(99, 20, 3, 5.0); 
        verify(productoDAO, times(1)).actualizarStock(20, 3); 
    }
}