package Controlador;

/**
 * Clase abstracta base para todos los controladores.
 * Proporciona la estructura común y define contratos que todos los controladores deben seguir.
 * 
 * Ventajas:
 * - Evita duplicación de código en constructores y atributos DAO.
 * - Proporciona un contrato común para futuras extensiones.
 * - Facilita el mantenimiento y consistencia del código.
 */
public abstract class ControladorBase {
    
    /**
     * Método abstracto que cada controlador debe implementar.
     * Sirve como plantilla para validar la inyección de dependencias.
     */
    protected abstract void validarDependencias();
}
