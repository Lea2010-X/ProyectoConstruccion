package DAO;

import Configuracion.CConexion;
import Configuracion.CConexionTest;
import Modelo.ModeloCliente;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba de Integración para ClienteDAO.
 * Esta prueba SÍ se conecta a la base de datos (dbpos_test).
 */
public class ClienteDAOTest {
    
    private ClienteDAO dao;
    private CConexion conectorDePrueba;

    /**
     * Esto se ejecuta ANTES de CADA prueba (@Test).
     */
    @BeforeEach
    public void setUp() throws Exception {
        // 1. Usa la conexión de PRUEBA
        conectorDePrueba = new CConexionTest();
        
        // 2. Inyecta la conexión de prueba en el DAO
        dao = new ClienteDAO(conectorDePrueba);
        
        // 3. Limpia la tabla 'cliente' en la BD de prueba
        // Esto asegura que cada prueba inicie con la tabla vacía.
        try (Connection conn = conectorDePrueba.estableceConexion();
            Statement st = conn.createStatement()) {
            
            st.execute("SET FOREIGN_KEY_CHECKS=0"); // Desactiva FK para truncar
            st.execute("TRUNCATE TABLE cliente");
            st.execute("SET FOREIGN_KEY_CHECKS=1"); // Reactiva FK
        }
    }

    /**
     * Prueba si podemos agregar un cliente y luego obtenerlo.
     */
    @Test
    public void testAgregarYObtenerCliente() throws Exception {
        System.out.println("probando agregarCliente y obtenerClientes");
        
        // Arrange (Preparar)
        ModeloCliente clienteNuevo = new ModeloCliente();
        clienteNuevo.setNombre("Ana");
        clienteNuevo.setApPaterno("Sosa");
        clienteNuevo.setApMaterno("Paz");
        
        // Act (Actuar)
        // Agregamos el cliente (esto usa la BD dbpos_test)
        dao.agregarCliente(clienteNuevo);
        
        // Leemos TODOS los clientes de la BD (dbpos_test)
        List<ModeloCliente> clientes = dao.obtenerClientes();
        
        // Assert (Verificar)
        assertNotNull(clientes, "La lista no debe ser nula.");
        assertEquals(1, clientes.size(), "Debe haber exactamente 1 cliente en la BD.");
        
        ModeloCliente clienteObtenido = clientes.get(0);
        assertEquals("Ana", clienteObtenido.getNombre());
        assertEquals("Sosa", clienteObtenido.getApPaterno());
    }

    /**
     * Prueba si podemos eliminar un cliente.
     */
    @Test
    public void testEliminarCliente() throws Exception {
        System.out.println("probando eliminarCliente");

        // Arrange (Preparar) - Insertamos un cliente
        ModeloCliente clienteTemp = new ModeloCliente();
        clienteTemp.setNombre("Cliente");
        clienteTemp.setApPaterno("A");
        clienteTemp.setApMaterno("Borrar");
        dao.agregarCliente(clienteTemp);

        // Obtenemos su ID
        int idClienteCreado = dao.obtenerClientes().get(0).getIdCliente();
        
        // Act (Actuar)
        dao.eliminarCliente(idClienteCreado);

        // Assert (Verificar)
        List<ModeloCliente> clientes = dao.obtenerClientes();
        assertEquals(0, clientes.size(), "La lista debe estar vacía después de eliminar.");
    }
}