package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Implementación de CConexion que apunta a la base de datos de PRUEBA (dbpos_test).
 * Usada solo por los archivos de Test (DAO Tests).
 */
public class CConexionTest extends CConexion {

    private String usuario = "root";
    private String contrasena = ""; 
    private String bd = "dbpos_test";
    private String ip = "localhost";
    private String puerto = "3306";
    private String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    /**
     * Sobrescribe el método de la clase padre para conectarse a la BD de prueba.
     */
    @Override
    public Connection estableceConexion() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
        } catch (Exception e1) {
            // Usamos System.out.println en lugar de JOptionPane para las pruebas
            System.err.println("No se conectó a la BD de PRUEBA: " + e1.toString());
        }
        return conectar;
    }
    
}