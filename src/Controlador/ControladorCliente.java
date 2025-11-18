package Controlador;

import DAO.ClienteDAO;
import Modelo.ModeloCliente;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para la entidad Cliente.
 * Su función es coordinar la Vista (FrmClientes) con la capa de acceso a datos (ClienteDAO).
 */
public final class ControladorCliente {

    private ClienteDAO clienteDAO; 

    /**
     * Constructor estándar para la aplicación.
     * Usa el DAO real.
     */
    public ControladorCliente() {
        this.clienteDAO = new ClienteDAO();
    }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     */
    public ControladorCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO; // Usa el DAO "inyectado"
    }

    public List<ModeloCliente> obtenerClientes() throws SQLException {
        return this.clienteDAO.obtenerClientes();
    }

    public void agregarCliente(String nombre, String appaterno, String apmaterno) throws SQLException {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setNombre(nombre);
        cliente.setApPaterno(appaterno);
        cliente.setApMaterno(apmaterno);

        this.clienteDAO.agregarCliente(cliente);
    }

    public void modificarCliente(int id, String nombre, String appaterno, String apmaterno) throws SQLException {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(id);
        cliente.setNombre(nombre);
        cliente.setApPaterno(appaterno);
        cliente.setApMaterno(apmaterno);

        this.clienteDAO.modificarCliente(cliente);
    }

    public void eliminarCliente(int id) throws SQLException {
        this.clienteDAO.eliminarCliente(id);
    }
}