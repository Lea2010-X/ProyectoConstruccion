package Util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Clase de utilidad para aplicar un tema moderno y consistente a toda la aplicación.
 * Proporciona métodos para estilizar componentes con colores, fuentes y bordes modernos.
 */
public class TemaModerno {
    
    // Paleta de colores moderna (Material Design)
    public static final Color COLOR_PRIMARIO = new Color(33, 150, 243);      // Azul
    public static final Color COLOR_PRIMARIO_OSCURO = new Color(21, 101, 192); // Azul oscuro
    public static final Color COLOR_SECUNDARIO = new Color(76, 175, 80);     // Verde
    public static final Color COLOR_PELIGRO = new Color(244, 67, 54);        // Rojo
    public static final Color COLOR_ADVERTENCIA = new Color(255, 152, 0);    // Naranja
    public static final Color COLOR_FONDO = new Color(245, 245, 245);        // Gris claro
    public static final Color COLOR_TEXTO_OSCURO = new Color(33, 33, 33);    // Gris oscuro
    public static final Color COLOR_TEXTO_CLARO = new Color(117, 117, 117);  // Gris medio
    public static final Color COLOR_BORDE = new Color(189, 189, 189);        // Gris borde
    
    // Fuentes modernas
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_PEQUENA = new Font("Segoe UI", Font.PLAIN, 10);
    
    /**
     * Estiliza un botón con tema moderno.
     * 
     * @param boton Botón a estilizar
     * @param tipo "primario", "secundario", "peligro" o "advertencia"
     */
    public static void estilizarBoton(JButton boton, String tipo) {
        boton.setFont(FUENTE_NORMAL);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(true);
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        
        switch (tipo.toLowerCase()) {
            case "primario":
                boton.setBackground(COLOR_PRIMARIO);
                boton.setForeground(Color.WHITE);
                break;
            case "secundario":
                boton.setBackground(COLOR_SECUNDARIO);
                boton.setForeground(Color.WHITE);
                break;
            case "peligro":
                boton.setBackground(COLOR_PELIGRO);
                boton.setForeground(Color.WHITE);
                break;
            case "advertencia":
                boton.setBackground(COLOR_ADVERTENCIA);
                boton.setForeground(Color.WHITE);
                break;
            default:
                boton.setBackground(COLOR_PRIMARIO);
                boton.setForeground(Color.WHITE);
        }
    }
    
    /**
     * Estiliza un campo de texto con tema moderno.
     * 
     * @param campo Campo de texto a estilizar
     */
    public static void estilizarCampoTexto(JTextField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBackground(Color.WHITE);
        campo.setForeground(COLOR_TEXTO_OSCURO);
        campo.setCaretColor(COLOR_PRIMARIO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
    }
    
    /**
     * Estiliza un panel con tema moderno.
     * 
     * @param panel Panel a estilizar
     * @param conSombra Si aplica sombra al panel
     */
    public static void estilizarPanel(JPanel panel, boolean conSombra) {
        panel.setBackground(Color.WHITE);
        
        if (conSombra) {
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(224, 224, 224)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
            ));
        } else {
            panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        }
    }
    
    /**
     * Estiliza una tabla con tema moderno.
     * 
     * @param tabla Tabla a estilizar
     */
    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setRowHeight(28);
        tabla.setSelectionBackground(COLOR_PRIMARIO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(COLOR_BORDE);
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(COLOR_TEXTO_OSCURO);
        
        // Estilizar encabezado
        javax.swing.table.JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setBackground(COLOR_PRIMARIO);
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(FUENTE_SUBTITULO);
        encabezado.setBorder(BorderFactory.createLineBorder(COLOR_PRIMARIO_OSCURO, 1));
    }
    
    /**
     * Estiliza una etiqueta con tema moderno.
     * 
     * @param etiqueta Etiqueta a estilizar
     * @param tipo "titulo", "subtitulo" o "normal"
     */
    public static void estilizarEtiqueta(JLabel etiqueta, String tipo) {
        etiqueta.setForeground(COLOR_TEXTO_OSCURO);
        
        switch (tipo.toLowerCase()) {
            case "titulo":
                etiqueta.setFont(FUENTE_TITULO);
                break;
            case "subtitulo":
                etiqueta.setFont(FUENTE_SUBTITULO);
                break;
            default:
                etiqueta.setFont(FUENTE_NORMAL);
        }
    }
    
    /**
     * Obtiene un borde moderno redondeado.
     * 
     * @return Border moderno
     */
    public static Border obtenerBordeModerno() {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        );
    }
}
