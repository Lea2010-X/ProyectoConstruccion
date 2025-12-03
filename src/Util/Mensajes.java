package Util;

public final class Mensajes {

    // Validaciones Genéricas 
    public static final String MSG_CAMPOS_OBLIGATORIOS = "Todos los campos son obligatorios.";
    public static final String MSG_SELECCIONE_REGISTRO = "Debe seleccionar un registro de la tabla.";
    public static final String MSG_NUMERO_INVALIDO = "Ingrese un número válido.";
    public static final String MSG_NUMERO_POSITIVO = "El valor debe ser positivo.";
    
    // Validaciones Clientes
    public static final String MSG_NOMBRE_REQUERIDO = "El nombre es requerido.";
    public static final String MSG_APPATERNO_REQUERIDO = "El apellido paterno es requerido.";
    public static final String MSG_APMATERNO_REQUERIDO = "El apellido materno es requerido.";

    // Validaciones Productos/Ventas
    public static final String MSG_PRECIO_POSITIVO = "El precio debe ser un número positivo.";
    public static final String MSG_STOCK_NO_NEGATIVO = "El stock no puede ser negativo.";
    public static final String MSG_CANTIDAD_MAYOR_CERO = "La cantidad debe ser mayor a cero.";
    public static final String MSG_STOCK_INSUFICIENTE = "La cantidad de venta no puede ser mayor al stock disponible.";
    public static final String MSG_PRODUCTO_DUPLICADO = "El producto ya está agregado en la lista.";
    public static final String MSG_SELECCIONE_CLIENTE = "Debe seleccionar un cliente para realizar la venta.";
    public static final String MSG_CARRITO_VACIO = "Debe agregar al menos un producto a la venta.";

    // Validaciones Reportes
    public static final String MSG_INGRESE_NUM_FACTURA = "Debe ingresar un número de factura.";
    public static final String MSG_FACTURA_NO_ENCONTRADA = "No se encontró la factura N°: ";
    public static final String MSG_SELECCIONE_RANGO_FECHAS = "Debe seleccionar un rango de fechas válido.";
    public static final String MSG_FECHAS_INCOHERENTES = "La fecha 'Desde' no puede ser posterior a la fecha 'Hasta'.";
    public static final String MSG_REPORTE_REQUERIDO_PDF = "Debe generar o buscar la información antes de crear el PDF.";

    // Validaciones de pdf
    public static final String MSG_PDF_GENERADO_EXITO = "PDF generado exitosamente en:\n%s\n\n¿Desea abrir el archivo?";
    public static final String MSG_ERROR_CREAR_PDF = "Error al crear el documento PDF: ";
    public static final String MSG_ERROR_ABRIR_ARCHIVO = "No se puede abrir el archivo automáticamente.";

    // Confirmaciones
    public static final String MSG_CONFIRMAR_ELIMINACION = "¿Está seguro de que desea eliminar este registro?";
    public static final String TITULO_CONFIRMAR = "Confirmar Acción";

    // Mensajes de Éxito
    public static final String MSG_GUARDADO_EXITO = "Registro guardado correctamente.";
    public static final String MSG_MODIFICADO_EXITO = "Registro modificado correctamente.";
    public static final String MSG_ELIMINADO_EXITO = "Registro eliminado correctamente.";
    public static final String MSG_VENTA_EXITO = "Venta realizada correctamente.";
    public static final String TITULO_EXITO = "Operación Exitosa";
    public static final String TITULO_PDF_GENERADO = "PDF Generado";

    // Mensajes de Error y Advertencia
    public static final String MSG_ERROR_BD = "Error de base de datos: ";
    public static final String MSG_ERROR_FORMATO = "Error de formato en los datos ingresados.";
    public static final String TITULO_ERROR = "Error";
    public static final String TITULO_ADVERTENCIA = "Advertencia";

    private Mensajes() {
        throw new UnsupportedOperationException("No instanciar");
    }
}