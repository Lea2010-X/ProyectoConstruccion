package Controlador;

import DAO.ProductoDAO;
import Modelo.ModeloProducto;
import java.sql.SQLException;

// Imports de JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

// Imports de Mockito
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

    @Mock // Crea un DAO impostor
    private ProductoDAO daoImpostor;

    @InjectMocks // Crea un Controlador real e inyecta al impostor
    private ControladorProducto controlador; //

    @Test
    public void testAgregarProducto_DebeConstruirModeloYllamarAlDAO() throws SQLException {
        System.out.println("probando la lógica de agregarProducto");

        // 1. Arrange
        String nombre = "Lápiz";
        double precio = 15.5;
        int stock = 100;
        
        ArgumentCaptor<ModeloProducto> capturador = ArgumentCaptor.forClass(ModeloProducto.class);

        // 2. Act
        controlador.agregarProducto(nombre, precio, stock); //

        // 3. Assert
        // Verifica que el DAO fue llamado 1 vez con el objeto correcto
        verify(daoImpostor).agregarProducto(capturador.capture()); //
        
        ModeloProducto productoPasadoAlDAO = capturador.getValue();
        
        assertNotNull(productoPasadoAlDAO);
        assertEquals("Lápiz", productoPasadoAlDAO.getNombreProducto());
        assertEquals(15.5, productoPasadoAlDAO.getPrecioProducto());
        assertEquals(100, productoPasadoAlDAO.getStockProducto());
    }
} 
