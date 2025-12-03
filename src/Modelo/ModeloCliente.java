package Modelo;

/**
 * Representa la entidad 'Cliente' de la base de datos.
 * Esta clase se utiliza para almacenar y transportar los datos
 * de un cliente (ID, nombre, apellidos) a través de las diferentes capas de la aplicación
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
        validarTextoNoVacio(nombre, "El nombre");
        this.nombre = nombre.trim();
    }

    public void setApPaterno(String apPaterno) {
        validarTextoNoVacio(apPaterno, "El apellido paterno");
        this.apPaterno = apPaterno.trim();
    }

    public void setApMaterno(String apMaterno) {
        validarTextoNoVacio(apMaterno, "El apellido materno");
        this.apMaterno = apMaterno.trim();
    }
    
    /**
     * Valida que un texto no sea nulo ni vacío.
     * @param texto El texto a validar
     * @param campo El nombre del campo para el mensaje de error
     * @throws IllegalArgumentException si el texto es inválido
     */
    private void validarTextoNoVacio(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apPaterno='" + apPaterno + '\'' +
                ", apMaterno='" + apMaterno + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        ModeloCliente that = (ModeloCliente) o;
        return idCliente == that.idCliente;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(idCliente);
    }
}