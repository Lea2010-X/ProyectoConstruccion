package Controlador;

import DAO.ClienteDAO;
import Modelo.ModeloCliente;
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
 * Prueba unitaria para ControladorCliente.
 * Usa Mockito para simular (mock) la capa DAO.
 */
@ExtendWith(MockitoExtension.class)
public class ControladorClienteTest {

    @Mock 
    private ClienteDAO daoImpostor;

    @InjectMocks
    private ControladorCliente controlador; 

    @Test
    public void testAgregarCliente_DebeConstruirModeloYllamarAlDAO() throws SQLException {
        System.out.println("probando la lógica de agregarCliente");
        String nombre = "Juan";
        String apPaterno = "Perez";
        String apMaterno = "Gomez";
        ArgumentCaptor<ModeloCliente> capturador = ArgumentCaptor.forClass(ModeloCliente.class);
        controlador.agregarCliente(nombre, apPaterno, apMaterno); 
        verify(daoImpostor).agregarCliente(capturador.capture()); 
        ModeloCliente clientePasadoAlDAO = capturador.getValue();
        assertNotNull(clientePasadoAlDAO);
        assertEquals("Juan", clientePasadoAlDAO.getNombre());
        assertEquals("Perez", clientePasadoAlDAO.getApPaterno());
    }

    @Test
    public void testEliminarCliente_DebePasarElIdCorrectoAlDAO() throws SQLException {
        System.out.println("probando la lógica de eliminarCliente");
        int idParaBorrar = 42;
        controlador.eliminarCliente(idParaBorrar);
        verify(daoImpostor).eliminarCliente(42); 
    }
}