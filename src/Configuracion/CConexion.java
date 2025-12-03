package Configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * La clase CConexion gestiona la conexión con la base de datos MySQL.
 */ 
public class CConexion {
    
    private Connection conectar = null;
    
    private final String usuario;
    private final String contrasena;
    private final String cadena;
    private final Properties configuracion;
    
    /**
     * Constructor que carga la configuración desde config.properties
     */
    public CConexion() {
        this("config.properties");
    }
    
    /**
     * Constructor que permite especificar un archivo de configuración específico.
     * 
     * 
     * @param archivoConfig Ruta al archivo de propiedades
     */
    public CConexion(String archivoConfig) {
        this.configuracion = cargarConfiguracion(archivoConfig);
        
        this.usuario = configuracion.getProperty("db.usuario");
        this.contrasena = configuracion.getProperty("db.contrasena");
        
        String host = configuracion.getProperty("db.host");
        String puerto = configuracion.getProperty("db.puerto");
        String bd = configuracion.getProperty("db.nombre");
        
        this.cadena = "jdbc:mysql://" + host + ":" + puerto + "/" + bd;
    }
    
    /**
     * Carga la configuración desde un archivo .properties
     * 
     * @param archivoConfig Ruta al archivo
     * @return Properties con la configuración
     */
    private Properties cargarConfiguracion(String archivoConfig) {
        Properties prop = new Properties();
        
        try (InputStream input = new FileInputStream(archivoConfig)) {
            prop.load(input);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "No se pudo cargar el archivo de configuración: " + archivoConfig + ". " + e.getMessage());
        }
        
        return prop;
    }
    
    /**
     * Establece y devuelve una conexión a la base de datos.
     * 
     * @return Connection objeto de conexión
     */
    public Connection estableceConexion() {
        try {
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "No se pudo conectar a la base de datos: " + e.getMessage());
        }
        return conectar;
    }
}