package Controlador;

import DAO.ProductoDAO;
import Modelo.ModeloProductoInventario;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import org.mockito.ArgumentCaptor;

/**
 * Prueba unitaria para ControladorProducto.
 * Usa Mockito para simular (mock) la capa DAO.
 */
@ExtendWith(MockitoExtension.class)
public class ControladorProductoTest {

    @Mock 
    private ProductoDAO daoImpostor;

    @InjectMocks
    private ControladorProducto controlador; 

    @Test
    public void testAgregarProducto_DebeConstruirModeloYllamarAlDAO() throws SQLException {
        String nombre = "Lápiz";
        double precio = 15.5;
        int stock = 100;
        ArgumentCaptor<ModeloProductoInventario> capturador = ArgumentCaptor.forClass(ModeloProductoInventario.class);
        controlador.agregarProducto(nombre, precio, stock); 
        verify(daoImpostor).agregarProducto(capturador.capture()); 
        ModeloProductoInventario productoPasadoAlDAO = capturador.getValue();
        assertNotNull(productoPasadoAlDAO);
        assertEquals("Lápiz", productoPasadoAlDAO.getNombreProducto());
        assertEquals(15.5, productoPasadoAlDAO.getPrecioProducto());
        assertEquals(100, productoPasadoAlDAO.getStockProducto());
    }
}