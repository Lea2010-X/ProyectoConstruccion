package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * La clase CConexion gestiona la conexión con la base de datos MySQL.
 * * Su propósito es centralizar los datos de acceso y los métodos para
 * establecer y cerrar la conexión, facilitando su uso en otras partes
 * de la aplicación.
 */
public class CConexion {
    
    private Connection conectar = null;
    
    private final String usuario="root";
    private final String contrasena="";
    private final String bd="dbpos";
    private final String ip="localhost";
    private final String puerto="3306";
    
    private final String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    
    public Connection estableceConexion(){
        try{
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, "No se conectó a la BD: "+e1.toString());
        }
        return conectar;
    }
    
    public void cerrarConexion(){
        try{
            if(conectar!=null && !conectar.isClosed()){
                conectar.close();
            }
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null, "No se logró cerrar la conexión: "+e1.toString());
        }
    }
}