package Controlador;

import DAO.ClienteDAO;
import Modelo.ModeloCliente;
import java.sql.SQLException;

// Imports de JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

// Imports de Mockito
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify; // Para verificar llamadas
import org.mockito.ArgumentCaptor; // Para capturar argumentos

/**
 * Prueba unitaria para ControladorCliente.
 * Usa Mockito para simular (mock) la capa DAO.
 */
@ExtendWith(MockitoExtension.class) // Activa Mockito
public class ControladorClienteTest {

    @Mock // Crea un DAO impostor
    private ClienteDAO daoImpostor;

    @InjectMocks // Crea un Controlador real e inyecta al impostor
    private ControladorCliente controlador; //

    @Test
    public void testAgregarCliente_DebeConstruirModeloYllamarAlDAO() throws SQLException {
        System.out.println("probando la lógica de agregarCliente");

        // 1. Arrange (Preparar)
        String nombre = "Juan";
        String apPaterno = "Perez";
        String apMaterno = "Gomez";
        
        // Prepara un "capturador" para ver qué objeto se le pasa al DAO
        ArgumentCaptor<ModeloCliente> capturador = ArgumentCaptor.forClass(ModeloCliente.class);

        // 2. Act (Actuar)
        // Llama al método del controlador
        controlador.agregarCliente(nombre, apPaterno, apMaterno); //

        // 3. Assert (Verificar)
        // Verifica que el método 'agregarCliente' del DAO fue llamado EXACTAMENTE 1 VEZ
        // y captura el objeto 'ModeloCliente' que se le pasó.
        verify(daoImpostor).agregarCliente(capturador.capture()); //
        
        // Obtiene el cliente que el controlador construyó
        ModeloCliente clientePasadoAlDAO = capturador.getValue();
        
        // Verifica que el controlador construyó el objeto correctamente
        assertNotNull(clientePasadoAlDAO);
        assertEquals("Juan", clientePasadoAlDAO.getNombre());
        assertEquals("Perez", clientePasadoAlDAO.getApPaterno());
    }

    @Test
    public void testEliminarCliente_DebePasarElIdCorrectoAlDAO() throws SQLException {
        System.out.println("probando la lógica de eliminarCliente");
        
        // 1. Arrange
        int idParaBorrar = 42;
        
        // 2. Act
        controlador.eliminarCliente(idParaBorrar); //
        
        // 3. Assert
        // Verifica que el método 'eliminarCliente' del DAO fue llamado 1 vez
        // y que se le pasó EXACTAMENTE el id 42.
        verify(daoImpostor).eliminarCliente(42); //
    }
}