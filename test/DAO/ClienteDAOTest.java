package DAO;

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
public class ClienteDAOTest extends BaseDAOTest {
    
    private ClienteDAO dao;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new ClienteDAO(conectorDePrueba);
        limpiarTablas("cliente");
    }

    @Test
    public void testAgregarYObtenerCliente() throws Exception {
        ModeloCliente clienteNuevo = new ModeloCliente();
        clienteNuevo.setNombre("Ana");
        clienteNuevo.setApPaterno("Sosa");
        clienteNuevo.setApMaterno("Paz");
        dao.agregarCliente(clienteNuevo);
        List<ModeloCliente> clientes = dao.obtenerClientes();
        assertNotNull(clientes, "La lista no debe ser nula.");
        assertEquals(1, clientes.size(), "Debe haber exactamente 1 cliente en la BD.");
        ModeloCliente clienteObtenido = clientes.get(0);
        assertEquals("Ana", clienteObtenido.getNombre());
        assertEquals("Sosa", clienteObtenido.getApPaterno());
    }
    @Test
    public void testEliminarCliente() throws Exception {
        System.out.println("probando eliminarCliente");
        ModeloCliente clienteTemp = new ModeloCliente();
        clienteTemp.setNombre("Cliente");
        clienteTemp.setApPaterno("A");
        clienteTemp.setApMaterno("Borrar");
        dao.agregarCliente(clienteTemp);
        int idClienteCreado = dao.obtenerClientes().get(0).getIdCliente();
        try (Connection conn = getConnection();
            Statement st = conn.createStatement()) {
            st.execute("SET FOREIGN_KEY_CHECKS=0");
            st.execute("DELETE FROM factura WHERE fkcliente=" + idClienteCreado);
            st.execute("SET FOREIGN_KEY_CHECKS=1");
        }
        dao.eliminarCliente(idClienteCreado);
        List<ModeloCliente> clientes = dao.obtenerClientes();
        assertEquals(0, clientes.size(), "La lista debe estar vacía después de eliminar.");
    }
}