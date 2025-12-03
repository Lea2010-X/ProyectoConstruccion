package Controlador;


import DAO.ReportesDAO;
import Modelo.ModeloReporteFactura;
import Modelo.ModeloDetalleVenta;
import Modelo.ModeloReporteVentasPeriodo;
import Util.Constantes;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Controlador para los módulos de Reportes.
 * Coordina las Vistas de reportes con el ReportesDAO.
 */
public final class ControladorReportes extends ControladorBase {
 
    private ReportesDAO reportesDAO;
    private DecimalFormat formato = Constantes.FORMATEADOR_DECIMAL;

    /**
     * Constructor estándar para la aplicación.
     * Usa el DAO real.
     */
    public ControladorReportes() {
        this.reportesDAO = new ReportesDAO();
        validarDependencias();
    }
    
    /**
     * Constructor para Pruebas (Inyección de Dependencias).
     * @param reportesDAO Un DAO (puede ser uno real o un mock).
     */
    public ControladorReportes(ReportesDAO reportesDAO) {
        this.reportesDAO = reportesDAO;
        validarDependencias();
    }

    @Override
    protected void validarDependencias() {
        if (this.reportesDAO == null) {
            throw new IllegalStateException("ReportesDAO no puede ser nulo");
        }
    }

    /**
     * Busca un comprobante completo por su número.
     * @param numeroFactura El ID a buscar.
     * @return Un ModeloReporteFactura, o null si no se encuentra.
     * @throws SQLException si hay un error de BD.
     */
    public ModeloReporteFactura buscarComprobante(int numeroFactura) throws SQLException {
        return reportesDAO.buscarComprobante(numeroFactura);
    }
    
    /**
     * Genera el reporte de ventas por fecha.
     * @param desde Fecha de inicio.
     * @param hasta Fecha de fin.
     * @return Un ModeloReporteVentasPeriodo con los items y el total.
     * @throws SQLException si hay un error de BD.
     */
    public ModeloReporteVentasPeriodo generarReportePorFecha(Date desde, Date hasta) throws SQLException {
        List<ModeloDetalleVenta> items = reportesDAO.generarReportePorFecha(desde, hasta);
        double totalGeneral = 0;
        for (ModeloDetalleVenta item : items) {
            totalGeneral += item.getSubtotal();
        }
        ModeloReporteVentasPeriodo reporte = new ModeloReporteVentasPeriodo();
        reporte.setItems(items);
        reporte.setTotalGeneral(Double.parseDouble(formato.format(totalGeneral)));
        
        return reporte;
    }
}