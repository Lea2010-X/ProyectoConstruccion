package Configuracion;

/**
 * Implementación de CConexion que apunta a la base de datos de PRUEBA (dbpos_test).
 * Usada solo por los archivos de Test (DAO Tests).
 * 
 * Esta clase simplemente hereda de CConexion y usa el archivo config.properties
 * para obtener la configuración de la base de datos de prueba.
 */
public class CConexionTest extends CConexion {

    /**
     * Constructor que carga la configuración desde config.properties
     */
    public CConexionTest() {
        super("config.properties");
    }
}