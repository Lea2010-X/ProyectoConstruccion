package DAO;

import Configuracion.CConexion;
import Configuracion.CConexionTest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.BeforeEach;

/**
 * Clase base abstracta para todas las pruebas de DAO.
 * Proporciona la infraestructura común de conexión a la base de datos de prueba
 * y métodos helper para limpieza de tablas.
 */
public abstract class BaseDAOTest {
    
    protected CConexion conectorDePrueba;
    
    /**
     * Inicializa el conector de prueba ANTES de cada test.
     * Este método puede ser sobrescrito por las clases hijas si necesitan
     * configuración adicional, pero deben llamar a super.setUp().
     */
    @BeforeEach
    public void setUp() throws Exception {
        conectorDePrueba = new CConexionTest();
    }
    
    /**
     * Método helper para limpiar tablas específicas de la base de datos de prueba.
     * Desactiva temporalmente las restricciones de foreign keys para permitir TRUNCATE.
     * 
     * @param tablas Nombres de las tablas a limpiar (en el orden que se proporcionan).
     * @throws SQLException si ocurre un error al limpiar las tablas.
     * @throws IllegalArgumentException si algún nombre de tabla contiene caracteres no válidos.
     */
    protected void limpiarTablas(String... tablas) throws SQLException {
        try (Connection conn = conectorDePrueba.estableceConexion();
            Statement st = conn.createStatement()) {
            st.execute("SET FOREIGN_KEY_CHECKS=0");
            
            for (String tabla : tablas) {
                if (!tabla.matches("^\\w+$")) {
                    throw new IllegalArgumentException(
                        "Nombre de tabla no válido: " + tabla + 
                        ". Solo se permiten caracteres alfanuméricos y guiones bajos.");
                }
                st.addBatch("TRUNCATE TABLE `" + tabla + "`"); 
            }
            
            st.executeBatch();
            st.execute("SET FOREIGN_KEY_CHECKS=1");
        }
    }
    
    /**
     * Obtiene una conexión a la base de datos de prueba.
     * Útil para operaciones de setup complejas en las clases hijas.
     * 
     * @return Connection objeto de conexión a la BD de prueba.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    protected Connection getConnection() throws SQLException {
        return conectorDePrueba.estableceConexion();
    }
}

