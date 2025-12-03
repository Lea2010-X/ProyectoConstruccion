package DAO;

import Modelo.ModeloCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Prueba de Integración para FacturaDAO.
 */
public class FacturaDAOTest extends BaseDAOTest {

    private FacturaDAO dao;
    private ClienteDAO clienteDAO;
    
    private int idClientePrueba;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
        dao = new FacturaDAO(conectorDePrueba);
        clienteDAO = new ClienteDAO(conectorDePrueba);

        limpiarTablas("detalle", "factura", "cliente");
        ModeloCliente cliente = new ModeloCliente();
        cliente.setNombre("ClienteFactura");
        cliente.setApPaterno("Test");
        cliente.setApMaterno("Test");
        clienteDAO.agregarCliente(cliente);
        idClientePrueba = clienteDAO.obtenerClientes().get(0).getIdCliente();
    }

    @Test
    public void testCrearFacturaYObtenerUltimaFacturaID() throws Exception {
        System.out.println("probando crearFactura y obtenerUltimaFacturaID");
        int idFacturaCreada = dao.crearFactura(idClientePrueba);
        int ultimoID = dao.obtenerUltimaFacturaID();
        assertTrue(idFacturaCreada > 0, "El ID devuelto debe ser mayor a 0.");
        assertEquals(idFacturaCreada, ultimoID, "El último ID de factura debe ser el que acabamos de crear.");
    }
}