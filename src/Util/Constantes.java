package Util;

import java.text.DecimalFormat;

/**
 * Clase de utilidad que centraliza configuraciones y constantes lógicas.
 */
public final class Constantes {
    
    public static final double TASA_IVA = 0.18;
    
    /**
     * Formato decimal para mostrar valores monetarios.
     */
    public static final String FORMATO_DECIMAL = "#.##";
    
    /**
     * Instancia compartida de DecimalFormat.
     */
    public static final DecimalFormat FORMATEADOR_DECIMAL = new DecimalFormat(FORMATO_DECIMAL);
    
    /**
     * Constructor privado para evitar instanciación.
     */
    private Constantes() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }
}