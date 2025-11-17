package Modelo;

/**
 * Representa la entidad 'Cliente' de la base de datos.
 * Esta clase se utiliza para almacenar y transportar los datos
 * de un cliente (ID, nombre, apellidos) a través de las diferentes capas de la aplicación
 *
 */
public class ModeloCliente {
   
    private int idCliente;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }
    
    
}