package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Configuracion.CConexion;
import Modelo.*; 

/**
 * Esta clase maneja toda la lógica de acceso a datos para la entidad Cliente.
 */
public class ClienteDAO {
	private CConexion conector;
	
	/**
     * Constructor estándar para la aplicación.
     * Usa la conexión de producción.
     */
    public ClienteDAO() {
        this.conector = new CConexion();
    }

    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param conector Un conector de base de datos (ej. uno de prueba).
     */
    public ClienteDAO(CConexion conector) {
        this.conector = conector;
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     * @return una lista de objetos ModeloCliente.
     * @throws SQLException si ocurre un error de SQL.
     */
    public List<ModeloCliente> obtenerClientes() throws SQLException {
        List<ModeloCliente> clientes = new ArrayList<>();
        String sql = "SELECT idcliente, nombre, appaterno, apmaterno FROM cliente";

        try (Connection conn = conector.estableceConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ModeloCliente cliente = new ModeloCliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApPaterno(rs.getString("appaterno"));
                cliente.setApMaterno(rs.getString("apmaterno"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    /**
     * Agrega un nuevo cliente a la base de datos.
     * @param cliente El objeto ModeloCliente con los datos a guardar.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void agregarCliente(ModeloCliente cliente) throws SQLException {
        String consulta = "INSERT INTO cliente (nombre, appaterno, apmaterno) VALUES (?, ?, ?)";

        try (Connection conn = conector.estableceConexion();
            CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApPaterno());
            cs.setString(3, cliente.getApMaterno());
            cs.execute();
        }
    }

    /**
     * Modifica un cliente existente en la base de datos.
     * @param cliente El objeto ModeloCliente con los datos actualizados.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void modificarCliente(ModeloCliente cliente) throws SQLException {
        String consulta = "UPDATE cliente SET nombre = ?, appaterno = ?, apmaterno = ? WHERE idcliente = ?";

        try (Connection conn = conector.estableceConexion();
            CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApPaterno());
            cs.setString(3, cliente.getApMaterno());
            cs.setInt(4, cliente.getIdCliente());
            cs.execute();
        }
    }

    /**
     * Elimina un cliente de la base de datos usando su ID.
     * @param idCliente El ID del cliente a eliminar.
     * @throws SQLException si ocurre un error de SQL.
     */
    public void eliminarCliente(int idCliente) throws SQLException {
        String consulta = "DELETE FROM cliente WHERE idcliente = ?";

        try (Connection conn = conector.estableceConexion();
            CallableStatement cs = conn.prepareCall(consulta)) {

            cs.setInt(1, idCliente);
            cs.execute();
        }
    }

    /**
     * Busca clientes por nombre (coincidencia parcial).
     * @param nombre El término de búsqueda.
     * @return una lista de objetos ModeloCliente.
     * @throws SQLException si ocurre un error de SQL.
     */
    public List<ModeloCliente> buscarPorNombre(String nombre) throws SQLException {
        List<ModeloCliente> clientes = new ArrayList<>();
        String consulta = "SELECT idcliente, nombre, appaterno, apmaterno FROM cliente WHERE nombre LIKE CONCAT('%', ?, '%')";

        try (Connection conn = conector.estableceConexion();
             PreparedStatement ps = conn.prepareStatement(consulta)) {
            
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ModeloCliente cliente = new ModeloCliente();
                    cliente.setIdCliente(rs.getInt("idcliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApPaterno(rs.getString("appaterno"));
                    cliente.setApMaterno(rs.getString("apmaterno"));
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }
}