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

    private ClienteDAO clienteDAO; // Atributo privado

    /**
     * Constructor estándar para la aplicación.
     * Usa el DAO real.
     */
    public ControladorCliente() {
        this.clienteDAO = new ClienteDAO();
    }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param clienteDAO Un DAO (puede ser uno real o un mock).
     */
    public ControladorCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO; // Usa el DAO "inyectado"
    }

    /**
     * Pide al DAO la lista de todos los clientes.
     * @return Lista de ModeloCliente
     * @throws SQLException si el DAO falla.
     */
    public List<ModeloCliente> obtenerClientes() throws SQLException {
        return this.clienteDAO.obtenerClientes();
    }

    /**
     * Procesa la solicitud de agregar un nuevo cliente.
     * @param nombre Nombre del cliente.
     * @param appaterno Apellido paterno.
     * @param apmaterno Apellido materno.
     * @throws SQLException si el DAO falla.
     */
    public void agregarCliente(String nombre, String appaterno, String apmaterno) throws SQLException {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setNombre(nombre);
        cliente.setApPaterno(appaterno);
        cliente.setApMaterno(apmaterno);

        this.clienteDAO.agregarCliente(cliente);
    }

    /**
     * Procesa la solicitud de modificar un cliente.
     * @param id El ID del cliente a modificar.
     * @param nombre El nuevo nombre.
     * @param appaterno El nuevo apellido paterno.
     * @param apmaterno El nuevo apellido materno.
     * @throws SQLException si el DAO falla.
     */
    public void modificarCliente(int id, String nombre, String appaterno, String apmaterno) throws SQLException {
        ModeloCliente cliente = new ModeloCliente();
        cliente.setIdCliente(id);
        cliente.setNombre(nombre);
        cliente.setApPaterno(appaterno);
        cliente.setApMaterno(apmaterno);

        this.clienteDAO.modificarCliente(cliente);
    }

    /**
     * Procesa la solicitud de eliminar un cliente.
     * @param id El ID del cliente a eliminar.
     * @throws SQLException si el DAO falla.
     */
    public void eliminarCliente(int id) throws SQLException {
        this.clienteDAO.eliminarCliente(id);
    }
}